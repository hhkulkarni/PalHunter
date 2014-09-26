package com.usc.csci587;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import oracle.spatial.geometry.JGeometry;
import oracle.spatial.geometry.JGeometry.Point;
import oracle.sql.STRUCT;

public class SelectFromDb {
	
	private Connection conn;
	
	
	public SelectFromDb() {
		super();
		try {
			conn=DbUtils.getInstance("jdbc:oracle:thin:@//128.125.163.168:1521/csci585","team1","hp240312");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public ArrayList<JGeometry.Point> getTrajectory(String uid){
		ArrayList<JGeometry.Point> latlong=new ArrayList<JGeometry.Point>();
		try {
			
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select t.location from trajectories t where t.user=" + uid);

			STRUCT st=(STRUCT) rs.getObject("location");
			JGeometry geom=JGeometry.load(st);
			double[] temp=geom.getOrdinatesArray();
			for(int i=0;i<temp.length;i+=2){
				latlong.add(new Point(temp[i], temp[i+1])); // gtrh
			}
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		finally{
			org.apache.commons.dbutils.DbUtils.closeQuietly(conn);
		}
		return latlong;
	}
}
