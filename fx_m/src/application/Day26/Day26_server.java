package application.Day26;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Day26_server {

	public static void main(String[] args) {
		
		// ���� ���� �����
		try {
			// ���� ���� �����
			ServerSocket serverSocket = new ServerSocket();
			// ���� ���� ���ε�
			serverSocket.bind(new InetSocketAddress("192.168.17.138",5000));
			
			// Ŭ���̾�Ʈ�� ��û ���
			while(true) {
				System.out.println("[���� ���� �����..]");
				// ��û �������
				Socket socket = serverSocket.accept();
				// ������ ������ ���� Ȯ��
				InetSocketAddress socketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
				System.out.println("[Ŭ���̾�Ʈ�� ������ �Ǿ����ϴ�]");
				System.out.println("Ŭ���̾�Ʈ ���� : " + socketAddress.getHostName());
				
				// Ŭ���̾�Ʈ�κ��� ������ ����
				InputStream inputStream = socket.getInputStream();
				byte[] bytes = new byte[1000];
				inputStream.read(bytes);
				System.out.println("���� �޽��� : " + new String(bytes));
				
				// Ŭ���̾�Ʈ���� �۽��ϱ�
				Scanner scanner = new Scanner(System.in);
				System.out.println("���� �޽���");
				String msg = scanner.next();
					// ������ ��� ��Ʈ��
				OutputStream outputStream = socket.getOutputStream();
					// ��������
				outputStream.write(msg.getBytes());
			
			}
		} catch (Exception e) {
			
		}
	}
}
