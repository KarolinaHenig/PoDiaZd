package com.example.serverapppodiazd.registration

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
class RegistrationRequest {
    final String name
    final String surname
    final String login
    final String email
    final String password
}
