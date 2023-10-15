package org.example;

import ejb.CitoyenService;
import model.Citoyen;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws NamingException {

        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "root");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "root");

        Context context = new InitialContext(jndiProperties);

        final String jndiName = "ejb:/Citoyen_ejb_server/CitoyenServiceImpl!" + CitoyenService.class.getName();
        CitoyenService citoyenService = (CitoyenService) context.lookup(jndiName);

        Citoyen citoyen = citoyenService.getCitoyen("123456789012");
        System.out.println(citoyen);


    }
}