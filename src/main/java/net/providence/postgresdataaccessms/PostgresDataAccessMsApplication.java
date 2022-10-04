package net.providence.postgresdataaccessms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PostgresDataAccessMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresDataAccessMsApplication.class, args);
    }

}
