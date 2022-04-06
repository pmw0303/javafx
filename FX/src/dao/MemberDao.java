package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Member;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.ArcType;

public class MemberDao {
	
	private Connection con; // DB연동시 사용되는 클래스 : DB연동 클래
	private PreparedStatement ps; // 연결된 DB내 SQL 조직 할 때 사용되는 인터페이스 : DB조작 인터페이스
	private ResultSet rs; // 결과물을 조작하는 인터페이스
	

	public static MemberDao memberDao = new MemberDao(); // DB연동객체
	
	public MemberDao() {

		try {
		// DB연동
			Class.forName("com.mysql.cj.jdbc.Driver");	// 1. DB 드라이버 가져오기
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC",
					"root", "1234"); // 2. DB 주소 연결		
		}catch (Exception e) {
			System.out.println("DB 연동 오류" + e);
		}
	}
	
	public boolean idcheck(String id) {
		try {
		// SQL 작성
		String sql = "select *from member where mid=?";
		// SQL 조작
		ps = con.prepareStatement(sql);
		ps.setString(1, id);
		// SQL 실행
		rs = ps.executeQuery(); // select 실행 -> 결과 존재 -> test
		// SQL 결과
		if(rs.next()) { // 다음 결과물이 존재하면 -> 해당 아이디 존재
			return true; // 해당 아이디 중복
		}
		}catch (Exception e) {
			System.out.println("SQL 오류 " + e);
		}
		
		return false;
	}
	
	// 메소드
		// 회원가입 
	public boolean signup(Member member) {		
		try {
			String sql = "insert into member(mid, mpassword, memail, maddress, mpoint, mcince) values(?,?,?,?,?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getMid());
			ps.setString(2, member.getMpassword());
			ps.setString(3, member.getMemail());
			ps.setString(4, member.getMaddress());
			ps.setInt(5, member.getMpoint());
			ps.setString(6, member.getMcince());
			
			ps.executeUpdate(); // insert 실행
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
		// 로그인 / 인수 : id, pw
	public boolean login(String id, String password) {
		try {
			// sql 작성
			String sql = "select *from member where mid=? and mpassword=?";			
			// sql 조작
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);		
			// sql 실행
			rs = ps.executeQuery();
			// sql 결과
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("SQL 오류 " + e);
		}
		return false;
	}
		// 아이디 찾기 / 인수 : email
	public String findid(String email) {
		try {
			String sql = "select *From member where memail=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString(2);
				return id;
			}
			
		}catch (Exception e) {
			System.out.println("SQL 오류 " + e);
		}
		return null;
	}
		// 비밀번호 찾기 / 인수 : id, email
	public String findpw(String id, String email) {
		try {
			String sql = "select *From member where mid=? and memail=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String password = rs.getString(3);
				return password;
			}
		} catch (Exception e) {
			System.out.println("SQL 오류 " + e);
		}
		return null;
	}
	
	// 아이디로 회원정보 호출
	public Member getmember(String id) {
		try {
		// SQL 작성
			String sql = "select *from member where mid=?";
		// SQL 조작
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
		// SQL 실행
			rs = ps.executeQuery();
		// SQL 결과
			if(rs.next()) {
				Member member = new Member(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7));
				// rs.next() : 결과 내 다음 레코드
				return member;
			}
		} catch (Exception e) {
			System.out.println("SQL 오류 " + e);
		}return null;
	}
	// 회원 탈퇴 [회원번호를 인수로 받아 해당 회원 레코드 삭제]
	public boolean delete(int mnum) {
		try {
			String sql = "delete from member where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeLargeUpdate(); // insert, update, delete 실행
			return true;
		} catch (Exception e) {
			System.out.println("SQL 오류 " + e);
		}
		return false;
	}
	// 회원 수정 [회원번호, 이메일, 주소 인수로 받아서 수정하기]
	public boolean update(int mnum, String email, String address) {
		try {
			String sql = "update from meber set memail=?, maddress=? where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email); ps.setString(2, address); ps.setInt(3, mnum);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("SQL 오류 " + e);
		}
		return false;
	}
	
}
