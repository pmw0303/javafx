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
	
	private Connection conn; // DB ���� Ŭ����
	private PreparedStatement ps; // ����� DB ���� �������̽�
	private ResultSet rs; // SQL ��� ���ڵ带 �������� �������̽�
	
	public static BoardDao boardDao = new BoardDao();
	
	public BoardDao() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC"
					,"root","1234");
		} catch (Exception e) {
			
		}
	}
	// �� ����
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
			System.out.println("SQL ���� " + e);
		}
		
		return false;
		}
	// �� ȣ�� (ObservableList �� fx���� ����)
	public ObservableList<Board> list() {
		// ����Ʈ ���� 
		ObservableList<Board> boardlist = FXCollections.observableArrayList();
		try {
		// SQL �ۼ�
			// ����
				// order by �ʵ�� asc / desc [�������� / ��������]
			String sql = "select *from board order by bnum desc";
		// SQL ����
			ps = conn.prepareStatement(sql);
		// SQL ����
			rs = ps.executeQuery(); // select
		// SQL ���
			// rs : �˻� ����� ���ڵ带 �ϳ��� ��ȸ [�������� = next()]
				// rs.next : �˻� ����� ���� ���ڵ�
				// rs.get�ڷ��� (�ʵ� ����) : �ش� �ʵ��� ������ ��������
			while(rs.next()) {
				// ��üȭ
				Board board = new Board(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getInt(6));
				// �ش� ��ü�� ����Ʈ�� �ֱ�
				boardlist.add(board);
				
			}
			// ���� �� ����Ʈ ��ȯ
			return boardlist;
		} catch (Exception e) {
			System.out.println("SQL ���� " + e);
		}
		
		
		return null;
		}
	
	public boolean delete( int bnum ) { 
		try {
		// SQL �ۼ�
		String sql = "delete from board where bnum=?";
		// SQL ����
		ps = conn.prepareStatement(sql);
		ps.setInt(1, bnum);
		// SQL ����
		ps.executeUpdate();
		// SQL ���
		return true;
		}catch(Exception e ) { System.out.println( "[SQL ����]"+e  ); }
		return false; 
	}
	
	public boolean update(int bnum, String title, String content) {
		
		try {
			// SQL �ۼ�
			String sql = "update board set btitle=?, bcontent=? where bnum=?";
			// SQL ����
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, bnum);
			// SQL ����
			ps.executeUpdate();
			// SQL ���
			return true;
		} catch (Exception e) {
			System.out.println( "[SQL ����]"+e  );
		}	
		return false;
	}
	
	// ��� �ۼ� �޼ҵ�
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
			System.out.println( "[SQL ����]"+e  );
		}
	
		return false;
	}
	
	// ��� ȣ�� �޼ҵ� [�ش� �Խù��� ��۸� ���]
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
			System.out.println( "[SQL ����]"+e  );
		}
		return null;
	}
	
}
