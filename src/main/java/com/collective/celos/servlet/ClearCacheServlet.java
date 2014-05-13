package com.collective.celos.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Posting to this servlet clears the scheduler cache.
 */
@SuppressWarnings("serial")
public class ClearCacheServlet extends AbstractServlet {
        
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException {
        clearSchedulerCache();
    }

}
