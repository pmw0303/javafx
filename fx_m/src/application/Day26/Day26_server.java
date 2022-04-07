package application.Day26;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Day26_server {

	public static void main(String[] args) {
		
		// 서버 소켓 만들기
		try {
			// 서버 소켓 만들기
			ServerSocket serverSocket = new ServerSocket();
			// 서버 소켓 바인딩
			serverSocket.bind(new InetSocketAddress("192.168.17.138",5000));
			
			// 클라이언트의 요청 대기
			while(true) {
				System.out.println("[서버 연결 대기중..]");
				// 요청 있을경우
				Socket socket = serverSocket.accept();
				// 수락된 소켓의 정보 확인
				InetSocketAddress socketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
				System.out.println("[클라이언트와 연결이 되었습니다]");
				System.out.println("클라이언트 정보 : " + socketAddress.getHostName());
				
				// 클라이언트로부터 데이터 수신
				InputStream inputStream = socket.getInputStream();
				byte[] bytes = new byte[1000];
				inputStream.read(bytes);
				System.out.println("받은 메시지 : " + new String(bytes));
				
				// 클라이언트에게 송신하기
				Scanner scanner = new Scanner(System.in);
				System.out.println("보낼 메시지");
				String msg = scanner.next();
					// 소켓의 출력 스트림
				OutputStream outputStream = socket.getOutputStream();
					// 내보내기
				outputStream.write(msg.getBytes());
			
			}
		} catch (Exception e) {
			
		}
	}
}
