package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;

public class UserDAO{
	
	public String pub = "localhost";
	public String pri = "192.168.0.2";
	
	public Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbId = "stubby";
		String dbPw = "pass1234";
		
		Class.forName(driver);  
		Connection conn = DriverManager.getConnection(url, dbId, dbPw);
							
		return conn;
	}
	
	public UserDTO getUserDTO(String email, String password) throws Exception{

		UserDTO dto = new UserDTO();
		String sql  = "SELECT * FROM member WHERE email = ? AND pw = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
			 
		if(rs.next()) {
			dto.setIdx(rs.getInt("idx"));
			dto.setEmail(rs.getString("email"));
			dto.setPassword(rs.getString("pw"));  
			dto.setNickName(rs.getString("nick_name"));
		}
		rs.close();
		pstmt.close();
		conn.close();
		return dto;
	}
	
	public UserDTO NaverUserDTO(String email)throws Exception{

		UserDTO dto = new UserDTO();
		String sql  = "SELECT * FROM member WHERE email = ? ";
		Connection conn = getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
	
		ResultSet	rs = pstmt.executeQuery();
			 
		if(rs.next()) {
			dto.setEmail(rs.getString("email")); 
			dto.setNickName(rs.getString("nick_name"));
			dto.setIdx(rs.getInt("idx"));
		}
		rs.close();
		pstmt.close();
		conn.close();
		return dto;
	}
	
	public int naverSignInUp(String name, String email, String nickname, String token)throws Exception{
		
    String sql = "SELECT email, token FROM member WHERE email = ?";
    Connection conn = getConnection();
   
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, email);
    ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
      	String naverEmail = rs.getString("email");
        String naverToken = rs.getString("token");

        if (naverEmail.equals(email)) {
        	// 이미 동일한 이메일을 가진 회원이 존재하는 경우
          if (naverToken.equals(token)) {
          	// 이미 동일한 토큰을 가진 회원인 경우
            return 1; // 로그인 성공
          } else {
          	// 동일한 이메일을 가진 회원이 있지만 토큰이 일치하지 않는 경우
          	sql = "UPDATE member SET token = ? WHERE email = ?";
          	pstmt = conn.prepareStatement(sql);
          	pstmt.setString(1, token);
     	      pstmt.setString(2, email);
     	      pstmt.executeUpdate();
            return 1; // 토큰 불일치
          }
        }
      }else if(!rs.next()) {
	      // 동일한 이메일을 가진 회원이 없는 경우, 회원 가입 처리
	      sql = "INSERT INTO member(idx, name, email, nick_name, status, token) VALUES(seq_idx.nextval, ?, ?, ?, 1, ?)";
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, name);
	      pstmt.setString(2, email);
	      pstmt.setString(3, nickname);
	      pstmt.setString(4, token);
	      pstmt.executeUpdate();
	      return 2; // 회원 가입 및 로그인 성공
      }
    
    pstmt.close();
    rs.close();
    conn.close();
    return -2; // 데이터베이스 오류
	}
	public int login(String email, String password)throws Exception {
		
		Connection conn = getConnection();
		String sql  = "SELECT pw FROM member WHERE email = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			if(rs.getString(1).equals(password))
				return 1; // 로그인 성공
			else
				return 0; // 비빌번호 불일치
		}
		return -1;
	}
	
	public int signupCheck(String email)throws Exception{
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM member WHERE email = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		ResultSet	rs = pstmt.executeQuery();
		if(rs.next()) {
				return 0; // 이미 존재하는 회원
			}else {
				return 1; // 가입가능한 회원 아이디
			}
		}
	
	
	public int singup(String email, String name, String nickname, String pw)throws Exception{
		Connection conn = getConnection();
		String sql = " INSERT INTO member(idx, email, name, nick_name, pw, agree1, agree2, agree3, status)"
							 + " VALUES(seq_idx.nextval,?,?,?,?,'Y','Y','Y',1)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, name);
		pstmt.setString(3, nickname); 
		pstmt.setString(4, pw);
		return pstmt.executeUpdate(); 
		
	}
	
	public void changePw(String email, String idcode, String pass)throws Exception {
		Connection conn = getConnection();
		
		String sql = "UPDATE member set pw = ? WHERE email = ? and idcode = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pass);
		pstmt.setString(2, email);
		pstmt.setString(3, idcode);
		pstmt.executeUpdate();
	}
}


		

