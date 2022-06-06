package pl.karolinahenig.podiazd.registration

import org.springframework.stereotype.Service

import java.util.function.Predicate

@Service
class EmailValidator implements Predicate<String> {

    @Override
    boolean test(String s) {
        //TODO: Regex to validate email
        return true
    }
}
