package controller.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Productadd implements Initializable{

	@FXML
    private Button btnadd;

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
    void add(ActionEvent event) {
    	// 1. 컨트롤에 입력된 데이터 가져오기
    	String pname = txtpname.getText();
    	String pcontent = txtpcontent.getText();
    	int pprice = Integer.parseInt(txtpprice.getText());
    		// 카테고리
    	String pcategory = null;
    	if(opt1.isSelected()) {pcategory="남성의류";}
    	if(opt2.isSelected()) {pcategory="여성의류";}
    	if(opt3.isSelected()) {pcategory="게임기기";}
    	if(opt4.isSelected()) {pcategory="생활용품";}
    	
    	int mnum = Login.member.getMnum();
    	// 2. 객체화
    	Product product = new Product(0, pname, pimage, pcontent, pcategory, pprice, 1, null, mnum);
    	// 3. DB처리
    	boolean result = ProductDao.productDao.add(product);
    	
    	
    	// 4. 결과처리
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("제품 등록 완료");
    		alert.showAndWait();
    		
    		Home.home.loadpage("/view/product/product.fxml");
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("제품 등록 실패 [ 관리자 문의 ]");
    		alert.showAndWait();
    	}
    }
    
    private String pimage = null; 
    // 메소드 밖에서 선언 이유 -> 둘다 사용하기 위해 (imgadd, add 메소드)

    @FXML
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/product/product.fxml");
    }

    @FXML
    void imgadd(ActionEvent event) { // 이미지 등록 버튼
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
    	
    	
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	
    	
    }
}
