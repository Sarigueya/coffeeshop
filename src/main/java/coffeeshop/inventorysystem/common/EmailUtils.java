package coffeeshop.inventorysystem.common;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailUtils {

    private final JavaMailSender emailSender;

    private final MessageSource messageSource;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromMail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        if (list != null && list.size() > 0)
            message.setCc(list.toArray(new String[0]));
        emailSender.send(message);
    }

    private String[] getCcArray(List<String> ccList) {
        String[] cc = new String[ccList.size()];

        for (int i = 0; i < ccList.size(); i++) {
            cc[i] = ccList.get(i);
        }
        return cc;
    }

    public void forgotMail(String to, String subject, String password) throws MessagingException {
        var locale = LocaleContextHolder.getLocale();
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromMail);
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "<p><b>" + messageSource.getMessage("email.forgot.html.title", null, locale) + "</b><br>"
                + "<b>" + messageSource.getMessage("email.forgot.html.email", null, locale) + " </b> " + to + " <br>"
                + "<b>" + messageSource.getMessage("email.forgot.html.password", null, locale) + " </b> " + password + "<br>"
                + "<b>" + messageSource.getMessage("email.forgot.html.remember", null, locale) + " </b> " + "<br>"
                + "<a href=\"https://localhost:4200/\">" + messageSource.getMessage("email.forgot.html.login", null, locale) + "</a></p>";
        message.setContent(htmlMsg, "text/html");
        emailSender.send(message);
    }
}
