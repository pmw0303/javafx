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
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/product/product.fxml");
    }

    @FXML
    void delete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("��ǰ�� �����ϰڽ��ϱ�?");
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
    	
    	// ��Ͽ��� ���õ� ��ǰ ��ü
    	Product product = Productcontrol.select;
    	img.setImage(new Image(product.getPimg()));
    	txtpname.setText(product.getPname());
    	txtpcontent.setText(product.getPcontent());
    		// õ���� ��ǥ
    	DecimalFormat df = new DecimalFormat("���� : #,##0��");
    	txtpprice.setText(df.format(product.getPprice()));
    	
    	if(product.getPactivation() ==1 ) { txtpactivation.setText("���� : �Ǹ���");}
    	if(product.getPactivation() ==2 ) { txtpactivation.setText("���� : ������"); }
    	if(product.getPactivation() ==3 ) { txtpactivation.setText("���� : �ǸſϷ�"); }
    	txtpdate.setText("��ǰ ����� : " + product.getPdate());
    	
    	// ȸ����ȣ�� �̿��� id ã��
    	txtmid.setText("���ȸ�� : " + MemberDao.memberDao.getmid(product.getMnum()));
    	
    	txtpname.setEditable(false);
    	txtpcontent.setEditable(false);
    	
    	// ���ȸ�� ��ȣ�� �α��ε� ��ȣ�� ����ġ ������
    	if(product.getMnum() != Login.member.getMnum()) {
    		btndelete.setVisible(false);
    		btnupdate.setVisible(false);
    	}
    	
    }
}