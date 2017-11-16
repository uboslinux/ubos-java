//
// This file is part of InfoGrid(tm). You may not use this file except in
// compliance with the InfoGrid license. The InfoGrid license and important
// disclaimers are contained in the file LICENSE.InfoGrid.txt that you should
// have received with InfoGrid. If you have not received LICENSE.InfoGrid.txt
// or you do not consent to all aspects of the license and the disclaimers,
// no license is granted; do not use this file.
// 
// For more information about InfoGrid go to http://infogrid.org/
//
// Copyright 1998-2016 by Johannes Ernst
// All rights reserved.
//

package net.ubos.undertow;

import io.undertow.Undertow;
import org.diet4j.core.Module;
import org.diet4j.core.ModuleActivationException;
import org.diet4j.core.ModuleDeactivationException;
import org.diet4j.core.ModuleSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main program of the Undertow daemon. Activate using diet4j.
 */
public class ModuleInit
{
    private static final Logger log = LoggerFactory.getLogger( ModuleInit.class );

    /**
     * Diet4j module activation.
     * 
     * @param thisModule the Module being activated
     * @throws ModuleActivationException thrown if module activation failed
     */
    public static void moduleActivate(
            Module thisModule )
        throws
            ModuleActivationException
    {
        log.trace( "Activating {}", thisModule );

        ModuleSettings settings = thisModule.getModuleSettings();

        Undertow.Builder builder = Undertow.builder();
        
        builder.addHttpListener(
                settings.getInteger( "port", 80 ),
                settings.getString(  "host", "localhost" ));
        if( settings.containsKey( "serverthreads" )) {
            builder.setIoThreads(
                    settings.getInteger( "serverthreads" ));
        }
        builder.setHandler( DispatchingHandler.SINGLETON );

        theUndertowServer = builder.build();
        theUndertowServer.start();
    }

    /**
     * Diet4j module deactivation.
     * 
     * @param thisModule the Module being deactivated
     * @throws ModuleDeactivationException thrown if module deactivation failed
     */
    public static void moduleDeactivate(
            Module thisModule )
        throws
            ModuleDeactivationException
    {
        log.trace( "Deactivating {}", thisModule );

        try {
            if( theUndertowServer != null ) {
                theUndertowServer.stop();
            }

        } catch( Exception ex ) {
            throw new ModuleDeactivationException( thisModule.getModuleMeta(), ex );
        }
    }

    /**
     * The HTTP server.
     */
    protected static Undertow theUndertowServer;

}
