package controller.board;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BoardDao;
import dto.Board;
import dto.Reply;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Boardview implements Initializable {
	

    @FXML
    private Button btnrewrite;

    @FXML
    private TextField txttitle;

    @FXML
    private TextArea txtcontent;

    @FXML
    private Button btnback;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;

    @FXML
    private Label lblwrite;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblview;

    @FXML
    private TextArea txtrecontent;

    @FXML
    private TableView<Reply> replytable;
    
    // *** ��� ���̺� �޼ҵ� -> �޼ҵ�� ����ԵǸ� ��� ȣ���� �� ���� / initial �� �ѹ��ۿ� ����
    public void replytableshow() {
    	// ���� �Խù� ��ȣ
    	int bnum = controller.board.Board.board.getBnum();
    	// �Խù� ��ȣ�� �ִ� ���
    	ObservableList<Reply> replylist = BoardDao.boardDao.replylist(bnum);
    	// ������ �°� ������̺� �ִ� ���� �ҷ�����
    	TableColumn tc = replytable.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("rnum"));
		tc = replytable.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("rwrite"));
		tc = replytable.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("rdate"));
		tc = replytable.getColumns().get(3);
		tc.setCellValueFactory( new PropertyValueFactory<>("rcontent") );
    	//
    	replytable.setItems(replylist); 	
    }

    @FXML
    void rewrite(ActionEvent event) { // ��� �ۼ�
    	
    	// ��Ʈ�ѷ��� �Էµ� ������ ��������
    	String rcontent = txtrecontent.getText();
    	// ���� �α��ε� ���̵� ��������
    	String rwrite = Login.member.getMid();
    	// ���� ���̺�信�� Ŭ���� �Խù��� ��ȣ ��������
    	int bnum = controller.board.Board.board.getBnum();
    	//��üȭ
    	Reply reply = new Reply(0, rcontent, rwrite, null, bnum);
    	// DB ó��
    	boolean result = BoardDao.boardDao.rwirte(reply);
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("��� ��� �Ϸ�");
    		alert.showAndWait();
    		txtrecontent.setText("");
    		// ��� �ۼ� �� ���ΰ�ħ
    		replytableshow();
    	}
    }

    boolean upcheck = true; // ���� ��Ȳ ����ġ ����
    @FXML
    void update(ActionEvent event) { // �� ����
    	Alert alert = new Alert(AlertType.INFORMATION);
    	if(upcheck) {    	
    	alert.setHeaderText("�Խñ� ������ ���� �Ϸ� ��ư�� �����ּ���");
    	alert.showAndWait();
    	txttitle.setEditable(true);
		txtcontent.setEditable(true);
		btnupdate.setText("�����Ϸ�");
		upcheck = false;
    	}else { // �����Ϸ�
    		// dbó��
    		BoardDao.boardDao.update(controller.board.Board.board.getBnum(),
    				txttitle.getText(), txtcontent.getText());
    		
    		
    		alert.setHeaderText("������ �Ϸ�Ǿ����ϴ�");
        	alert.showAndWait();
        	txttitle.setEditable(false);
    		txtcontent.setEditable(false);
    		btnupdate.setText("����");
    		upcheck = true;
    		
    	}
    }
    
    @FXML
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/board/board.fxml");
    }

    @FXML
    void delete(ActionEvent event) { // �� ����
    	
    	// ��� �޽��� �˸�
    	Alert alert = new Alert(AlertType.CONFIRMATION); // Ȯ�� ��� ��ư
    	alert.setHeaderText("�Խù��� ���� �ϰڽ��ϱ�?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	// Optional Ŭ���� : null �� ��ü�� ����
    	if(optional.get() == ButtonType.OK) {
    		BoardDao.boardDao.delete(
    				controller.board.Board.board.getBnum());
    		Home.home.loadpage("/view/board/board.fxml");
    	}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		replytableshow();
		Board board = controller.board.Board.board; // board��Ʈ�ѳ� ���̺��� ���õ� ��ü ȣ�� 
		
		// �� ��Ʈ�ѿ� ���õ� board�� ������ �����ϱ� 
		lblwrite.setText( "�ۼ��� : " + board.getBwrite() );
		lbldate.setText( "�ۼ��� : " + board.getBdate() );
		lblview.setText( "��ȸ�� : " + board.getBview() );
		txttitle.setText( board.getBtitle() );
		txtcontent.setText( board.getBcontent() );
		// ���࿡ �Խù� �ۼ��� �� ����α��ε� id�� �������� ������
		if( ! board.getBwrite().equals( Login.member.getMid() ) ) { // !:����
			btndelete.setVisible(false); // ��ư �����
			btnupdate.setVisible(false); // false = ��ư ����� true = ��ư ���̱�
		}
		// ���� �� ������ ���� ���ϰ� ���� ����
		txttitle.setEditable(false);
		txtcontent.setEditable(false);
	}

}
