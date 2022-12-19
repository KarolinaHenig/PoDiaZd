package pl.karolinahenig.podiazd.login

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.karolinahenig.podiazd.appuser.AppUserService

@Service
class LoginService {
    @Autowired
    AppUserService appUserService
    String login(LoginRequest request) {
         String email = request.email
        boolean isValidUser = appUserService.loadUserByUsername(email)
        if (!isValidUser) {
            throw new IllegalAccessException("Wprowadzony adres e-mail nie jest powiązany z kontem")
        }
    }

}