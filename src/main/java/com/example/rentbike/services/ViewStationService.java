package com.example.rentbike.services;


import com.example.rentbike.models.bike.Bike;
import com.example.rentbike.repositories.impl.BikeRepo;

import java.util.List;

public class ViewStationService extends BaseService {

	private final BikeRepo bikeRepo;

    public ViewStationService() {
        this.bikeRepo = new BikeRepo();
    }

    public List<Bike> getAllBikeAvailable(int stationId){
        return bikeRepo.getAllBikeAvailable(stationId);
    }
}
