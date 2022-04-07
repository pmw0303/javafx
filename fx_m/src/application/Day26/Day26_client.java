package application.Day26;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Day26_client {
	
	public static void main(String[] args) {
		
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress("192.168.17.138",5000));
			System.out.println("[서버 연결 완료]");
			
			Scanner scanner = new Scanner(System.in);
			// 서버에 데이터 송신
				// 데이터 입력받기
			System.out.println("서버에게 보낼 메시지 : "); String msg = scanner.next();
				// 소켓의 출력 스트림 가져오기
			OutputStream outputStream = socket.getOutputStream();
				// 내보내기
			outputStream.write(msg.getBytes());
			
			// 서버에서 데이터 수신
				// 소켓의 입력 스트림
			InputStream inputStream = socket.getInputStream();
			byte[] bytes = new byte[1000];
				// 입력스트림에서 바이트 가져오기
			inputStream.read(bytes);
			System.out.println("서버가 보낸 메시지 : " + new String(bytes));
			
			
		} catch (Exception e) {
			
		}
	}

}
