package dto;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import controller.Server;

public class Client {

	// ������ ������ Ŭ���̾�Ʈ�� Ŭ����
	
	// �ʵ�
	public Socket socket;
	
	// ������ 
	public Client(Socket socket) {
		this.socket = socket;
		receive(); // ������ ����� Ŭ���̾�Ʈ ��ü�� �����Ǿ��� �� �ޱ� �޼ҵ�
	}
	
	// ������ �޽��� �޴� �޼ҵ� [�������� : ������ Ŭ���̾�Ʈ�� ����Ǿ��� ��]
	public void receive() {
		// ��Ƽ������
			// run �޼ҵ带 �ʼ������� ����
		Runnable runnable = new Runnable() { // �͸� ������ü			
			@Override
			public void run() { // �߻� �޼ҵ� ����
				// ��������� �޽��� �޴� ����
				try {
					while(true) {
						InputStream inputStream = socket.getInputStream();
						byte[] bytes = new byte[1000]; // ����Ʈ �迭 ����
						inputStream.read(bytes); // �Է½�Ʈ������ ����Ʈ �о����
						String msg = new String(bytes); // ����Ʈ�� -> ���ڿ�
						// ������ ���� �޽����� ���� ������ ���ӵ� ��� Ŭ���̾�Ʈ���� ���� �޽��� ������
						for(Client client : Server.clientlist) {
							client.send(msg);
						}
					}
				}catch(Exception e) {}						
			}
		}; // ��Ƽ������ ���� ��
		
		// �ش� ��Ƽ�����带 ������Ǯ�� �־��ֱ�
		Server.threadpool.submit(runnable);
	}
	// ������ �޽����� ������ �޼ҵ�
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
