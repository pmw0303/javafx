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
			System.out.println("[���� ���� �Ϸ�]");
			
			Scanner scanner = new Scanner(System.in);
			// ������ ������ �۽�
				// ������ �Է¹ޱ�
			System.out.println("�������� ���� �޽��� : "); String msg = scanner.next();
				// ������ ��� ��Ʈ�� ��������
			OutputStream outputStream = socket.getOutputStream();
				// ��������
			outputStream.write(msg.getBytes());
			
			// �������� ������ ����
				// ������ �Է� ��Ʈ��
			InputStream inputStream = socket.getInputStream();
			byte[] bytes = new byte[1000];
				// �Է½�Ʈ������ ����Ʈ ��������
			inputStream.read(bytes);
			System.out.println("������ ���� �޽��� : " + new String(bytes));
			
			
		} catch (Exception e) {
			
		}
	}

}
