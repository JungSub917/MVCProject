package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CityLikeDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public CityLikeDAO() {
		try {
	    String driver = "oracle.jdbc.driver.OracleDriver";
	    String url = "jdbc:oracle:thin:@localhost:1521:xe";
	    String id = "stubby";
	    String pw = "pass1234";
	    Class.forName(driver);
	    conn = DriverManager.getConnection(url, id, pw);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void citylikeBtn(int idx, int cityId) {
		
		String sql = "SELECT * FROM city_like WHERE idx = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			
			boolean cityLiked = false;
			
			while(rs.next()) {
				int cityid = rs.getInt("city_id");
				if(cityid == cityId) {
					cityLiked = true;
				}
			}
			
			if(cityLiked) {
				String sql1 = "DELETE FROM city_like WHERE city_id = ? AND idx = ?";
				PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setInt(1, cityId);
				pstmt1.setInt(2, idx);
				pstmt1.executeUpdate();
				pstmt1.close();
				
				String sql2 = "UPDATE city SET like_cnt = like_cnt - 1 WHERE city_id = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, cityId);
				pstmt2.executeUpdate();
				pstmt2.close();
				
			}else {
				String sql3 = "INSERT INTO city_like(city_id, idx) VALUES (?, ?)";
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setInt(1, cityId);
				pstmt3.setInt(2, idx);
				pstmt3.executeUpdate();
				pstmt3.close();
				
				String sql4 = "UPDATE city SET like_cnt = like_cnt + 1 WHERE city_id = ?";
				PreparedStatement pstmt4 = conn.prepareStatement(sql4);
				pstmt4 = conn.prepareStatement(sql4);
				pstmt4.setInt(1, cityId);
				pstmt4.executeUpdate();
				pstmt4.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
      try {
        if (rs != null) rs.close();
        if (pstmt != null)pstmt.close();
        if (conn != null) conn.close();
   
      } catch (Exception e) {
        e.printStackTrace();
    }
	}
	}
}
