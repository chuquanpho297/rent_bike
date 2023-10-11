package com.example.rentbike.services;


import com.example.rentbike.models.station.Station;
import com.example.rentbike.repositories.impl.StationRepo;

import java.util.List;

public class HomeService extends BaseService {

	private final StationRepo stationRepo;

    public HomeService() {
        this.stationRepo = new StationRepo();
    }

    public List<Station> getAllStations(){
        return stationRepo.getAllStations();
    }
}
