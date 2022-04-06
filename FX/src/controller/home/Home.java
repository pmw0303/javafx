package controller.home;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.Main;
import controller.login.Login;
import dao.MemberDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Home implements Initializable{

	@FXML
	private Label lblloginid;
	@FXML
	private Label lblpoint;
	@FXML
	private Label lbllogout;
	@FXML
	private Label lbldelete;
	@FXML
	private Label lblinfo;
	@FXML
	private Label lblupdate;
	@FXML
	private BorderPane borderpane;
	
	@FXML
	public void accinfo(MouseEvent e) { 
		loadpage("/view/home/info.fxml");
	}
	@FXML
	public void accupdate(MouseEvent e) { 
		loadpage("/view/home/update.fxml");
	}
	
	public void loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page));
			borderpane.setCenter(parent);
		} catch (Exception e) {
			System.out.println("Load ���� : " + e);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblloginid.setText(Login.member.getMid()+" ��");
		lblpoint.setText("����Ʈ : " + Login.member.getMpoint()+" ��");
	}
	
	@FXML // �α׾ƿ� �� Ŭ��
	public void logout(MouseEvent e) {
		Login.member = null;
		Main.instance.loadpage("/view/login/login.fxml");
	}
	
	@FXML // ȸ��Ż�� �� Ŭ��
	public void delete(MouseEvent e) {
	
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Ż�� �Ͻðڽ��ϱ�?");
		
		Optional<ButtonType> optional = alert.showAndWait();
		if(optional.get() == ButtonType.OK) {
			boolean result = MemberDao.memberDao.delete(Login.member.getMnum());
			
			if(result) {
				// �α׾ƿ� [Login Ŭ���� �� Member ��ü null���� ��
				Login.member = null;
				// ������ ��ȯ
				Main.instance.loadpage("/view/login/login.fxml");
			}else {
				
			}
		}
		
	}
	
}
