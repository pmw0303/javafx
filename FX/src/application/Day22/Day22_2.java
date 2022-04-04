package application.Day22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.mysql.cj.xdevapi.PreparableStatement;



public class Day22_2 {
	
	// �ʵ�
	private Connection connection; // DB���� ��ü
	
	// ������
	public Day22_2() {
		try {
		// DB����̺� Ŭ���� ȣ��
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC",
				"root", "1234");
		
		System.out.println("DB ���� ����");
		}catch (Exception e) {
			System.out.println("DB ���� ����");
		}
	}
	
	// �޼ҵ�
	public boolean write(String �ۼ���, String ����) {
		try {
		// SQL �ۼ� [DB ���̺� ���� ����]
		String sql = "insert into test(writer, content) values(?, ?)";
		// SQL ����
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, �ۼ���);
		ps.setString(2, ����);
		// SQL ����
		ps.executeUpdate(); // ���� ����
		
		// ������
		return true;
		
		}catch (Exception e) {
			System.out.println("SQL ���� ����" + e);
		}
		// ���н�
		return false;
		
	}
	
	// ������ ȣ��
	public ArrayList<������> get() {
		
		try {
			ArrayList<������> �����͸�� = new ArrayList<>();
			// SQL �ۼ� / ������ ȣ��
			String sql = "select * from test";
			// SQL ����
			PreparedStatement ps = connection.prepareStatement(sql);
			// SQL ����
			ResultSet rs = ps.executeQuery();
			
			// ������� �ϳ��� �ƴϰ� ���� ���� �ݺ����� ����ؼ� ���پ� ���
			while(rs.next()) { // ���� ���� ������
				// ���پ� [���ڵ�] ���� ��üȭ
				������ temp = new ������(
					rs.getInt(1), // �ش� �� [���ڵ�] �� ù��° �ʵ� ��
					rs.getString(2), // �ش� �� [���ڵ�] �� �ι�° �ʵ� ��
					rs.getString(3)); // �ش� �� [���ڵ�] �� ����° �ʵ� ��
				
				�����͸��.add(temp);
			}
			
			// ������ ��ȯ
			return �����͸��;
		
		}catch(Exception e) {
			System.out.println("sql ���� ����");
		}
		
		// ���н�
		return null;
	}

}
