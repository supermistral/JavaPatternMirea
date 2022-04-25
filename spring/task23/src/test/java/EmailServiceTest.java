import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import ru.mirea.task23.services.EmailService;

import javax.mail.internet.MimeMessage;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    private EmailService emailService;

    @Test
    public void sendEmailTest() {
        Mockito.when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        Mockito.doNothing().when(mailSender).send(Mockito.any(MimeMessage.class));

        emailService.sendMail("email@email.com", "subject", "message");

        Mockito.verify(mailSender).send(Mockito.any(MimeMessage.class));
    }
}
