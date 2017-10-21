package com.turvo.db.dao;

public class LatLongDao {
	private float lattitude;
	private float longitide;
	
	public LatLongDao(float lattitude, float longitude) {
		this.lattitude = lattitude;
		this.longitide = longitude;
	}
	
	public float getLattitude() {
		return lattitude;
	}
	public void setLattitude(float lattitude) {
		this.lattitude = lattitude;
	}
	public float getLongitide() {
		return longitide;
	}
	public void setLongitide(float longitide) {
		this.longitide = longitide;
	}
	
	public boolean equals(Object location){
		if(location instanceof LatLongDao){
			LatLongDao loc = (LatLongDao)location;
			if(this.lattitude == loc.getLattitude() && this.longitide == loc.getLongitide()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
