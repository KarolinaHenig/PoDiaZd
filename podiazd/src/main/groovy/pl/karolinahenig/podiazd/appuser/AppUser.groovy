package pl.karolinahenig.podiazd.appuser


import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.SequenceGenerator


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
class AppUser implements UserDetails{

    @Id
    @SequenceGenerator(
            name =  "user_id",
            sequenceName = "user_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id"
    )

    private Long id
    private String name
    private String username
    private String email
    private String password
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole
    private Boolean locked
    private Boolean enabled

    AppUser(String name,
            String username,
            String email,
            String password,
            AppUserRole appUserRole,
            Boolean locked,
            Boolean enabled) {

        this.name = name
        this.username = username
        this.email = email
        this.password = password
        this.appUserRole = appUserRole
        this.locked = locked
        this.enabled = enabled
    }

    AppUser() {}

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

    @Override
    String getUsername() {
        return username
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
