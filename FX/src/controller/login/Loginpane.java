package controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Loginpane implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblconfirm.setText("");
	}
	
    @FXML
    private TextField txtid;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Button btnlogin;

    @FXML
    private Button btnsignup;

    @FXML
    private Button btnfindid;

    @FXML
    private Button btnfindpassword;

    @FXML
    private Label lblconfirm;

    @FXML
    void accfindid(ActionEvent event) {
    	System.out.println(" ���̵�ã�� �������� �̵� ");
    	Login.instance.loadpage("/view/login/findid.fxml");
    }

    @FXML
    void accfindpassword(ActionEvent event) {
    	System.out.println(" �н�����ã�� �������� �̵� ");
    	Login.instance.loadpage("/view/login/findpw.fxml");
    }

    @FXML
    void accsignup(ActionEvent event) {
    	System.out.println(" ȸ������ �������� �̵� ");
    	Login.instance.loadpage("/view/login/signuppane.fxml");
    }

    @FXML
    void login(ActionEvent event) {
    	
    	// ��Ʈ�ѷ��� �Էµ� �� ��������
    	String id = txtid.getText();
    	String password = txtpassword.getText();
    	// DB ��ü �� �޼ҵ� ȣ��
    	boolean result = MemberDao.memberDao.login(id, password);
    	
    	if(result) {
    		
    		// �α��� ������ ������ ȸ������ ���� [�α׾ƿ��� �ʱ�ȭ]
    		Login.member = MemberDao.memberDao.getmember(id);
    		
    		// ������ ��ȯ
    		controller.Main.instance.loadpage("/view/home/home.fxml");
    		lblconfirm.setText("�α��� ����");
    	}else {
    		lblconfirm.setText("ȸ�� ������ �����ϴ�");
    	}
    	
    }
	
	
}
