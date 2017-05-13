package io.chrismajor.wlt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * Created by Christo on 06/05/2017.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /** added to allow sec namespace in Thymeleaf*/
    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}
