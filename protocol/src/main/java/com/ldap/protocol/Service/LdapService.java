package com.ldap.protocol.Service;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;
//@Service
//public class LdapService {
//
//    private final LdapTemplate ldapTemplate;
//
//    public LdapService(LdapTemplate ldapTemplate) {
//        this.ldapTemplate = ldapTemplate;
//    }
//
//    public List<String> getAuthoritiesForUser(String username) {
//        List<String> authorities = new ArrayList<>();
//
//        // Set up the search controls
//        SearchControls controls = new SearchControls();
//        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//
//        // Search for the user in LDAP using a filter (sAMAccountName is specific to Active Directory)
//        NamingEnumeration<SearchResult> results = ldapTemplate.search(
//                "", "(sAMAccountName=" + username + ")", controls,
//                (AttributesMapper<String>) attrs -> {
//                    List<String> userGroups = new ArrayList<>();
//                    Attribute memberOf = attrs.get("memberOf");
//                    if (memberOf != null) {
//                        NamingEnumeration<?> groups = memberOf.getAll();
//                        while (groups.hasMore()) {
//                            userGroups.add(groups.next().toString());
//                        }
//                    }
//                    return userGroups;
//                });
//
//        // Process the search results
//        try {
//            while (results.hasMore()) {
//                List<String> userGroups = (List<String>) results.next();
//                authorities.addAll(userGroups);
//            }
//        } catch (Exception e) {
//            // Handle any exceptions
//        }
//
//        return authorities;
//    }
//}

@Service
public class LdapService {

    private final LdapTemplate ldapTemplate;

    public LdapService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public List<String> getAuthoritiesForUser(String username) {
        List<String> authorities = new ArrayList<>();

        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<javax.naming.directory.SearchResult> results = (NamingEnumeration<SearchResult>) ldapTemplate.search(
                "", "(sAMAccountName=" + username + ")", controls,
                (AttributesMapper<List<String>>) attrs -> {
                    List<String> userGroups = new ArrayList<>();
                    Attribute memberOf = attrs.get("memberOf");
                    if (memberOf != null) {
                        NamingEnumeration<?> groups = memberOf.getAll();
                        while (groups.hasMore()) {
                            String group = groups.next().toString();
                            // Extract only the group/role name from the full DN if needed
                            // Add logic to extract just the group/role name if required
                            userGroups.add(group);
                        }
                    }
                    return userGroups;
                });

        try {
            while (results.hasMore()) {
                List<String> userGroups = (List<String>) results.next();
                authorities.addAll(userGroups);
            }
        } catch (Exception e) {
            // Handle any exceptions
        }

        return authorities;
    }
}
