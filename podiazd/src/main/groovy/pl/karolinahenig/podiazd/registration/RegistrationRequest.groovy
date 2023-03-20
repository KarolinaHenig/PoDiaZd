package pl.karolinahenig.podiazd.registration

import lombok.EqualsAndHashCode
import lombok.ToString
import org.springframework.beans.factory.annotation.Autowired

@EqualsAndHashCode
@ToString
class RegistrationRequest {
    @Autowired
    final String name
    @Autowired
    final String surname
    @Autowired
    final String email
    @Autowired
    final String password

    String getName() {
        return name
    }

    String getSurname() {
        return surname
    }

    String getEmail() {
        return email
    }

    String getPassword() {
        return password
    }
}
