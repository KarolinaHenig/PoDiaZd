package pl.karolinahenig.podiazd.registration.email

interface EmailSender {
void send(String to, String email)
}