package com.example.rentbike.models.station;

import com.example.rentbike.models.BaseEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Station extends BaseEntity {
    private String name;
    private Integer numAvailableBike;
    private Double area;
    private String address;
    private Integer numEmptyDockPoint;
    public Station(int x) {
        this.numEmptyDockPoint = x;
    }

}

