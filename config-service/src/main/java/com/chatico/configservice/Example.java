//package com.chatico.configservice;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.vault.core.VaultOperations;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Example {
//
//    // inject the actual template
//    @Autowired
//    private VaultOperations operations;
//
//    public void writeSecrets(String userId, String password) {
//
//        Map<String, String> data = new HashMap<String, String>();
//        data.put("password", password);
//
//        operations.write(userId, data);
//    }
//
//    public Person readSecrets(String userId) {
//
//        VaultResponseSupport<Person> response = operations.read(userId, Person.class);
//        return response.getBody();
//    }
//}