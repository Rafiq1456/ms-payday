package com.payment.system.messaging;

import java.util.LinkedList;
import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailPublisher {

    final LinkedList<MailEvent> mailEvents = new LinkedList<>();

    public void sendEvent(MailEvent mailEvent) {
        log.info("Mail event was published. Event - {}", mailEvent);
        mailEvents.push(mailEvent);
    }

    @Bean
    public Supplier<MailEvent> sendMail() {
        return () -> {
            if (mailEvents.peek() != null) {
                var mailEvent = mailEvents.peek();
                mailEvents.poll();
                log.info("Mail event was published. Event - {}", mailEvent);
                return mailEvent;
            }
            return null;
        };
    }

}

