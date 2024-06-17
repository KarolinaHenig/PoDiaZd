package pl.karolinahenig.podiazd.registration.token


import lombok.NoArgsConstructor
import pl.karolinahenig.podiazd.appuser.AppUser

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import java.time.LocalDateTime


@NoArgsConstructor
@Entity
class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id

    @Column(nullable = false)
    private String token

    @Column(nullable = false)
    private LocalDateTime createdAt

    @Column(nullable = false)
    private LocalDateTime expiresAt

    private LocalDateTime confirmedAt

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser

    ConfirmationToken(String token,
                      LocalDateTime createdAt,
                      LocalDateTime expiresAt,
                      AppUser appUser) {
        this.token = token
        this.createdAt = createdAt
        this.expiresAt = expiresAt
        this.appUser = appUser
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getToken() {
        return token
    }

    void setToken(String token) {
        this.token = token
    }

    LocalDateTime getCreatedAt() {
        return createdAt
    }

    void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt
    }

    LocalDateTime getExpiresAt() {
        return expiresAt
    }

    void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt
    }

    LocalDateTime getConfirmedAt() {
        return confirmedAt
    }

    void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt
    }

    AppUser getAppUser() {
        return appUser
    }

    void setAppUser(AppUser appUser) {
        this.appUser = appUser
    }
}
