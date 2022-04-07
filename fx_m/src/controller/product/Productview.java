package controller.product;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.MemberDao;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Productview implements Initializable{

	@FXML
    private ImageView img;

    @FXML
    private Button btnback;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;

    @FXML
    private TextField txtpname;

    @FXML
    private TextArea txtpcontent;

    @FXML
    private Label txtpactivation;

    @FXML
    private Label txtpdate;

    @FXML
    private Label txtmid;
    
    @FXML
    private Label txtpprice;
    
    @FXML
    private Button btnactivation;

    @FXML
    void activation(ActionEvent event) {
    	if(btnactivation.getText().equals("거래중")) {
    		// 컨트롤 값 변경
    		txtpactivation.setText("상태 : 거래중"); 
    		btnactivation.setText("판매완료");
    		// DB 값 변경
    		ProductDao.productDao.activation(Productcontrol.select.getPnum());
    		// 선택된 제품의 상태 변경
    		Productcontrol.select.setPactivation(2);
    		return;
    	}
    	if(btnactivation.getText().equals("판매완료")) {
    		txtpactivation.setText("상태 : 판매완료"); 
    		btnactivation.setText("판매중");
    		ProductDao.productDao.activation(Productcontrol.select.getPnum());
    		Productcontrol.select.setPactivation(3);
    		return;
    	}
    	if(btnactivation.getText().equals("판매중")) {
    		txtpactivation.setText("상태 : 판매중"); 
    		btnactivation.setText("거래중");
    		ProductDao.productDao.activation(Productcontrol.select.getPnum());
    		Productcontrol.select.setPactivation(1);
    		return;
    	}
    }


    @FXML
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/product/product.fxml");
    }

    @FXML
    void delete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("제품을 삭제하겠습니까?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get() == ButtonType.OK) {
    		ProductDao.productDao.delete(Productcontrol.select.getPnum());
    		Home.home.loadpage("/view/product/product.fxml");
    	}
    }

    @FXML
    void update(ActionEvent event) {
    	Home.home.loadpage("/view/product/productupdate.fxml");
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	// 목록에서 선택된 제품 객체
    	Product product = Productcontrol.select;
    	img.setImage(new Image(product.getPimg()));
    	txtpname.setText(product.getPname());
    	txtpcontent.setText(product.getPcontent());
    		// 천단위 쉼표
    	DecimalFormat df = new DecimalFormat("가격 : #,##0원");
    	txtpprice.setText(df.format(product.getPprice()));
    	
    	if(product.getPactivation() ==1 ) { 
    		txtpactivation.setText("상태 : 판매중"); btnactivation.setText("거래중");
    		}
    	if(product.getPactivation() ==2 ) { 
    		txtpactivation.setText("상태 : 예약중"); btnactivation.setText("판매완료");
    		}
    	if(product.getPactivation() ==3 ) { 
    		txtpactivation.setText("상태 : 판매완료"); btnactivation.setText("판매중");
    		}
    	txtpdate.setText("제품 등록일 : " + product.getPdate());
    	
    	// 회원번호를 이용해 id 찾기
    	txtmid.setText("등록회원 : " + MemberDao.memberDao.getmid(product.getMnum()));
    	
    	txtpname.setEditable(false);
    	txtpcontent.setEditable(false);
    	
    	// 등록회원 번호와 로그인된 번호가 동일치 않으면
    	if(product.getMnum() != Login.member.getMnum()) {
    		btndelete.setVisible(false);
    		btnupdate.setVisible(false);
    	}
    	
    }
}
