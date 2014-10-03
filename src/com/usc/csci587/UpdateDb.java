package com.usc.csci587;

import java.sql.Connection;
import java.sql.Statement;

public class UpdateDb {
	
	private long timestamp;
	

	public int updateuserTable(String longitude,String lat,String uid,String timestamp){
		if(new Long(timestamp)<this.timestamp)
			return -1;
		try {
			this.timestamp=new Long(timestamp);
			Connection conn=DbUtils.getInstance("jdbc:oracle:thin:@//128.125.163.168:1521/csci585","team1","hp240312");
			System.out.println("Input parameters longitude:"+longitude+ ",latitude :"+lat);
			Statement stmt=conn.createStatement();
			stmt.executeQuery("update users u SET u.user_timestamp=CURRENT_TIMESTAMP, u.location=SDO_GEOMETRY(2001, 8307, SDO_POINT_TYPE(76.3434, 98.2323, NULL), NULL, NULL) WHERE u.user_id=2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
