package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Board;
import dto.Reply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao {
	
	private Connection conn; // DB 연결 클래스
	private PreparedStatement ps; // 연결된 DB 조작 인터페이스
	private ResultSet rs; // SQL 결과 레코드를 가져오는 인터페이스
	
	public static BoardDao boardDao = new BoardDao();
	
	public BoardDao() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC"
					,"root","1234");
		} catch (Exception e) {
			
		}
	}
	// 글 쓰기
	public boolean write(Board board) {
		try {
			String sql = "insert into board(btitle, bcontent, bwrite) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getBtitle());
			ps.setString(2, board.getBcontent());
			ps.setString(3, board.getBwrite());
			
			ps.executeUpdate(); // insert, update, delete <--> ps.executeQuery
			return true;
			
		} catch (Exception e) {
			System.out.println("SQL 오류 " + e);
		}
		
		return false;
		}
	// 글 호출 (ObservableList 는 fx에서 만듬)
	public ObservableList<Board> list() {
		// 리스트 선언 
		ObservableList<Board> boardlist = FXCollections.observableArrayList();
		try {
		// SQL 작성
			// 정렬
				// order by 필드명 asc / desc [오름차순 / 내림차순]
			String sql = "select *from board order by bnum desc";
		// SQL 조작
			ps = conn.prepareStatement(sql);
		// SQL 실행
			rs = ps.executeQuery(); // select
		// SQL 결과
			// rs : 검색 결과의 레코드를 하나씩 순회 [가져오기 = next()]
				// rs.next : 검색 결과가 다음 레코드
				// rs.get자료형 (필드 순서) : 해당 필드의 데이터 가져오기
			while(rs.next()) {
				// 객체화
				Board board = new Board(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getInt(6));
				// 해당 객체를 리스트에 넣기
				boardlist.add(board);
				
			}
			// 성공 시 리스트 반환
			return boardlist;
		} catch (Exception e) {
			System.out.println("SQL 오류 " + e);
		}
		
		
		return null;
		}
	
	public boolean delete( int bnum ) { 
		try {
		// SQL 작성
		String sql = "delete from board where bnum=?";
		// SQL 조작
		ps = conn.prepareStatement(sql);
		ps.setInt(1, bnum);
		// SQL 실행
		ps.executeUpdate();
		// SQL 결과
		return true;
		}catch(Exception e ) { System.out.println( "[SQL 오류]"+e  ); }
		return false; 
	}
	
	public boolean update(int bnum, String title, String content) {
		
		try {
			// SQL 작성
			String sql = "update board set btitle=?, bcontent=? where bnum=?";
			// SQL 조작
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, bnum);
			// SQL 실행
			ps.executeUpdate();
			// SQL 결과
			return true;
		} catch (Exception e) {
			System.out.println( "[SQL 오류]"+e  );
		}	
		return false;
	}
	
	// 댓글 작성 메소드
	public boolean rwirte(Reply reply) {
		
		try {
			String sql = "insert into reply(rcontent, rwrite, bnum) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, reply.getRcontent());
			ps.setString(2, reply.getRwrite());
			ps.setInt(3, reply.getBnum());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println( "[SQL 오류]"+e  );
		}
	
		return false;
	}
	
	// 댓글 호출 메소드 [해당 게시물의 댓글만 출력]
	public ObservableList<Reply> replylist(int bnum){
		
		ObservableList<Reply> replylist = FXCollections.observableArrayList();
		
		try {
			String sql = "select * from reply where bnum =? order by rnum desc";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bnum);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reply reply = new Reply(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5));
				replylist.add(reply);
			}
			return replylist;
		} catch (Exception e) {
			System.out.println( "[SQL 오류]"+e  );
		}
		return null;
	}
	
}
