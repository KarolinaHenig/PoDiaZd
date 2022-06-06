package pl.karolinahenig.podiazd.registration.email

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

import javax.mail.MessagingException
import javax.mail.internet.MimeMessage

@Service
class EmailService implements EmailSender {

    @Autowired
    private final static org.slf4j.Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class)
    @Autowired
    private final JavaMailSender mailSender

    @Override
    @Async
    void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage()
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8")
            helper.setText(email, true)
            helper.setTo(to)
            helper.setSubject("Zweryfikuj swój email")
            helper.setFrom("hello@podiazd.com")
        } catch (MessagingException e) {
            LOGGER.error("Podczas wysyłania maila wystąpił błąd", e)
            throw new IllegalStateException("Podczas wysyłania maila wystąpił błąd")
        }

    }
}
