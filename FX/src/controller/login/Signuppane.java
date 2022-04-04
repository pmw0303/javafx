package controller.login;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Signuppane implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblconfirm.setText("");
	}
	
    @FXML
    private TextField txtid;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Button btnsignup;

    @FXML
    private Button btnback;

    @FXML
    private Label lblconfirm;

    @FXML
    private PasswordField txtpasswordconfirm;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtaddress;

    @FXML
    void back(ActionEvent event) {
    	System.out.println("�ڷΰ��� ��ư�� �������ϴ�.");
    	Login.instance.loadpage("/view/login/loginpane.fxml");
    }

    @FXML
    void signup(ActionEvent event) {
    	// ��Ʈ�ѿ� ����� ������ ��������
    	String id = txtid.getText();
    	String password = txtpassword.getText();
    	String passwordconfirm = txtpasswordconfirm.getText();
    	String email = txtemail.getText();
    	String address = txtaddress.getText();
    	
    	// ���� ��¥ �������� - ���� ��ȯ
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	String cince = format.format(new Date());
    	
    	// ��ȿ�� �˻�
    		// id �ߺ�üũ, ��й�ȣ üũ, �̸��� üũ, �ּ� üũ
    	
    	//���̵� �ߺ�üũ
    	boolean result1 = MemberDao.memberDao.idcheck(id);
    	if(result1) {
    		lblconfirm.setText("ID �ߺ�");
    		return;
    	}
    	// ���̵� ����
    	if(id.length() < 4 || id.length() > 10) {
    		lblconfirm.setText("���̵� ���� ���� �߻�");
    		return;
    	}    	
    	// ��й�ȣ ����
    	if(password.length() < 4 || password.length() > 10
    			|| passwordconfirm.length() < 4 || passwordconfirm.length() > 10) {
    	// ��й�ȣ�� �ʹ� ª�ų� ��� ���� X
    		lblconfirm.setText("��й�ȣ�� ���� ���� �߻�");
    		return;
    	}    	
    	// ��й�ȣ üũ
    	if(!password.equals(passwordconfirm)) {
    		lblconfirm.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
    		return;
    	}
    	// �̸��� üũ
    	if(email.indexOf("@") == -1) { // �̸��Ͽ� @�� ������
    		lblconfirm.setText("�ùٸ��� ���� �̸��� ���");
    		return;
    	}
    	// �ּ� üũ
    	if(!address.contains("��") && !address.contains("��") && !address.contains("��")) {
    		lblconfirm.setText("�ּҿ� ��, ��, �� �ʼ� ���ԵǾ����");
    		return;
    	}
    	
    	// ����� DB ����
    		// ��üȭ [ȸ����ȣ ���� / ����Ʈ]
    	Member member = new Member(0, id, password, email, address, 100, cince);
    		// DB����
 
	    	boolean result = MemberDao.memberDao.signup(member);
	    	if(result) {
	    		// �޽���â ��� [alert]
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("�˸�");
	    		alert.setHeaderText("ȯ���մϴ�");
	    		alert.setContentText("�ְ� �Ǿ��ּ���");
	    		alert.showAndWait();
	    		
	    		// ȭ�� ��ȯ
	    		Login.instance.loadpage("/view/login/loginpane.fxml");
	    	}else {
	    		System.out.println("���Խ���");
	    	}   	
    }

}







