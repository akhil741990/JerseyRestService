package com.turvo.vehicle.tracker;

import java.util.LinkedList;

import com.turvo.db.dao.LatLongDao;

public class DeviationDetector {

	private LatLongDao vehicleLocation;
	private int vehicleId;
	private float threshold;
	public void trackDeviction(){
		LatLongDao currectHop,nextHop;
	    LinkedList<LatLongDao> route = null; //GoogleMap.getRoute(source,destination);
		currectHop = route.getFirst();
		int index = 1;
		nextHop = route.get(index);
		while(!vehicleLocation.equals(route.getLast())){
			// vehicleLocation = RestService.getLocation(vehicleId);
			 if(!isVehicleOnLineBetweenThesePoints(currectHop,nextHop)){
				  if( ifDeviatedMoreThanThreshold(currectHop,nextHop)){
					   // send alert to the Tranport Department and send the updated route to the vehicle .
					   // restart the same algo with the updated route
					  System.out.println("Vehicle has deviated from the route");
					   break;
					   
				  }else{
					  if(vehicleLocation.equals(nextHop)){
						 currectHop = nextHop;
						 nextHop = route.get(++index); // add check for size limit of linked list
					  }
				  }
			 }else{
				 System.out.println("Vehicle is on the route");
			 }
		}
	}



	private boolean isVehicleOnLineBetweenThesePoints(LatLongDao currectHop, LatLongDao nextHop){
		// find if the vehicle is on the line between theses points using trigonometric functions
		return true;
	}

	private boolean ifDeviatedMoreThanThreshold(LatLongDao currentHop, LatLongDao nextHop){
	       if(getDistanceBetweenThesePoints(vehicleLocation,currentHop) > threshold || getDistanceBetweenThesePoints(vehicleLocation,nextHop) > threshold ){
		      return true;
		   }
		   return false;
	}

	private Float getDistanceBetweenThesePoints(LatLongDao source, LatLongDao destination){
	    // find distance using trigonometric functions   
		return 0.0f;    
	}
}
