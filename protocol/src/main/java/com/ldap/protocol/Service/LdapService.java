package com.ldap.protocol.Service;

import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.Hashtable;
import javax.naming.AuthenticationException;


@Service
public class LdapService {

//    private static final
    String LDAP_URL = "ldap://34.67.239.153";
//    private static final
    String LDAP_BASE_DN = "dc=server403,dc=com";


//    public boolean authenticate(String username, String password) {
//        try {
//            Hashtable<String, String> env = new Hashtable<>();
//            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//            env.put(Context.PROVIDER_URL, LDAP_URL);
//            env.put(Context.SECURITY_AUTHENTICATION, "simple");
//            env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + "," + LDAP_BASE_DN);
//            env.put(Context.SECURITY_CREDENTIALS, password);
//
//            DirContext context = new InitialDirContext(env);
//            context.close();
//            return true;
//        } catch (NamingException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


    public boolean authenticate(String username, String password) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, LDAP_URL);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + "," + LDAP_BASE_DN);
            env.put(Context.SECURITY_CREDENTIALS, password);

            DirContext context = new InitialDirContext(env);
            context.close();
            return true;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        } catch (NamingException e) {
            e.printStackTrace();
            return false;
        }
    }



    private String getUserAttribute(String username, String attribute) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, LDAP_URL);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + "," + LDAP_BASE_DN);
//            env.put(Context.SECURITY_CREDENTIALS, "Xi5peHj[4z01K1V]");

            DirContext context = new InitialDirContext(env);

            // Retrieve user's attributes
            Attributes attributes = context.getAttributes("");
            String userAttribute = attributes.get(attribute).get().toString();
            context.close();
            return userAttribute;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFirstName(String username) {
        return getUserAttribute(username, "givenName");
    }

    public String getLastName(String username) {
        return getUserAttribute(username, "sn");
    }
}






