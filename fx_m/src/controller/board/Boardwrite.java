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
    	// 작성자는 현재 로그인된 아이디 객체는 login 클래스 내 Member 객체에 저장되어있음
    	String write = Login.member.getMid();
    	// 객체화
    	Board board = new Board(0, title, content, write, null, 0);
    	// DB 저장
    		// 1
//    	BoardDao boardDao = new BoardDao();
//    	boardDao.write(board);
    		
    		// 2. BoardDao 에 선언되어 있으므로 굳이 new 를 사용하여 메모리를 또 쓸 필요가 없음
    	boolean result = BoardDao.boardDao.write(board);
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("게시글 작성 완료");
    		alert.showAndWait();
    		// 페이지 전환
    		Home.home.loadpage("/view/board/board.fxml");
    	}else {
    		
    	}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    }
}
