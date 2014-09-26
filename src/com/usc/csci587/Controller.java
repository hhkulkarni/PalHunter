package com.usc.csci587;

import java.sql.Connection;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/controller")
public class Controller {
	
	@GET
	@Path("/setcurrentlocation")
	@Produces(MediaType.TEXT_HTML)
	public Response setCurrentLocation(
    		@Context HttpHeaders headers,
    		@QueryParam("long") String longitude, @QueryParam("lat") String lat, @QueryParam("uid") String uid)
	{
		UpdateDb update =new UpdateDb();
		update.updateuserTable(longitude,lat,uid);
		/*try {
			Connection conn=DbUtils.getInstance("jdbc:oracle:thin:@//128.125.163.168:1521/csci585","team1","hp240312");
			System.out.println("Input parameters longitude:"+longitude+ ",latitude :"+lat);
			Statement stmt=conn.createStatement();
			stmt.executeQuery("update users u SET u.user_timestamp=CURRENT_TIMESTAMP, u.location=SDO_GEOMETRY(2001, 8307, SDO_POINT_TYPE(76.3434, 98.2323, NULL), NULL, NULL) WHERE u.user_id=2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return Response.status(200).entity("Input parameters longitude:"+longitude+ ",latitude :"+lat).build();
		
	}

}
