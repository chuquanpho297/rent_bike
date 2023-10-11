package com.example.rentbike.utils.mappers.impl;

import com.example.rentbike.models.station.Station;
import com.example.rentbike.utils.mappers.IResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StationMapper implements IResultSetMapper<Station> {
    @Override
    public Station mapRow(ResultSet res) throws SQLException {
        Station station = new Station();
        station.setId(res.getInt("stationId"));
        station.setName(res.getString("name"));
        station.setArea(res.getDouble("area"));
        station.setAddress(res.getString("address"));
        station.setNumEmptyDockPoint(res.getInt("numEmptyDockPoint"));
        station.setNumAvailableBike(res.getInt("numAvailableBike"));
        return station;
    }
}
