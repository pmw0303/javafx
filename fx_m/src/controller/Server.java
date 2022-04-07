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
    
    // ������ ����� Ŭ���̾�Ʈ�� �����ϴ� ����Ʈ
    public static Vector<Client> clientlist = new Vector<>();
    	// Vector ���� ���� : ����ȭ -> ���� ���� �����尡 �ϳ��� �޼ҵ忡 ������ �� ��� ���� �����
    // ��Ƽ �����带 �������ִ� ������Ǯ
    public static ExecutorService threadpool;
    	// ExecutorService : ������Ǯ �������ִ� �������̽�
    // 1. ���� ����
    ServerSocket serverSocket;
    // 2. ���� ���� �޼ҵ�
    public void serverstart() {
    	try {
    		// ���� ��Ĺ �޸��Ҵ�
        	serverSocket = new ServerSocket();
        	serverSocket.bind(new InetSocketAddress("127.0.0.1", 1234)); // ip, port
    	} catch (Exception e) {}
			// Ŭ���̾�Ʈ ��û ��� [��Ƽ������ : ����, ������, �ޱ� ��� �ϱ� ����]
    		Runnable runnable = new Runnable() {				
				@Override
				public void run() {
					try {
						while(true) {
							Socket socket = serverSocket.accept(); // ��û �����Ŀ� ������ ������ ����
							clientlist.add(new Client(socket)); // ����� Ŭ���̾�Ʈ ���� �� ����Ʈ�� ����
						}
					} catch (Exception e) {
						
					}
					
				}
			}; 
			threadpool = Executors.newCachedThreadPool();
			threadpool.submit(runnable);
		 	
    }
    // 3. ���� ���� �޼ҵ�
    public void serverstop() {
    	try {
			for(Client client : clientlist) {
				client.socket.close();
			}
			// ���� ���� �ݱ�
			serverSocket.close();
			// ������Ǯ �ݱ�
			threadpool.shutdown();
		} catch (Exception e) {
			
		}
    }

    @FXML
    void server(ActionEvent event) { // ���� ���� ��ư ������ ��
    	if(btnserver.getText().equals("���� ����")) {
    		serverstart(); // ���� ���� �޼ҵ� ȣ��
    		txtserver.appendText("[������ �����մϴ�]\n");
    		btnserver.setText("���� ����");
    		
    	}else {
    		serverstop(); // ���� ���� �޼ҵ� ȣ��
    		txtserver.appendText("[������ �����մϴ�]\n");
    		btnserver.setText("���� ����");
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtserver.setDisable(true);
		
	}
}
