package com.example.rentbike.services;

import com.example.rentbike.models.station.Station;
import com.example.rentbike.repositories.impl.StationRepo;

import java.sql.SQLException;
import java.util.List;

/**
 * This class controls the flow of events in select dock to return bike screen
 */
public class SelectDockToReturnBikeService extends BaseService {
	/**
	 * Get all stations in DB that has empty dock to display
	 * @return List[Station]
	 * @throws SQLException
	 */
	private final StationRepo stationRepo;

	public SelectDockToReturnBikeService() {
		this.stationRepo = new StationRepo();
	}


	public List<Station> getStationHasEmptyDock() throws SQLException {
//        return new Station().getStationHasEmptyDock();
		return stationRepo.getStationHasEmptyDock();
    }
}
