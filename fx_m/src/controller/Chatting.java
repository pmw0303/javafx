package controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import controller.login.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Chatting implements Initializable{

	@FXML
    private Button btnconnect;

    @FXML
    private TextArea txtcontent;

    @FXML
    private TextField txtmsg;

    @FXML
    private Button btnsend;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtip;

    @FXML
    private TextField txtport;
    
    // 1. 클라이언트 소켓 선언
    Socket socket = new Socket();
    
    // 2. 클라이언트 실행 메소드
    public void clientstart() {
    	// 멀티스레드
    	Thread thread = new Thread() {
    		@Override
    		public void run() {
    			try {
    				socket = new Socket("127.0.0.1", 1234); // 서버의 ip와 port 번호 넣어주기
    				send(Login.member.getMid()+"님 입장했습니다\n"); // 입장과 동시에 입장 메시지 보내기
    				receive();
    			}catch(Exception e) {}
    		};
    	};
    	thread.start();
    }
    
    // 3. 클라이언트 종료 메소드
    public void clientstop() {
    	try {
			socket.close();
		} catch (Exception e) {}
    }
    // 4. 서버에게 메시지 보내기 메소드
    public void send(String msg) {
    	Thread thread = new Thread() {
    		@Override
    		public void run() {
    			try {
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(msg.getBytes());
					outputStream.flush(); // 3. 스트림 초기화 [ 스트림 내 바이트 지우기 ]
				} catch (Exception e) {}   			
    		}
    	};
    	thread.start();
    }
    // 5. 서버에게 메시지 받기 메소드
    public void receive() {
    	try {
			while(true) {
				InputStream inputStream = socket.getInputStream();
				byte[] bytes = new byte[1000];
				inputStream.read(bytes);
				String msg = new String(bytes);
				txtcontent.appendText(msg);
			}
		} catch (Exception e) {}
    }
    
    @FXML
    void connect(ActionEvent event) {
    	if(btnconnect.getText().equals("채팅방 입장")) {
    		clientstart();    		
    		txtcontent.appendText("---[채팅방 입장]---\n");
    		btnconnect.setText("채팅방 나가기");   		
    		// 채팅방 들어온 후
    		txtmsg.setText("");
    		txtmsg.setDisable(false); 	// 채팅 입력창 수정 금지
    		txtcontent.setDisable(false); 	// 채팅창 목록 사용금지
    		btnsend.setDisable(false); 	// 전송버튼 사용 금지
    		txtmsg.requestFocus(); // 채팅입력창으로 포커스(커서) 이동
    		
    	}else {
    		clientstop();   		
    		txtcontent.appendText("---[채팅방 나가기]---\n");
    		btnconnect.setText("채팅방 입장");
    		// 채팅방 나간 후
    		txtmsg.setText("입장 후 사용 가능");
    		txtmsg.setDisable(true); 	// 채팅 입력창 수정 금지
    		txtcontent.setDisable(true); 	// 채팅창 목록 사용금지
    		btnsend.setDisable(true); 	// 전송버튼 사용 금지
    	}
    }

    @FXML
    void send(ActionEvent event) { // 메시지 전송 버튼
    	String msg = txtmsg.getText() + "\n"; // 보낼 메시지
    	send(msg);
    	txtmsg.setText(""); // 보내기 후 입력창 지우기
    	txtmsg.requestFocus(); // 보내기 후 입력창으로 포커스(커서) 이동
    } 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 채팅fxml 열렸을 때 초기값 메소드
			// 채팅방 입장 전ㅇ 아래 fxid 를 사용 못하게 금지
		txtmsg.setText("입장 후 사용 가능");
		txtmsg.setDisable(true); 	// 채팅 입력창 수정 금지
		txtcontent.setDisable(true); 	// 채팅창 목록 사용금지
		btnsend.setDisable(true); 	// 전송버튼 사용 금지

		
	}
}
