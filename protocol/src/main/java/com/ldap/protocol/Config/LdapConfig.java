package com.ldap.protocol.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

//@Configuration
//public class LdapConfig {
//
//    @Value("${ldap.server}")
//    private String ldapServer;
//
//    @Value("${ldap.port}")
//    private int ldapPort;
//
//    @Value("${ldap.baseDN}")
//    private String baseDN;
//
//    @Bean
//    public LdapTemplate ldapTemplate() {
//        LdapContextSource contextSource = new LdapContextSource();
//        contextSource.setUrl("ldap://" + ldapServer + ":" + ldapPort);
//        contextSource.setBase(baseDN);
//        contextSource.setUserDn("");
//        contextSource.setPassword("");
//        contextSource.afterPropertiesSet();
//
//        return new LdapTemplate(contextSource);
//    }
//}

@Configuration
public class LdapConfig {

    private final String ldapServer = "34.67.239.153";
    private final int ldapPort = 389;
    private final String baseDN = "dc=server403,dc=com";
    private final String userDn = "cn=admin,dc=server403,dc=com";
    private final String password = "Xi5peHj[4z01K1V]";

    @Bean
    public LdapTemplate ldapTemplate() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://" + ldapServer + ":" + ldapPort);
        contextSource.setBase(baseDN);
        contextSource.setUserDn(userDn);
        contextSource.setPassword(password);
        contextSource.afterPropertiesSet();

        return new LdapTemplate(contextSource);
    }
}
