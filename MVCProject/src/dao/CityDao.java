package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.CityDto;

public class CityDao {
	public Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbId = "stubby";
		String dbPw = "pass1234";
		
		Class.forName(driver);  
		Connection conn = DriverManager.getConnection(url, dbId, dbPw);
							
		return conn;
	}
	public ArrayList<CityDto> getCityListDto() throws Exception{
	Connection conn = getConnection();
		String sql = "SELECT * FROM city";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<CityDto> CityList = new ArrayList<CityDto>();
		
		while(rs.next()) {
			int Cityid=rs.getInt("city_id");
			int rank=rs.getInt("rank");
			double lat=rs.getDouble("lat");
			double lon=rs.getDouble("lon");
			String name=rs.getString("name");
			String why=rs.getString("why");
			String tip=rs.getString("tip");
			String sum=rs.getString("sum");
			CityDto city=new CityDto(Cityid,rank,lat,lon,name,why,tip,sum);
			CityList.add(city);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return CityList;
	}

	public ArrayList<CityDto> getCityLikeDto(int num) throws Exception{
		Connection conn = getConnection();
		String sql = "SELECT *"
				+" FROM city_like cl, city c"
				+" WHERE cl.city_id = c.city_id AND idx=?"
				+" ORDER BY lno";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<CityDto> CityLike = new ArrayList<CityDto>();
		while(rs.next()) {
			int Cityid=rs.getInt("city_id");
			int rank=rs.getInt("rank");
			double lat=rs.getDouble("lat");
			double lon=rs.getDouble("lon");
			String name=rs.getString("name");
			String why=rs.getString("why");
			String tip=rs.getString("tip");
			String sum=rs.getString("sum");
			CityDto city=new CityDto(Cityid,rank,lat,lon,name,why,tip,sum);
			CityLike.add(city);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return CityLike;
	}
	public int setMyPlanDao(int pcnt, int idx, String start, String end) throws Exception {
		Connection conn = getConnection();
		String sql = "INSERT INTO my_plan(pnum,p_cnt,idx,p_start,p_end)"
				+ " VALUES(SEQ_PNUM.nextval,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pcnt);
		pstmt.setInt(2, idx);
		pstmt.setString(3,start);
		pstmt.setString(4,end);
		pstmt.executeUpdate();
		int max = 0;
		sql = "SELECT MAX(pnum) AS \"max\" FROM my_plan";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			max = rs.getInt("max");
		}
		rs.close();
		pstmt.close();
		conn.close();
		return max;
	} 
	public int getPlanCntDao(int max) throws Exception{
		Connection conn = getConnection();
		String sql = "SELECT * FROM my_plan WHERE pnum=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, max);
		ResultSet rs = pstmt.executeQuery();
		int pcnt = rs.getInt("p_cnt");
		
		rs.close();
		pstmt.close();
		conn.close();
		return pcnt;
	}
	public void setPlantripDao(int cityid, int planday, int pno, int order) throws Exception{
		Connection conn = getConnection();
		String sql = "INSERT INTO plan_trip(city_id,plan_day,pno,plan_order)"
				+ " VALUES(?,?,?,?)";
		// cityid,!idx,plan_day,!pnum,i
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cityid);
		pstmt.setInt(2, planday);
		pstmt.setInt(3, pno);
		pstmt.setInt(4, order);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	public void deleteMyPlanDao(int pnum) throws Exception{
		Connection conn = getConnection();
		String sql = "DELETE FROM my_plan WHERE pnum=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pnum);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	public void deletePlanTripDao(int pno) throws Exception{
		Connection conn = getConnection();
		String sql = "DELETE FROM plan_trip WHERE pno=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pno);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
}





