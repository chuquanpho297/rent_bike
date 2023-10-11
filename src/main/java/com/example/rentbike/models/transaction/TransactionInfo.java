package com.example.rentbike.models.transaction;

import com.example.rentbike.models.BaseEntity;
import com.example.rentbike.models.order.Order;
import lombok.*;

import java.sql.Timestamp;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionInfo extends BaseEntity {
    private String errorCode;
    private Card card;
    private Order order;
    private String transactionContent;
    private Integer amount;
    private String command;
    private Timestamp createdAt;

    public TransactionInfo(String errorCode) {
        this.errorCode = errorCode;
    }

}
