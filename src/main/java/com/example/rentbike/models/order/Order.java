package com.example.rentbike.models.order;

import com.example.rentbike.models.BaseEntity;
import com.example.rentbike.models.bike.Bike;
import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity {
    private Bike bike;
    private Timestamp start;
    private Timestamp end;
    private Integer deposit;
    private Integer totalUpToNow;

    public Order(Bike bike, Timestamp start) {
        this.bike = bike;
        this.deposit = (int) (bike.getValue() * 0.4);
        this.start = start;
        this.totalUpToNow = 15000;
    }

}

