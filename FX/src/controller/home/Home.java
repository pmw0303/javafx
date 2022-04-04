package controller.home;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.Main;
import controller.login.Login;
import dao.MemberDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

public class Home implements Initializable{

	@FXML
	private Label lblloginid;
	@FXML
	private Label lblpoint;
	@FXML
	private Label lbllogout;
	@FXML
	private Label lbldelete;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblloginid.setText(Login.member.getMid()+" 님");
		lblpoint.setText("포인트 : " + Login.member.getMpoint()+" 점");
	}
	
	@FXML // 로그아웃 라벨 클릭
	public void logout(MouseEvent e) {
		Login.member = null;
		Main.instance.loadpage("/view/login/login.fxml");
	}
	
	@FXML // 회원탈퇴 라벨 클릭
	public void delete(MouseEvent e) {
	
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("탈퇴 하시겠습니까?");
		
		Optional<ButtonType> optional = alert.showAndWait();
		if(optional.get() == ButtonType.OK) {
			boolean result = MemberDao.memberDao.delete(Login.member.getMnum());
			
			if(result) {
				// 로그아웃 [Login 클래스 내 Member 객체 null으로 수
				Login.member = null;
				// 페이지 전환
				Main.instance.loadpage("/view/login/login.fxml");
			}else {
				
			}
		}
		
	}
	
}
