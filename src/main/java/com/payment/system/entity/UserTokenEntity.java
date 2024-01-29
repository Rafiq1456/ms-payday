package com.payment.system.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_token")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserTokenEntity {

    @Id
    String token;
    @CreationTimestamp
    LocalDateTime date;
    @ManyToOne
    UserEntity user;
}

