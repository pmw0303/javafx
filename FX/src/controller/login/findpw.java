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
	    	System.out.println("�ڷΰ��� ��ư�� �������ϴ�.");
			Login.instance.loadpage("/view/login/loginpane.fxml");
	    }

	    @FXML
	    void findpassword(ActionEvent event) {
	    	String id = txtid.getText();
	    	String email = txtemail.getText();
			String password = MemberDao.memberDao.findpw(id, email);
			
			if(password != null) {
				Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("�˸�");
	    		alert.setHeaderText("PASSWORD ã�� ���");
	    		alert.setContentText(password);
	    		Member.sendmail(email, password);
	    		alert.showAndWait();
	    	}else {
	    		Alert alert = new Alert(AlertType.INFORMATION); // �޽��� ��ü
	    		alert.setTitle("�˸�");
	    		alert.setHeaderText("PASSWORD ã�� ���");
	    		alert.setContentText("ID �� EMAIL �� �߸��Ǿ����ϴ�");
	    		alert.showAndWait(); // �޽��� ����
	    	}
	    }	
}
