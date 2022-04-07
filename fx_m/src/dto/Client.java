package dto;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import controller.Server;

public class Client {

	// 서버에 들어오는 클라이언트의 클래스
	
	// 필드
	public Socket socket;
	
	// 생성자 
	public Client(Socket socket) {
		this.socket = socket;
		receive(); // 서버와 연결된 클라이언트 객체가 생성되었을 때 받기 메소드
	}
	
	// 서버로 메시지 받는 메소드 [실행조건 : 서버와 클라이언트가 연결되었을 때]
	public void receive() {
		// 멀티스레드
			// run 메소드를 필수적으로 구현
		Runnable runnable = new Runnable() { // 익명 구현객체			
			@Override
			public void run() { // 추상 메소드 구현
				// 계속적으로 메시지 받는 상태
				try {
					while(true) {
						InputStream inputStream = socket.getInputStream();
						byte[] bytes = new byte[1000]; // 바이트 배열 선언
						inputStream.read(bytes); // 입력스트림으로 바이트 읽어오기
						String msg = new String(bytes); // 바이트열 -> 문자열
						// 서버가 받은 메시지를 현재 서버에 접속된 모든 클라이언트에게 받은 메시지 보내기
						for(Client client : Server.clientlist) {
							client.send(msg);
						}
					}
				}catch(Exception e) {}						
			}
		}; // 멀티스레드 구현 끝
		
		// 해당 멀티스레드를 스레드풀에 넣어주기
		Server.threadpool.submit(runnable);
	}
	// 서버로 메시지를 보내느 메소드
	public void send(String msg) {
		Runnable runnable = new Runnable() {	
			@Override
			public void run() {
				try {
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(msg.getBytes());
				} catch (Exception e) {
					
				}				
			}
		};
		Server.threadpool.submit(runnable);
	}
	
}
