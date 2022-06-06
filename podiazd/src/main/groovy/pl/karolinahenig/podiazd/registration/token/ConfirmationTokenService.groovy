package pl.karolinahenig.podiazd.registration.token

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.LocalDateTime

@Service
class ConfirmationTokenService {
    @Autowired
    private final ConfirmationTokenRepository confirmationTokenRepository

     void saveConfirmationToken(ConfirmationToken token){
     confirmationTokenRepository.save(token)
     }

    Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    int setConfirmedAt(String token) {
        return confirmationTokenRepository.upadateConfirmedAt(
                token, LocalDateTime.now());
    }
}
