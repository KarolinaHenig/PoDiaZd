package pl.karolinahenig.podiazd.login

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.karolinahenig.podiazd.security.jwt.JwtProvider

@RestController
@RequestMapping(path = "api/v1/login")
class LoginController {
    @Autowired
    private final LoginService loginService
    @Autowired
    JwtProvider jwtProvider
    @Autowired
    AuthenticationManager authenticationManager

    @PostMapping
    ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        )

        SecurityContextHolder.getContext().setAuthentication(authentication)

        String jwt = jwtProvider.generateJwtToken(authentication)
        return ResponseEntity.ok(['token': jwt])
    }
}
