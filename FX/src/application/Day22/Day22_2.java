package application.Day22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.mysql.cj.xdevapi.PreparableStatement;



public class Day22_2 {
	
	// 필드
	private Connection connection; // DB연동 객체
	
	// 생성자
	public Day22_2() {
		try {
		// DB드라이브 클래스 호출
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC",
				"root", "1234");
		
		System.out.println("DB 연동 성공");
		}catch (Exception e) {
			System.out.println("DB 연동 실패");
		}
	}
	
	// 메소드
	public boolean write(String 작성자, String 내용) {
		try {
		// SQL 작성 [DB 테이블 내에 삽입]
		String sql = "insert into test(writer, content) values(?, ?)";
		// SQL 설정
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, 작성자);
		ps.setString(2, 내용);
		// SQL 실행
		ps.executeUpdate(); // 삽입 실행
		
		// 성공시
		return true;
		
		}catch (Exception e) {
			System.out.println("SQL 연결 실패" + e);
		}
		// 실패시
		return false;
		
	}
	
	// 데이터 호출
	public ArrayList<데이터> get() {
		
		try {
			ArrayList<데이터> 데이터목록 = new ArrayList<>();
			// SQL 작성 / 데이터 호출
			String sql = "select * from test";
			// SQL 조작
			PreparedStatement ps = connection.prepareStatement(sql);
			// SQL 실행
			ResultSet rs = ps.executeQuery();
			
			// 결과물이 하나가 아니고 여러 개라서 반복문을 사용해서 한줄씩 출력
			while(rs.next()) { // 다음 줄이 있으면
				// 한줄씩 [레코드] 단위 객체화
				데이터 temp = new 데이터(
					rs.getInt(1), // 해당 줄 [레코드] 의 첫번째 필드 값
					rs.getString(2), // 해당 줄 [레코드] 의 두번째 필드 값
					rs.getString(3)); // 해당 줄 [레코드] 의 세번째 필드 값
				
				데이터목록.add(temp);
			}
			
			// 성공시 반환
			return 데이터목록;
		
		}catch(Exception e) {
			System.out.println("sql 연결 실패");
		}
		
		// 실패시
		return null;
	}

}
