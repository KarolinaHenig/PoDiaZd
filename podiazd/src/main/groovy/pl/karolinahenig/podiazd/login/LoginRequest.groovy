package pl.karolinahenig.podiazd.login

import lombok.EqualsAndHashCode
import lombok.ToString
import org.springframework.beans.factory.annotation.Autowired

@EqualsAndHashCode
@ToString
class LoginRequest {
    @Autowired
    final String email
    @Autowired
    final String password

    String getEmail() {
        return email
    }

    String getPassword() {
        return password
    }
}
