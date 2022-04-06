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
    	// 파일 추적 클래스 / 파일 선택 했을 때
    	FileChooser fileChooser = new FileChooser(); 
    	
    	// 파일 선택 형식
    	fileChooser.getExtensionFilters().add(
    			new ExtensionFilter("이미지파일:img file","*png","*jpeg","*jpg","*gif"));
    	// 새로운 stage에서 파일 선택 화면 실행
    	File file = fileChooser.showOpenDialog(new Stage());
    	
    	// 선택한 파일의 경로
    	txtpath.setText("파일경로 : " + file.getPath()); // 경로 구분선 : \
    	
    	// 파일경로
    	pimage = file.toURI().toString(); // 경로 구분선 : /   		
    	
    	// 이미지 미리보기
    	Image image = new Image(pimage); // 해당 이미지 경로가 '/' 로 구분되어있어야 하기 때문
    	img.setImage(image);
    	
    	// ** 파일 복사해오기 / 프로젝트 안에 img 로 
    	try {
			// 파일 입력 스트림
			FileInputStream inputStream = new FileInputStream(file);
			// file 은 fileChoose 에서 선택된 file

			// 파일 출력 스트림
			// 새로운 경로 설정
			File copyfile = new File("C:/Users/504/git/JAVA_FX1/fx_m/src/img/" + file.getName());
			FileOutputStream outputStream = new FileOutputStream(copyfile);
			// 바이트 배열 선언
			byte[] bytes = new byte[1024 * 1024 * 1024]; // 1byte -> 1024byte -> 1kbyte -> 1024kbyte -> 1mbyte
			// 반복문을 이용한 inputstream의 파일스트림 모두 읽어오기
			int size;
			while( (size = inputStream.read(bytes)) > 0 ) { // 읽어온 바이트가 0보다 작으면 반복문 종료
				outputStream.write(bytes, 0, size);
			}
			// 용량이 큰 경우 스트림 종료
			inputStream.close();
			outputStream.close();
			// 파일명 DB 저장
			pimage = copyfile.toURI().toString();
    	
    	}catch (Exception e) {
			System.out.println("파일 입출력 오류 " + e);
		}
    }

    @FXML
    void update(ActionEvent event) {
    	
    	if(pimage == null) pimage = Productcontrol.select.getPimg();
    	
    	String pcategory = null;
    	if(opt1.isSelected()) {pcategory="남성의류";}
    	if(opt2.isSelected()) {pcategory="여성의류";}
    	if(opt3.isSelected()) {pcategory="게임기기";}
    	if(opt4.isSelected()) {pcategory="생활용품";}
    	
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
    		alert.setHeaderText("수정 완료");
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
		if(product.getPcategory().equals("남성의류")) { opt1.setSelected(true); }
		if(product.getPcategory().equals("여성의류")) { opt2.setSelected(true); }
		if(product.getPcategory().equals("게임기기")) { opt3.setSelected(true); }
		if(product.getPcategory().equals("생활용품")) { opt4.setSelected(true); }
		
	}
	
}
