package com.payment.system.messaging;

import com.payment.system.service.EmailService;
import java.util.Objects;
import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailListener {

    EmailService emailService;

    @Bean
    public Consumer<MailEvent> mailResult() {
        return event -> {
            if (Objects.nonNull(event)) {
                log.info("Mail result consumer is started. Event - {}", event);
                emailService.sendEmail(event);
            }
        };
    }
}

