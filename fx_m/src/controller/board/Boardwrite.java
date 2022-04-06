package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BoardDao;
import dto.Board;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Boardwrite implements Initializable{

	@FXML
    private Button btnwrite;

    @FXML
    private TextField txttitle;

    @FXML
    private TextArea txtcontent;

    @FXML
    private Button btnback;

    @FXML
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/board/board.fxml");
    }

    @FXML
    void write(ActionEvent event) {
    	
    	String title = txttitle.getText();
    	String content = txtcontent.getText();
    	// �ۼ��ڴ� ���� �α��ε� ���̵� ��ü�� login Ŭ���� �� Member ��ü�� ����Ǿ�����
    	String write = Login.member.getMid();
    	// ��üȭ
    	Board board = new Board(0, title, content, write, null, 0);
    	// DB ����
    		// 1
//    	BoardDao boardDao = new BoardDao();
//    	boardDao.write(board);
    		
    		// 2. BoardDao �� ����Ǿ� �����Ƿ� ���� new �� ����Ͽ� �޸𸮸� �� �� �ʿ䰡 ����
    	boolean result = BoardDao.boardDao.write(board);
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("�Խñ� �ۼ� �Ϸ�");
    		alert.showAndWait();
    		// ������ ��ȯ
    		Home.home.loadpage("/view/board/board.fxml");
    	}else {
    		
    	}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    }
}
