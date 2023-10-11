package com.example.rentbike.repositories.impl;

import com.example.rentbike.models.station.Station;
import com.example.rentbike.utils.mappers.impl.StationMapper;

import java.util.List;

public class StationRepo extends GenericRepo<Station> {

    public List<Station> getAllStations() {
        return findAll("stations", new StationMapper());
    }

    public List<Station> getStationHasEmptyDock() {
        return query("select * from stations where numEmptyDockPoint <= 0", new StationMapper());
    }

    public void updateStationById(int numEmptyDockPoint, int numAvailableBike, int stationId) {
        update("update stations set numEmptyDockPoint = ?, numAvailableBike = ? where stationId = ?", numEmptyDockPoint, numAvailableBike, stationId);
    }

}
