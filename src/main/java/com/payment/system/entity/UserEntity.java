package com.payment.system.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity{

    String name;
    String email;
    String password;
    String activationId;
    boolean isActive;

    @OneToMany(mappedBy = "user")
    List<UserTokenEntity> tokenUsers;

    @OneToMany(mappedBy = "user")
    List<AccountEntity> accountUsers;
}
