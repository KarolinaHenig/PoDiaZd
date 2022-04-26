package pl.karolinahenig.podiazd.registration

import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
class RegistrationController {

    private final RegistrationService registrationService
 @PostMapping
    String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request)
    }
}
