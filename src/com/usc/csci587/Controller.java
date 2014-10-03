package com.usc.csci587;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import oracle.spatial.geometry.JGeometry.Point;

@Path("/controller")
public class Controller {

	@GET
	@Path("/setcurrentlocation")
	@Produces(MediaType.TEXT_HTML)
	public Response setCurrentLocation(@Context HttpHeaders headers,
			@QueryParam("long") String longitude,
			@QueryParam("lat") String lat, @QueryParam("uid") String uid, @QueryParam("time") String timestamp) {
		UpdateDb update = new UpdateDb();
		update.updateuserTable(longitude, lat, uid,timestamp);
		return Response
				.status(200)
				.entity("Input parameters longitude:" + longitude
						+ ",latitude :" + lat).build();

	}

	@GET
	@Path("/gettrajectory")
	@Produces(MediaType.TEXT_HTML)
	public Response getTrajectory(@QueryParam("uid") String uid) {
		SelectFromDb select = new SelectFromDb();
		ArrayList<Point> points = select.getTrajectory(uid);
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
		for (int i = 0; i < points.size(); i++) {
			JSONObject innerobj = new JSONObject();
			innerobj.put("x", points.get(i).getX());
			innerobj.put("y", points.get(i).getY());
			list.add(innerobj);
		}
		obj.put("points", list);
		return Response.status(200).entity(obj.toJSONString()).build();
	}

}
