package application.Day22;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DB테스트컨트롤러 implements Initializable{ // fxml 새로 열렸을 때 초기값 설정 메소드
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Day22_2 db연동 = new Day22_2();
		
	}

	@FXML
    private TextField txtwriter;

    @FXML
    private TextField txtcontent;

    @FXML
    private Button btnget;

    @FXML
    private Button btnwrite;

    @FXML
    private TextArea txtcontentlist;

    @FXML
    void get(ActionEvent event) {
    	System.out.println("DB 내 데이터를 호출합니다");
    	
    	// DB 연동 객체
    	Day22_2 db연동 = new Day22_2();
    	// DB 객체 내 메소드 호출
    	ArrayList<데이터> 데이터목록 = db연동.get();
    	// 결과 = 리스트를 컨트롤에 넣어주기
    	for(데이터 temp : 데이터목록) {
    		
    		txtcontentlist.appendText(
    				
    				 temp.get번호() + "- "+
    				 temp.get작성자() + ": "+
    				 temp.get내용() + "\n"
    				);
    	}
    	
    }

    @FXML
    void write(ActionEvent event) {
    	System.out.println("DB 내 데이터를 저장합니다");

    	// fxml.getText() : 해당 컨트롤러에 입력된 값 가져오기
    	String 작성자 = txtwriter.getText();
    	String 내용 = txtcontent.getText();
    	
    	// DB연동 객체 만들기
    	Day22_2 db연동 = new Day22_2();
    	boolean result = db연동.write(작성자, 내용);
    	if(result) {
    		System.out.println("DB 저장 성공");
    		txtwriter.setText(""); // 입력을 성공하여 공백으로 바꿔준다
    		txtcontent.setText("");
    	}else {
    		System.out.println("DB 저장 실패" );
    	}
    }

}
