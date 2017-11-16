/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.ubos.undertow;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

/**
 *
 */
public final class DispatchingHandler
    implements
        HttpHandler
{
    /**
     * The singleton instance of this class.
     */
    public static final DispatchingHandler SINGLETON = new DispatchingHandler();

    /**
     * Keep this a singleton.
     */
    private DispatchingHandler() {}

    /**
     * Register a handler for a particular URL.
     * 
     * @param url, relative to context root
     * @param handler the HttpHandler to register
     */
    public void registerHandler(
            String      url,
            HttpHandler handler )
    {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleRequest(
            HttpServerExchange exchange )
        throws
            Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
