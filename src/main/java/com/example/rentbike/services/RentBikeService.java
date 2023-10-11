package com.example.rentbike.services;


import com.example.rentbike.models.bike.Bike;
import com.example.rentbike.repositories.impl.BikeRepo;

public class RentBikeService extends BaseService {

	/**
	 * get bike with given barcode, if the barcode is invalid in format, does not exist or the bike has already been rented
	 * throw exception
	 * @param barcode
	 * @return Bike or Null
	 * @throws Exception
	 */
    private final BikeRepo bikeRepo;

    public RentBikeService() {
        bikeRepo = new BikeRepo();
    }

    public Bike validateBarcodeBike(String barcode) throws Exception {
        Bike tmp;
        if (!this.validateBarcode(barcode))
            throw new Exception("Invalid Barcode");
        tmp = bikeRepo.getBikeByBarcode(barcode);

        if (tmp == null) throw new Exception("Barcode is not exist");

        if (checkAvailableBike(tmp))
            throw new Exception("Bike has already been rented");
        return tmp;
    }

    public boolean checkAvailableBike(Bike bike) {
        try {
            return bike.getIsRenting();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateBarcode(String barcode) {
        if (barcode == null) return false;
        barcode = barcode.trim();
        if (barcode.isEmpty()) return false;
        for (int i = 0; i < barcode.length(); i++) {
            if (!Character.isLetterOrDigit(barcode.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     * Create content for the order
     * @param barcode
     * @return
     */
    public String getContent(String barcode) {
        return "Pay deposit for renting bike " + barcode;
    }

    /**
     * create new order based on the rented bike
     * create new invoice based on the order
     * @param rented Bike
     * @return
     */

    /**
     * set isRenting of bike
     *
     * @param bike
     * @param state
     */
    public void setRentBike(Bike bike, boolean state) {
        bike.setIsRenting(state);
    }

    public int getDeposit(Bike bike) {
        return (int) (bike.getValue() * 0.4);
    }
}