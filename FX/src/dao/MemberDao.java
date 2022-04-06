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
	
	private Connection con; // DB������ ���Ǵ� Ŭ���� : DB���� Ŭ��
	private PreparedStatement ps; // ����� DB�� SQL ���� �� �� ���Ǵ� �������̽� : DB���� �������̽�
	private ResultSet rs; // ������� �����ϴ� �������̽�
	

	public static MemberDao memberDao = new MemberDao(); // DB������ü
	
	public MemberDao() {

		try {
		// DB����
			Class.forName("com.mysql.cj.jdbc.Driver");	// 1. DB ����̹� ��������
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC",
					"root", "1234"); // 2. DB �ּ� ����		
		}catch (Exception e) {
			System.out.println("DB ���� ����" + e);
		}
	}
	
	public boolean idcheck(String id) {
		try {
		// SQL �ۼ�
		String sql = "select *from member where mid=?";
		// SQL ����
		ps = con.prepareStatement(sql);
		ps.setString(1, id);
		// SQL ����
		rs = ps.executeQuery(); // select ���� -> ��� ���� -> test
		// SQL ���
		if(rs.next()) { // ���� ������� �����ϸ� -> �ش� ���̵� ����
			return true; // �ش� ���̵� �ߺ�
		}
		}catch (Exception e) {
			System.out.println("SQL ���� " + e);
		}
		
		return false;
	}
	
	// �޼ҵ�
		// ȸ������ 
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
			
			ps.executeUpdate(); // insert ����
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
		// �α��� / �μ� : id, pw
	public boolean login(String id, String password) {
		try {
			// sql �ۼ�
			String sql = "select *from member where mid=? and mpassword=?";			
			// sql ����
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);		
			// sql ����
			rs = ps.executeQuery();
			// sql ���
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("SQL ���� " + e);
		}
		return false;
	}
		// ���̵� ã�� / �μ� : email
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
			System.out.println("SQL ���� " + e);
		}
		return null;
	}
		// ��й�ȣ ã�� / �μ� : id, email
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
			System.out.println("SQL ���� " + e);
		}
		return null;
	}
	
	// ���̵�� ȸ������ ȣ��
	public Member getmember(String id) {
		try {
		// SQL �ۼ�
			String sql = "select *from member where mid=?";
		// SQL ����
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
		// SQL ����
			rs = ps.executeQuery();
		// SQL ���
			if(rs.next()) {
				Member member = new Member(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7));
				// rs.next() : ��� �� ���� ���ڵ�
				return member;
			}
		} catch (Exception e) {
			System.out.println("SQL ���� " + e);
		}return null;
	}
	// ȸ�� Ż�� [ȸ����ȣ�� �μ��� �޾� �ش� ȸ�� ���ڵ� ����]
	public boolean delete(int mnum) {
		try {
			String sql = "delete from member where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeLargeUpdate(); // insert, update, delete ����
			return true;
		} catch (Exception e) {
			System.out.println("SQL ���� " + e);
		}
		return false;
	}
	// ȸ�� ���� [ȸ����ȣ, �̸���, �ּ� �μ��� �޾Ƽ� �����ϱ�]
	public boolean update(int mnum, String email, String address) {
		try {
			String sql = "update from meber set memail=?, maddress=? where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email); ps.setString(2, address); ps.setInt(3, mnum);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("SQL ���� " + e);
		}
		return false;
	}
	
}
