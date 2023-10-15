package org.example.ejb;

import ejb.CitoyenService;
import ejb.CitoyenServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBFactory {
        public static CitoyenService createCitoyenBeanFromJNDI() throws NamingException {
            return lookupCitoyenBean();
        }

        private static CitoyenService lookupCitoyenBean() throws NamingException {
            Context ctx = createInitialContext();
            String appName = "";
            String moduleName = "Citoyen_ejb_server-1.0-SNAPSHOT";
            String distinctName = "";
            String beanName = CitoyenServiceImpl.class.getSimpleName();
            String viewClassName = CitoyenService.class.getName();
            return (CitoyenService) ctx.lookup("ejb:"
                    + appName + "/" + moduleName
                    + "/" + distinctName + "/" + beanName + "!" + viewClassName);
        }

        private static Context createInitialContext() throws NamingException {
            Properties jndiProperties = new Properties();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.jboss.naming.remote.client.InitialContextFactory");
            jndiProperties.put(Context.URL_PKG_PREFIXES,
                    "org.jboss.ejb.client.naming");
            jndiProperties.put(Context.PROVIDER_URL,
                    "http-remoting://localhost:8080");
            jndiProperties.put("jboss.naming.client.ejb.context", true);
            return new InitialContext(jndiProperties);
        }
}
