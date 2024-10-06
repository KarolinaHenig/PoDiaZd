package pl.karolinahenig.podiazd.registration


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = "api/v1/registration")
class RegistrationController {

    @Autowired
    private final RegistrationService registrationService

    @PostMapping
    void register(@RequestBody RegistrationRequest request) {
         registrationService.register(request)
    }

    @GetMapping (path="confirm")
    String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token)
    }

}
