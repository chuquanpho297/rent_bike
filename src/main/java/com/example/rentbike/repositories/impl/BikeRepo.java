package com.example.rentbike.repositories.impl;

import com.example.rentbike.models.bike.Bike;
import com.example.rentbike.models.station.Station;
import com.example.rentbike.utils.mappers.impl.BikeMapper;

import java.util.List;

public class BikeRepo extends GenericRepo<Bike> {
    public Bike getBikeById(int id) {
        return findOne("SELECT * FROM bikes join stations using(stationId) where bikeId = ?", new BikeMapper(), id);
    }

    public Bike getBikeByBarcode(String barcode) {
        return findOne("SELECT * FROM bikes  join  stations using(stationId) where barcode= ?", new BikeMapper(), barcode);
    }

    public List<Bike> getAllBike() {
        return query("SELECT * FROM bikes join  stations using(stationId)", new BikeMapper());
    }

    public List<Bike> getAllBikeAvailable(int stationId) {
        List<Bike> bikeList = query("select * from bikes where isRenting = ? and stationId = ?", new BikeMapper(false), 0, stationId);
        System.out.println(bikeList.toString());
        return bikeList;

    }


//    public boolean getIsRenting(int id) {
//        Bike bike = findOne("SELECT * FROM bikes  where bikeId = ?", new BikeMapper(), id);
//        return bike.getIsRenting();
//    }

    public void updateStationId(int bikeID, int stationID) {
        update("update bikes set stationId = ? where bikeId = ?", stationID, bikeID);
    }

    public void updateQtyDB(boolean isRent, Bike currentBike) {
        Station currentStation = currentBike.getStation();

        int stationId = currentStation.getId();
        int bikeId = currentBike.getId();

        int numAvailableBike = currentStation.getNumAvailableBike();
        int numEmptyDockPoint = currentStation.getNumEmptyDockPoint();

        if (isRent) {
            numEmptyDockPoint++;
            numAvailableBike--;
        } else {
            numEmptyDockPoint--;
            numAvailableBike++;
        }
        update("update stations set numEmptyDockPoint = ?, numAvailableBike = ? where stationId = ?", numEmptyDockPoint, numAvailableBike, stationId);
        update("update bikes set isRenting = ? where bikeId = ?", isRent, bikeId);
    }

    public void updateIsRentingById(boolean isRenting, Integer bikeId){
        update("update bikes set isRenting = ? where bikeId = ?", isRenting, bikeId);
    }

}
