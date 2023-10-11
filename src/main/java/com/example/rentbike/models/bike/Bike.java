package com.example.rentbike.models.bike;

import com.example.rentbike.models.BaseEntity;
import com.example.rentbike.models.station.Station;
import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bike extends BaseEntity {
    private String type;
    private String licensePlate;
    private Boolean isRenting;
    private Integer numPedal;
    private Integer numSaddle;
    private Integer numRearSeat;
    private String barcode;
    private Integer value;
    private Double coefficient;
    private String urlImage;
    private Integer batteryPercentage;
    private Integer remainingTime;
    private Station station;
}
