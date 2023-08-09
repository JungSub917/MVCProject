package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.natDTO;

public class natDAO{
	
	public Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbId = "stubby";
		String dbPw = "pass1234";
		
		Class.forName(driver);  
		Connection conn = DriverManager.getConnection(url, dbId, dbPw);
							
		return conn;
	}

	public ArrayList<natDTO> getNatCityDto() throws Exception{
		ArrayList<natDTO> maindto = new ArrayList<natDTO>();
	
		Connection conn = getConnection();

		String sql = " SELECT c.name, n.k_name, c.city_id, n.nat_id FROM city c, nation n"
							 + " WHERE c.nat_id = n.nat_id ORDER BY c.name";
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int natId = rs.getInt("nat_id");
			String natKorname = rs.getString("k_name");
			int cityId = rs.getInt("city_id");
			String cityName = rs.getString("name");
			natDTO dto = new natDTO(natId, natKorname, cityId, cityName);
			maindto.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return maindto;
	}
	
	public ArrayList<natDTO> natList() throws Exception{
		Connection conn = getConnection();
		String sql = "SELECT nat_id, k_name,e_name,description,currency, volt, visa, timedi, cities FROM nation";
		ArrayList<natDTO> ndto = new ArrayList<natDTO>();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String cities = rs.getString("cities");
			String[] arrCity = cities.split("___");
			int natId = rs.getInt("nat_id");
			String kname = rs.getString("k_name");
			String ename = rs.getString("e_name");
			String des = rs.getString("description");
			String cur = rs.getString("currency");
			String volt = rs.getString("volt");
			String visa = rs.getString("visa");
			String timedi = rs.getString("timedi");
			natDTO dto = new natDTO(natId,kname,ename,des,cur,volt,visa,timedi,arrCity);
			ndto.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return ndto;
	}
	public ArrayList<natDTO> natOrder(int option) throws Exception {
		Connection conn = getConnection();
		ArrayList<natDTO> natList = new ArrayList<natDTO>();
		String sql = "SELECT * FROM nation ORDER BY ";
		switch(option) {
		case 1:
			sql += "nat_id ASC"; // 추천 
			break;
		case 2:
			sql += "k_name ASC"; 	// 올림차순 z - a
			break;
		case 3:
			sql += "k_name DESC";  // 내림차순
			break;
		}
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int natId = rs.getInt("nat_id");
			String natKorname = rs.getString("k_name");
			String natEngname = rs.getString("e_name");
			natDTO toto = new natDTO(natId, natKorname, natEngname);
			natList.add(toto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return natList;
	}
		public ArrayList<natDTO> natSearch(String searchNat) throws Exception {
			Connection conn = getConnection();
			ArrayList<natDTO> searchnat = new ArrayList<natDTO>();
			String sql = "SELECT * FROM nation WHERE k_name LIKE ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+searchNat+"%");
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int natId = rs.getInt("nat_id");
					String natKorname = rs.getString("k_name");
					String natEngname = rs.getString("e_name");
					natDTO toto = new natDTO(natId, natKorname, natEngname);
					searchnat.add(toto);
				}
			rs.close();
			pstmt.close();
			conn.close();
			return searchnat;
		}
}