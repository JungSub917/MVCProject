package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.natDTO;
import dto.hotelDTO;

public class hotelDAO {
	public Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbId = "stubby";
		String dbPw = "pass1234";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, dbId, dbPw);

		return conn;
	}

	public ArrayList<natDTO> getHotelSearch() throws Exception {

		ArrayList<natDTO> hotelserchdto = new ArrayList<natDTO>();
		Connection conn = getConnection();

		String sql = " SELECT c.name, n.k_name, c.city_id, n.nat_id FROM city c, nation n"
				+ " WHERE c.nat_id = n.nat_id ORDER BY nat_id";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int natId = rs.getInt("nat_id");
			String natKorname = rs.getString("k_name");
			int cityId = rs.getInt("city_id");
			String cityName = rs.getString("name");
			natDTO dto = new natDTO(natId, natKorname, cityId, cityName);
			hotelserchdto.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return hotelserchdto;
	}

	public ArrayList<hotelDTO> hotelFO(int pageNum, int cityCode, int hotelOrder, int filter3, int filter4, int filter5,
		ArrayList<Integer> filter6) throws Exception {
		ArrayList<hotelDTO> hoteldto = new ArrayList<hotelDTO>();
		Connection conn = getConnection();

		String sql = " SELECT b2.*"
							 + " FROM (SELECT rownum rnum, b1.*"
							 + " FROM (SELECT * FROM hotel WHERE city_id = ? ";

		switch (filter3) {
		case 1:
			sql += "AND price <= 30000000";
			break;
		case 2:
			sql += "AND price <= 300000";
			break;
		case 3:
			sql += "AND price <= 600000";
			break;
		case 4:
			sql += "AND price >= 600000";
			break;
		}

		switch (filter4) {
		case 1:
			sql += "AND user_rating <= 5";
			break;
		case 2:
			sql += "AND user_rating >= 3";
			break;
		case 3:
			sql += "AND user_rating >= 3.5";
			break;
		case 4:
			sql += "AND user_rating >= 4";
			break;
		case 5:
			sql += "AND user_rating >= 4.5";
			break;
		}

		switch (filter5) {
		case 1:
			sql += "AND hotel_rating >= 0";
			break;
		case 2:
			sql += "AND hotel_rating >= 3";
			break;
		case 3:
			sql += "AND hotel_rating >= 4";
			break;
		case 4:
			sql += "AND hotel_rating >= 5";
			break;
		}

		if (filter6.size() > 0) {
			sql += " AND goo IN (";
			for (int i = 0; i < filter6.size(); i++) {
				if (i > 0) {
					sql += ", ";
				}
				sql += filter6.get(i);
			}
			sql += ")";
		} 

		sql += " ORDER BY ";
		switch (hotelOrder) {
		case 1:
			sql += "user_rating DESC";
			break;
		case 2:
			sql += "hotel_rating ASC";
			break;
		case 3:
			sql += "hotel_rating DESC";
			break;
		case 4:
			sql += "price ASC";
			break;
		case 5:
			sql += "price DESC";
			break;
		}
		
		 sql += ") b1) b2 WHERE b2.rnum>= ? AND b2.rnum <= ?";
		  
	  int endNum = pageNum * 12; 
	  int startNum = endNum - 11;
		 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cityCode);
		pstmt.setInt(2, startNum); 
		pstmt.setInt(3, endNum);
		 
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String hotelImg = rs.getString("img");
			String hotelName = rs.getString("name");
			int hotelPrice = rs.getInt("price");
			Double userRating = rs.getDouble("user_rating");
			hotelDTO dto = new hotelDTO(hotelImg, hotelName, hotelPrice, userRating);
			hoteldto.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return hoteldto;
	}
	public int hotelCount(int cityCode, int filter3, int filter4, int filter5, ArrayList<Integer> filter6 ) throws Exception {
		Connection conn = getConnection();
		
		String sql = "SELECT count(*) AS \"cnt\" FROM hotel WHERE city_id = ?";

		switch (filter3) {
		case 1:
			sql += "AND price <= 50000000";
			break;
		case 2:
			sql += "AND price <= 300000";
			break;
		case 3:
			sql += "AND price <= 600000";
			break;
		case 4:
			sql += "AND price >= 600000";
			break;
		}

		switch (filter4) {
		case 1:
			sql += " AND user_rating <= 5";
			break;
		case 2:
			sql += " AND user_rating >= 3";
			break;
		case 3:
			sql += " AND user_rating >= 3.5";
			break;
		case 4:
			sql += " AND user_rating >= 4";
			break;
		case 5:
			sql += " AND user_rating >= 4.5";
			break;
		}

		switch (filter5) {
		case 1:
			sql += " AND hotel_rating >= 0";
			break;
		case 2:
			sql += " AND hotel_rating >= 3";
			break;
		case 3:
			sql += " AND hotel_rating >= 4";
			break;
		case 4:
			sql += "AND hotel_rating >= 5";
			break;
		}

		if (filter6.size() > 0) {
			sql += " AND goo IN (";
			for (int i = 0; i < filter6.size(); i++) {
				if (i > 0) {
					sql += ", ";
				}
				sql += filter6.get(i);
			}
			sql += ")";
		}
		
		int cnt = 0;
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, cityCode);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			cnt = rs.getInt("cnt");
		}
		rs.close();
		pstmt.close();
		conn.close();
		return cnt;
	}
	
	public ArrayList<hotelDTO> hotelmapinfo(int cityCode)throws Exception {
		ArrayList<hotelDTO> dto = new ArrayList<hotelDTO>();
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM hotel WHERE city_id = ? ORDER BY user_rating DESC";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cityCode);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String img = rs.getString("img");
			double userRating = rs.getDouble("user_rating");
			int price = rs.getInt("price");
			double lat = rs.getDouble("lat");
			double lon = rs.getDouble("lon");
			String name = rs.getString("name");
			hotelDTO hotelmapdto = new hotelDTO(img, userRating, price, lat, lon, name);
			dto.add(hotelmapdto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return dto;
	}
}
