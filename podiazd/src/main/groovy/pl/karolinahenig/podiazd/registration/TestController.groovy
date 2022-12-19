package pl.karolinahenig.podiazd.registration

import org.springframework.context.annotation.Role
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = "api/v1/test")
@CrossOrigin(origins = "http://localhost:4200/")
class TestController {

    @GetMapping
    String test() {
        def principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        return "jupiii"
    }

}
