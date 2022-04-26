package pl.karolinahenig.podiazd.appuser


import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email)
}