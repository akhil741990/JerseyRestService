package com.turvo.db;

import java.sql.PreparedStatement;
import java.util.concurrent.Callable;

public class LocationUpdatorJob implements Callable<Integer>{

	private PreparedStatement ps;
	LocationUpdatorJob(PreparedStatement ps){
		this.ps = ps;
	}
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return ps.executeUpdate();
	}

}
