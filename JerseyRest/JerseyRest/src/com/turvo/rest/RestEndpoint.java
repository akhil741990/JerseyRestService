package com.turvo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.turvo.db.dao.LatLongDao;


@Path("vehicle")
public class RestEndpoint {

	
	@GET
	@Path("location/get/{id}")
	public Response getVehicleLocation(@PathParam("id")  String vehicleId){
		LatLongDao vehicleLocation = ApplicationContext.getDatabase().getVehicleLocation(Integer.parseInt(vehicleId));
		if(vehicleLocation != null){
			return Response.status(200).entity(String.format("(lattitude = %s, longitude = %s)", vehicleLocation.getLattitude(),vehicleLocation.getLongitide())).build();
		}else{
			return Response.status(200).entity("Vechile Location not found").build();
		}
		
	}
	
	@POST
	@Path("location/set/{id}")
	@Consumes("application/x-www-form-urlencoded")
	public Response setVehicleLocation(@PathParam("id")  String vehicleId, @FormParam("lattitude") String lattitude, @FormParam("longitude") String longitude){
		int rowsUpdated = ApplicationContext.getDatabase().insertorUpdateVehicleLocation(Integer.parseInt(vehicleId), Float.parseFloat(lattitude), Float.parseFloat(longitude));
		if(rowsUpdated != 0){
			return Response.status(200).entity("vehicle location updated successfully").build();
		}else{
			return Response.status(200).entity("Failure to update vehicle location").build();
		}
		
	}
}
