package controller.login;

import java.lang.reflect.Member;

import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class findid {

	@FXML
	private TextField txtid;

	@FXML
	private Button btnsignup;

	@FXML
	private Button btnback;

	@FXML
	private Label lblconfirm;

	@FXML
	private TextField txtemail;

	@FXML
	void back(ActionEvent event) {
		System.out.println("�ڷΰ��� ��ư�� �������ϴ�.");
		Login.instance.loadpage("/view/login/loginpane.fxml");
	}

	@FXML
	void findid(ActionEvent event) {
		String email = txtemail.getText();
		String id = MemberDao.memberDao.findid(email);
		
		if(id != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("�˸�");
    		alert.setHeaderText("ID ã�� ���");
    		alert.setContentText(id);
    		alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION); // �޽��� ��ü
    		alert.setTitle("�˸�");
    		alert.setHeaderText("ID ã�� ���");
    		alert.setContentText("EMAIL �� �߸��Ǿ����ϴ�");
    		alert.showAndWait(); // �޽��� ����
		}	
	}
}
