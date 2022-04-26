package pl.karolinahenig.podiazd

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import pl.karolinahenig.podiazd.appuser.AppUser
import pl.karolinahenig.podiazd.appuser.AppUserRepository

@SpringBootApplication
class PodiazdApplication {

    static void main(String[] args) {
        SpringApplication.run(PodiazdApplication, args)
    }

    @Bean
    CommandLineRunner runner(AppUserRepository appUserRepository) {
        return { args ->
            AppUser user = new AppUser()
            user.enabled = true
            user.username = "test"
            user.password = "test"
            appUserRepository.save(user)
        };
    }

}
