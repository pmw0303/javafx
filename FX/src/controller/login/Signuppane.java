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
    	System.out.println("뒤로가기 버튼을 눌렀습니다.");
    	Login.instance.loadpage("/view/login/loginpane.fxml");
    }

    @FXML
    void signup(ActionEvent event) {
    	// 컨트롤에 저장된 데이터 가져오기
    	String id = txtid.getText();
    	String password = txtpassword.getText();
    	String passwordconfirm = txtpasswordconfirm.getText();
    	String email = txtemail.getText();
    	String address = txtaddress.getText();
    	
    	// 현재 날짜 가져오기 - 형식 변환
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	String cince = format.format(new Date());
    	
    	// 유효성 검사
    		// id 중복체크, 비밀번호 체크, 이메일 체크, 주소 체크
    	
    	//아이디 중복체크
    	boolean result1 = MemberDao.memberDao.idcheck(id);
    	if(result1) {
    		lblconfirm.setText("ID 중복");
    		return;
    	}
    	// 아이디 형식
    	if(id.length() < 4 || id.length() > 10) {
    		lblconfirm.setText("아이디 길이 문제 발생");
    		return;
    	}    	
    	// 비밀번호 형식
    	if(password.length() < 4 || password.length() > 10
    			|| passwordconfirm.length() < 4 || passwordconfirm.length() > 10) {
    	// 비밀번호가 너무 짧거나 길면 가입 X
    		lblconfirm.setText("비밀번호의 길이 문제 발생");
    		return;
    	}    	
    	// 비밀번호 체크
    	if(!password.equals(passwordconfirm)) {
    		lblconfirm.setText("비밀번호가 일치하지 않습니다");
    		return;
    	}
    	// 이메일 체크
    	if(email.indexOf("@") == -1) { // 이메일에 @가 없으면
    		lblconfirm.setText("올바르지 않은 이메일 양식");
    		return;
    	}
    	// 주소 체크
    	if(!address.contains("시") && !address.contains("구") && !address.contains("동")) {
    		lblconfirm.setText("주소에 시, 구, 동 필수 포함되어야함");
    		return;
    	}
    	
    	// 통과시 DB 저장
    		// 객체화 [회원번호 없음 / 포인트]
    	Member member = new Member(0, id, password, email, address, 100, cince);
    		// DB저장
 
	    	boolean result = MemberDao.memberDao.signup(member);
	    	if(result) {
	    		// 메시지창 출력 [alert]
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("알림");
	    		alert.setHeaderText("환영합니다");
	    		alert.setContentText("최고가 되어주세요");
	    		alert.showAndWait();
	    		
	    		// 화면 전환
	    		Login.instance.loadpage("/view/login/loginpane.fxml");
	    	}else {
	    		System.out.println("가입실패");
	    	}   	
    }

}







