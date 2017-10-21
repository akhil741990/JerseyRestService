package com.turvo.db;

import com.turvo.db.dao.LatLongDao;

public interface Database {
	public void init();
	public int insertorUpdateVehicleLocation(int vehicleId, float lattitude, float longitude);
	public LatLongDao getVehicleLocation(int vehicleId);
}
