package com.example.rentbike.models.transaction;

import com.example.rentbike.models.BaseEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card extends BaseEntity {
    private String cvvCode;
    private String owner;
    private String cardNumber;
    private String expirationDate;
    private Integer amount;


}
