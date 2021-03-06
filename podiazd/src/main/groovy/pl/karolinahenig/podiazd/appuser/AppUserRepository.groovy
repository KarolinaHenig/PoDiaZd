package pl.karolinahenig.podiazd.appuser

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email)

    @Transactional
    @Modifying
    @Query("UPDATE AppUser AS a SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email)
}