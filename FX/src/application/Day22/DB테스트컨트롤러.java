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

public class DB�׽�Ʈ��Ʈ�ѷ� implements Initializable{ // fxml ���� ������ �� �ʱⰪ ���� �޼ҵ�
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Day22_2 db���� = new Day22_2();
		
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
    	System.out.println("DB �� �����͸� ȣ���մϴ�");
    	
    	// DB ���� ��ü
    	Day22_2 db���� = new Day22_2();
    	// DB ��ü �� �޼ҵ� ȣ��
    	ArrayList<������> �����͸�� = db����.get();
    	// ��� = ����Ʈ�� ��Ʈ�ѿ� �־��ֱ�
    	for(������ temp : �����͸��) {
    		
    		txtcontentlist.appendText(
    				
    				 temp.get��ȣ() + "- "+
    				 temp.get�ۼ���() + ": "+
    				 temp.get����() + "\n"
    				);
    	}
    	
    }

    @FXML
    void write(ActionEvent event) {
    	System.out.println("DB �� �����͸� �����մϴ�");

    	// fxml.getText() : �ش� ��Ʈ�ѷ��� �Էµ� �� ��������
    	String �ۼ��� = txtwriter.getText();
    	String ���� = txtcontent.getText();
    	
    	// DB���� ��ü �����
    	Day22_2 db���� = new Day22_2();
    	boolean result = db����.write(�ۼ���, ����);
    	if(result) {
    		System.out.println("DB ���� ����");
    		txtwriter.setText(""); // �Է��� �����Ͽ� �������� �ٲ��ش�
    		txtcontent.setText("");
    	}else {
    		System.out.println("DB ���� ����" );
    	}
    }

}
