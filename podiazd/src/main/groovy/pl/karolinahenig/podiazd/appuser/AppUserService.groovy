package pl.karolinahenig.podiazd.appuser

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import pl.karolinahenig.podiazd.registration.token.ConfirmationToken
import pl.karolinahenig.podiazd.registration.token.ConfirmationTokenService

import java.time.LocalDateTime

@Service
class AppUserService implements UserDetailsService {
    @Autowired
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    @Autowired
    private final AppUserRepository appUserRepository
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder
    @Autowired
    private final ConfirmationTokenService confirmationTokenService


    @Override
    UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow({ ->
                    new UsernameNotFoundException(
                            String.format(USER_NOT_FOUND_MSG, email))
                }
                )
    }

    String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getUsername())
                .isPresent()

        if (userExists) {
            throw new IllegalStateException("Podany email już istnieje")
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword())

        appUser.setPassword(encodedPassword)

        appUserRepository.save(appUser)

        String token = UUID.randomUUID().toString()

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        )
        confirmationTokenService.saveConfirmationToken(confirmationToken)

        //TODO: SEND EMAIL

        return token
    }
     int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
