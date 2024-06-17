package pl.karolinahenig.podiazd.appuser


import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@EqualsAndHashCode
@NoArgsConstructor
@Entity
class AppUser implements UserDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id
    private String name
    private String surname
    private String email
    private String password
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole
    private Boolean locked = false
    private Boolean enabled = false

    AppUser(String name,
            String surname,
            String email,
            String password,
            AppUserRole appUserRole) {

        this.name = name
        this.surname = surname
        this.email = email
        this.password = password
        this.appUserRole = appUserRole
        this.locked = locked
        this.enabled = enabled
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name())
        return Collections.singletonList(authority)
    }

    @Override
    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getSurname() {
        return surname
    }

    void setSurname(String surname) {
        this.surname = surname
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    AppUserRole getAppUserRole() {
        return appUserRole
    }

    void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole
    }

    Boolean getLocked() {
        return locked
    }

    void setLocked(Boolean locked) {
        this.locked = locked
    }

    Boolean getEnabled() {
        return enabled
    }

    void setEnabled(Boolean enabled) {
        this.enabled = enabled
    }

    @Override
    String getUsername() {
        return email
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return !locked
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return enabled
    }
}
