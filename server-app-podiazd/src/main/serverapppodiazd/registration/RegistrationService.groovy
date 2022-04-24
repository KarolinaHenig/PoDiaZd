package main.serverapppodiazd.registration

import org.springframework.stereotype.Service

@Service
class RegistrationService {
    static register = { RegistrationService request ->
        return "works"
    }
}
