package org.example;

import ejb.CitoyenService;
import org.example.ejb.EJBFactory;

import javax.naming.NamingException;

public class Main {
    public static void main(String[] args) throws NamingException {
        CitoyenService c = EJBFactory.createCitoyenBeanFromJNDI();
        System.out.println(c.getCitoyen("123456789011").getNom());
    }
}