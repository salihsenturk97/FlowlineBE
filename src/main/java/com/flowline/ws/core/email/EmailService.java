package com.flowline.ws.core.email;

import com.flowline.ws.core.configuration.FlowlineProperties;
import com.flowline.ws.core.shared.Messages;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {
    @Autowired
    private FlowlineProperties flowlineProperties;
    private JavaMailSenderImpl mailSender;

    @PostConstruct
    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(flowlineProperties.getEmail().host());
        mailSender.setPort(flowlineProperties.getEmail().port());
        mailSender.setUsername(flowlineProperties.getEmail().username());
        mailSender.setPassword(flowlineProperties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");
    }

    String activationEmail = """
            <html>
            <head>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f4f4f4;
                        margin: 0;
                        padding: 20px;
                    }
                    .container {
                        max-width: 600px;
                        margin: 0 auto;
                        background-color: #fff;
                        padding: 20px;
                        border-radius: 5px;
                        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                    }
                    h1 {
                        color: #333;
                    }
                    a {
                        color: #007bff;
                        text-decoration: none;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>${title}</h1>                   
                    <a href="${url}">${clickHere}</a>
                </div>
            </body>
            </html>
            """;

    public void sendActivationEmail(String email, String activationToken) {
        var activationUrl = flowlineProperties.getClient().host() + "/activation/" + activationToken;
        var title = Messages.getMessageForLocale( "flowline.mail.user.created.title", LocaleContextHolder.getLocale());
        var clickHere = Messages.getMessageForLocale("flowline.mail.click.here", LocaleContextHolder.getLocale());
        var mailBody = activationEmail
                .replace("${url}", activationUrl)
                .replace("${title}", title)
                .replace("${clickHere}", clickHere);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"UTF-8");

        try {
            message.setFrom("noreply@flowline.com");
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        this.mailSender.send(mimeMessage);
    }
}
