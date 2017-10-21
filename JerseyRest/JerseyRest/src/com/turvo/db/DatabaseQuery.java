package com.turvo.db;

public interface DatabaseQuery {
	public static String INSERT_OR_UPDATE_VEHICLE_LOCATION = "INSERT INTO vehicle_location (vehicle_id, latitude, longitude) VALUES(?,?,?) ON DUPLICATE KEY UPDATE "    
																+"latitude = ?, longitude = ?;";

    public static String GET_VEHICLE_LOCATION = "SELECT * from vehicle_location where vehicle_id = ?";
}
