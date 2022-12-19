package pl.karolinahenig.podiazd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSenderImpl

@SpringBootApplication
class PodiazdApplication {

    static void main(String[] args) {
        SpringApplication.run(PodiazdApplication, args)
    }

    @Bean
    JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setProtocol("smtp");
        javaMailSender.setHost("127.0.0.1");
        javaMailSender.setPort(1025);
        javaMailSender.setUsername("hello")
        javaMailSender.setPassword("hello")

        return javaMailSender;
    }

}
