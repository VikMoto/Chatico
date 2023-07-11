package com.chatico.configservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


//import org.springframework.vault.authentication.TokenAuthentication;
//import org.springframework.vault.client.VaultEndpoint;
//import org.springframework.vault.support.Versioned;
//import org.springframework.vault.core.VaultTemplate;



@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }
}
//public class ConfigServiceApplication implements CommandLineRunner {
//
//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(ConfigServiceApplication.class, args);
//        context.close();
//    }
//
//    @Override
//    public void run(String... strings) throws Exception {
//        VaultEndpoint vaultEndpoint = new VaultEndpoint();
//
//        vaultEndpoint.setHost("127.0.0.1");
//        vaultEndpoint.setPort(8200);
//        vaultEndpoint.setScheme("http");
//
//        // Authenticate
//        VaultTemplate vaultTemplate = new VaultTemplate(
//                vaultEndpoint,
//                new TokenAuthentication("dev-only-token"));
//
//        // Write a secret
//        Map<String, String> data = new HashMap<>();
//        data.put("spring.datasource.driverClassName", "org.postgresql.Driver");
//        data.put("spring.jpa.database-platform", "org.hibernate.dialect.PostgreSQLDialect");
//        data.put("spring.datasource.url", "jdbc:postgresql://localhost:5433/message_db");
//        data.put("spring.datasource.username", "postgress");
//        data.put("spring.datasource.password", "postgress");
//        data.put("spring.flyway.user", "postgres");
//        data.put("spring.flyway.password", "postgres");
//        data.put("spring.flyway.defaultSchema", "public");
//
//        Versioned.Metadata createResponse = vaultTemplate
//                .opsForVersionedKeyValue("secret")
//                .put("my-secret-password", data);
//
//        System.out.println("Secret written successfully.");
//
//        // Read a secret
//        Versioned<Map<String, Object>> readResponse = vaultTemplate
//                .opsForVersionedKeyValue("secret")
//                .get("my-secret-password");
//
//        String password = "";
//        if (readResponse != null && readResponse.hasData()) {
//            password = (String) readResponse.getData().get("spring.datasource.password");
//        }
//
//        if (!password.equals("postgres")) {
//            throw new Exception("Unexpected password");
//        }
//
//        System.out.println("Access granted!");
//    }
//}
