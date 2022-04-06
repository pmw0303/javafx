package controller.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Productupdate implements Initializable{

	@FXML
    private Button btnupdate;

    @FXML
    private TextField txtpname;

    @FXML
    private TextArea txtpcontent;

    @FXML
    private TextField txtpprice;

    @FXML
    private Button btnimg;

    @FXML
    private RadioButton opt1;

    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton opt2;

    @FXML
    private RadioButton opt3;

    @FXML
    private RadioButton opt4;

    @FXML
    private ImageView img;

    @FXML
    private Button btnback;

    @FXML
    private Label txtpath;

    @FXML
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/product/productview.fxml");
    }

    @FXML
    void imgadd(ActionEvent event) {
    	// ���� ���� Ŭ���� / ���� ���� ���� ��
    	FileChooser fileChooser = new FileChooser(); 
    	
    	// ���� ���� ����
    	fileChooser.getExtensionFilters().add(
    			new ExtensionFilter("�̹�������:img file","*png","*jpeg","*jpg","*gif"));
    	// ���ο� stage���� ���� ���� ȭ�� ����
    	File file = fileChooser.showOpenDialog(new Stage());
    	
    	// ������ ������ ���
    	txtpath.setText("���ϰ�� : " + file.getPath()); // ��� ���м� : \
    	
    	// ���ϰ��
    	pimage = file.toURI().toString(); // ��� ���м� : /   		
    	
    	// �̹��� �̸�����
    	Image image = new Image(pimage); // �ش� �̹��� ��ΰ� '/' �� ���еǾ��־�� �ϱ� ����
    	img.setImage(image);
    	
    	// ** ���� �����ؿ��� / ������Ʈ �ȿ� img �� 
    	try {
			// ���� �Է� ��Ʈ��
			FileInputStream inputStream = new FileInputStream(file);
			// file �� fileChoose ���� ���õ� file

			// ���� ��� ��Ʈ��
			// ���ο� ��� ����
			File copyfile = new File("C:/Users/504/git/JAVA_FX1/fx_m/src/img/" + file.getName());
			FileOutputStream outputStream = new FileOutputStream(copyfile);
			// ����Ʈ �迭 ����
			byte[] bytes = new byte[1024 * 1024 * 1024]; // 1byte -> 1024byte -> 1kbyte -> 1024kbyte -> 1mbyte
			// �ݺ����� �̿��� inputstream�� ���Ͻ�Ʈ�� ��� �о����
			int size;
			while( (size = inputStream.read(bytes)) > 0 ) { // �о�� ����Ʈ�� 0���� ������ �ݺ��� ����
				outputStream.write(bytes, 0, size);
			}
			// �뷮�� ū ��� ��Ʈ�� ����
			inputStream.close();
			outputStream.close();
			// ���ϸ� DB ����
			pimage = copyfile.toURI().toString();
    	
    	}catch (Exception e) {
			System.out.println("���� ����� ���� " + e);
		}
    }

    @FXML
    void update(ActionEvent event) {
    	
    	if(pimage == null) pimage = Productcontrol.select.getPimg();
    	
    	String pcategory = null;
    	if(opt1.isSelected()) {pcategory="�����Ƿ�";}
    	if(opt2.isSelected()) {pcategory="�����Ƿ�";}
    	if(opt3.isSelected()) {pcategory="���ӱ��";}
    	if(opt4.isSelected()) {pcategory="��Ȱ��ǰ";}
    	
    	Product upproduct = new Product(
    			Productcontrol.select.getPnum(), 
    			txtpname.getText(), 
    			pimage, 
    			txtpcontent.getText(), 
    			pcategory, 
    			Integer.parseInt(txtpprice.getText()), 
    			0, 
    			null, 
    			0);
    	
    	boolean result = ProductDao.productDao.update(upproduct);
    	if(result) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("���� �Ϸ�");
    		alert.showAndWait();
    		Home.home.loadpage("/view/product/product.fxml");
    	}else {
    		
    	}
    	
    }
    
    private String pimage = null; 
   
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		Product product = Productcontrol.select;
		txtpname.setText(product.getPname());
		txtpcontent.setText(product.getPcontent());
		txtpprice.setText(product.getPprice() + "");
		txtpath.setText(product.getPimg());
		if(product.getPcategory().equals("�����Ƿ�")) { opt1.setSelected(true); }
		if(product.getPcategory().equals("�����Ƿ�")) { opt2.setSelected(true); }
		if(product.getPcategory().equals("���ӱ��")) { opt3.setSelected(true); }
		if(product.getPcategory().equals("��Ȱ��ǰ")) { opt4.setSelected(true); }
		
	}
	
}
