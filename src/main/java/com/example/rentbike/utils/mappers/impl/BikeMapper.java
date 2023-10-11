package com.example.rentbike.utils.mappers.impl;

import com.example.rentbike.models.bike.Bike;
import com.example.rentbike.models.station.Station;
import com.example.rentbike.utils.mappers.IResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BikeMapper implements IResultSetMapper<Bike> {

    private boolean isLoadStation = true;

    public BikeMapper(boolean isLoadStation) {
        this.isLoadStation = isLoadStation;
    }

    public BikeMapper() {

    }

    @Override
    public Bike mapRow(ResultSet res) throws SQLException {
        Bike bike = new Bike();
        bike.setId(res.getInt("bikeId"));
        bike.setLicensePlate(res.getString("licensePlate"));
        bike.setNumRearSeat(res.getInt("numRearSeat"));
        bike.setNumPedal(res.getInt("numPedal"));
        bike.setValue(res.getInt("value"));
        bike.setCoefficient(res.getDouble("coefficient"));
        bike.setUrlImage(res.getString("urlImage"));
        bike.setNumSaddle(res.getInt("numSaddle"));
        bike.setBarcode(res.getString("barcode"));
        bike.setIsRenting(res.getBoolean("isRenting"));
        bike.setType(res.getString("type"));
        bike.setRemainingTime((Integer) checkNull(res.getInt("remainingTime"), res));
        bike.setBatteryPercentage((Integer) checkNull(res.getInt("batteryPercentage"), res));
        if (isLoadStation) {
            bike.setStation(new StationMapper().mapRow(res));
        } else {
            Station station = new Station();
            station.setId(res.getInt("stationId"));
            bike.setStation(station);
        }

        return bike;
    }

    private Object checkNull(Object value, ResultSet res) throws SQLException {
        if (res.wasNull()) {
            return null;
        } else return value;
    }
}
