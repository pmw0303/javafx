package controller.login;

import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class findpw {
	
	 @FXML
	    private TextField txtid;

	    @FXML
	    private Button btnfindpassword;

	    @FXML
	    private Button btnback;

	    @FXML
	    private Label lblconfirm;

	    @FXML
	    private TextField txtemail;

	    @FXML
	    void back(ActionEvent event) {
	    	System.out.println("뒤로가기 버튼을 눌렀습니다.");
			Login.instance.loadpage("/view/login/loginpane.fxml");
	    }

	    @FXML
	    void findpassword(ActionEvent event) {
	    	String id = txtid.getText();
	    	String email = txtemail.getText();
			String password = MemberDao.memberDao.findpw(id, email);
			
			if(password != null) {
				Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("알림");
	    		alert.setHeaderText("PASSWORD 찾기 결과");
	    		alert.setContentText(password);
	    		Member.sendmail(email, password);
	    		alert.showAndWait();
	    	}else {
	    		Alert alert = new Alert(AlertType.INFORMATION); // 메시지 객체
	    		alert.setTitle("알림");
	    		alert.setHeaderText("PASSWORD 찾기 결과");
	    		alert.setContentText("ID 와 EMAIL 이 잘못되었습니다");
	    		alert.showAndWait(); // 메시지 실행
	    	}
	    }	
}
