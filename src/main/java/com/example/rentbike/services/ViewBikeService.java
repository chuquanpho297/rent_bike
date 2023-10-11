package com.example.rentbike.services;

import com.example.rentbike.models.bike.Bike;
import com.example.rentbike.repositories.impl.BikeRepo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ViewBikeService extends BaseService {

    private final BikeRepo bikeRepo;

    public ViewBikeService() {
        bikeRepo = new BikeRepo();
    }

    public HashMap<String,Integer> counting(int amount) {
        int hour = amount / 3600;
        int minute = (amount - hour * 3600) / 60;
        int second = amount - hour * 3600 - minute * 60;
        if (second < 59) second += 1;
        else {
            second = 0;
            if (minute < 59) minute += 1;
            else {
                hour += 1;
                minute = 0;
            }
        }
        HashMap<String, Integer> time = new HashMap<>();
        time.put("hour", hour);
        time.put("minute", minute);
        time.put("second", second);
        time.put("newAmount", amount + 1);
        return time;
    }

    public int calculateAmountSeconds(Timestamp start) {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        long timeDifferenceMillis = timestamp.getTime() - start.getTime();
        return (int) (timeDifferenceMillis / 1000);
    }

    public Bike setBike(int id, String type){
        return bikeRepo.getBikeById(id);
//            if (type.equals("Standard electric bike")) {
//                return new StandardElectricBike().getBikeById(id);
//            } else if (type.equals("Standard bike")) {
//                return new StandardBike().getBikeById(id);
//            } else if (type.equals("Twin bike")) {
//                return new TwinBike().getBikeById(id);
//            } else if (type.equals("Electric twin bike")) {
//                return new TwinElectricBike().getBikeById(id);
//            }
//
//        return null;
    }

    public boolean bikeIsRenting(int id) {
        Bike bike = bikeRepo.getBikeById(id);
        return bike.getIsRenting();
    }
}
