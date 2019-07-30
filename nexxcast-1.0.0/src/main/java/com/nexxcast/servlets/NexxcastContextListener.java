package com.nexxcast.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nexxcast.utils.RandomStringGenerator;

/**
 * Application Lifecycle Listener implementation class ServletsContextListener
 *
 */
public class NexxcastContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public NexxcastContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	ServletContext sc = sce.getServletContext();
    	
    	sc.setAttribute("randStringGen", new RandomStringGenerator());
    }
	
}
