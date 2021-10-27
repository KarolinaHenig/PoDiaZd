package com.example.serverapppodiazd.appuser

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface UserRepository
        extends JpaRepository<AppUser, Long> {
Optional<AppUser> findByEmail(String email)

}