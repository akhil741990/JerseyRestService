package com.turvo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Future;

import org.apache.commons.dbcp.BasicDataSource;

import com.turvo.db.dao.LatLongDao;

public class MySqlDatabase implements Database{
	
	
	private static MySqlDatabase instance = new MySqlDatabase();
	private BasicDataSource dataSource;
	private DBJobExecutor dbJobExecutor;
	protected MySqlDatabase() {
		
	}
	// instance is initialized at at Class Load,
	// so no need to gaurd instance from the race of initialization from multiple threads 
	public static MySqlDatabase getInstance(){
		return instance;
	}
	
	public void init(){
		
		this.dataSource = new BasicDataSource();
		
		//TODO :  makes these values configurable via INI/ Properties file
		this.dataSource.setUrl("jdbc:mysql://localhost:3306/turvo");
		this.dataSource.setUsername("root");
		this.dataSource.setPassword("password");
		this.dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		this.dataSource.setInitialSize(3);
		this.dataSource.setMaxActive(10);
		this.dbJobExecutor = new DBJobExecutor();
	}
	
	private Connection getConnection() throws Exception {
		
			return this.dataSource.getConnection();

		
	}

	@Override
	public int insertorUpdateVehicleLocation(int vehicleId, float lattitude, float longitude) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = DatabaseOps.createPreparedStatement(con,DatabaseQuery.INSERT_OR_UPDATE_VEHICLE_LOCATION, new Object[]{vehicleId,lattitude,longitude,lattitude,longitude});
			LocationUpdatorJob dbJob = new LocationUpdatorJob(ps);
			Future<Integer> rowsAffected = this.dbJobExecutor.submit(dbJob);
			return rowsAffected.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally {
			DatabaseOps.closeConnection(con, ps);
		}
	}
	@Override
	public LatLongDao getVehicleLocation(int vehicleId) {
		Connection con = null;
		PreparedStatement ps = null;
		LatLongDao location = null;
		ResultSet vehicleLocation = null;
		try {
			con = getConnection();
			ps = DatabaseOps.createPreparedStatement(con,DatabaseQuery.GET_VEHICLE_LOCATION, new Object[]{vehicleId});
			vehicleLocation = ps.executeQuery();
			if(vehicleLocation.next()){
				location = new LatLongDao(vehicleLocation.getFloat(2),vehicleLocation.getFloat(3));
			}
			return location;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return location;
		}finally {
			DatabaseOps.closeConnection(con, ps,vehicleLocation);
		}
	}
}
