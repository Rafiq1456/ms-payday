package com.payment.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockEntity extends BaseEntity{
    String companyName;
    String content;
    String symbol;
    Long price;
}
