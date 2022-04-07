package controller;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dto.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Server implements Initializable{
    @FXML
    private Button btnserver;
    @FXML
    private TextArea txtserver;
    
    // 서버에 연결된 클라이언트를 저장하는 리스트
    public static Vector<Client> clientlist = new Vector<>();
    	// Vector 쓰는 이유 : 동기화 -> 여러 개의 스레드가 하나의 메소드에 접근할 때 대기 상태 만들기
    // 멀티 스레드를 관리해주는 스레드풀
    public static ExecutorService threadpool;
    	// ExecutorService : 스레드풀 지원해주는 인터페이스
    // 1. 서버 소켓
    ServerSocket serverSocket;
    // 2. 서버 실행 메소드
    public void serverstart() {
    	try {
    		// 서버 소캣 메모리할당
        	serverSocket = new ServerSocket();
        	serverSocket.bind(new InetSocketAddress("127.0.0.1", 1234)); // ip, port
    	} catch (Exception e) {}
			// 클라이언트 요청 대기 [멀티스레드 : 연결, 보내기, 받기 모두 하기 위해]
    		Runnable runnable = new Runnable() {				
				@Override
				public void run() {
					try {
						while(true) {
							Socket socket = serverSocket.accept(); // 요청 수락후에 수락된 소켓을 저장
							clientlist.add(new Client(socket)); // 연결된 클라이언트 생성 후 리스트에 저장
						}
					} catch (Exception e) {
						
					}
					
				}
			}; 
			threadpool = Executors.newCachedThreadPool();
			threadpool.submit(runnable);
		 	
    }
    // 3. 서버 종료 메소드
    public void serverstop() {
    	try {
			for(Client client : clientlist) {
				client.socket.close();
			}
			// 서버 소켓 닫기
			serverSocket.close();
			// 스레드풀 닫기
			threadpool.shutdown();
		} catch (Exception e) {
			
		}
    }

    @FXML
    void server(ActionEvent event) { // 서버 실행 버튼 눌렀을 때
    	if(btnserver.getText().equals("서버 실행")) {
    		serverstart(); // 서버 실행 메소드 호출
    		txtserver.appendText("[서버를 실행합니다]\n");
    		btnserver.setText("서버 중지");
    		
    	}else {
    		serverstop(); // 서버 중지 메소드 호출
    		txtserver.appendText("[서버를 중지합니다]\n");
    		btnserver.setText("서버 실행");
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtserver.setDisable(true);
		
	}
}
