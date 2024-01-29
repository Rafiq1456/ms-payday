package com.payment.system.service;

import com.payment.system.messaging.MailEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
     MailSender mailSender;

     MailMessage mailMessage;

    public void sendEmail(MailEvent mailEvent) {
        mailMessage.setSubject("express Bank");
        mailMessage.setTo(mailEvent.getAddress());
        mailMessage.setText(mailEvent.getMessage());
        try {
            mailSender.send((SimpleMailMessage) mailMessage);
            log.info("Email sending complete.");
        } catch (Exception e) {
            log.error("Email sending was failed ",e);
        }
    }
}
