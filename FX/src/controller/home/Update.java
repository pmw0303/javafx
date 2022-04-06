package controller.home;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import controller.login.Login;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Update implements Initializable{
	
	@FXML
	private TextField txtemail;
	@FXML
	private TextField txtaddress;
	@FXML
	private Button btnupdate;
	
	@FXML
	public void update(ActionEvent e) {
	
//		boolean result = MemberDao.memberDao.update(Login.member.getMnum(), txtemail.getText(), txtaddress.getText());
//		if(result) {
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setHeaderText("회원정보가 수정되었습니다 [재로그인]" );
//			alert.showAndWait();
//			
//			Main.instance.loadpage("/view/login/login.fxml");
//			Login.member = null;
//		}else{
//			
//		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtemail.setText(Login.member.getMemail());
		txtaddress.setText(Login.member.getMaddress());
		
	}
	
}
