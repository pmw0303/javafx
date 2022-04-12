package controller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import dao.MemberDao;
import dao.ProductDao;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class Record implements Initializable{
	
	@FXML
	private Label lblmtotal;
	
	@FXML
	private Label lblptotal;
	
	@FXML
	private Label lblbtotal;
	
	@FXML
	private BarChart mbc;
	
	@FXML
	private BarChart bbc;
	
	@FXML
	private BarChart pbc;
	
	@FXML
    private PieChart ppc;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		// ��ü ȸ�� ��
		int mtotal = MemberDao.memberDao.counttotal("member");
		lblmtotal.setText(mtotal + " ��");
		// ��ü ��ǰ ��
		int ptotal = MemberDao.memberDao.counttotal("product");
		lblptotal.setText(ptotal + " ��");
		// ��ü �Խù� ��
		int btotal = MemberDao.memberDao.counttotal("board");
		lblbtotal.setText(btotal + " ��");
		
		// ��¥�� ȸ�� ���� ��
			// ������ ����
		XYChart.Series series = new XYChart.Series<>(); // xy�� �迭 ����
		
		// DB ���� ��������
			// ��¥���� ȸ������ �� üũ [yyyy-MM-dd , X]
			// Map �÷��� -> Ű , ������ �ϳ��� ��Ʈ�� ���� / Ű = ��¥ , �� = ���� ��
		Map<String , Integer> map = MemberDao.memberDao.datetotal("member" , "msince");
	
		// �ݺ���
		// �����͸� �迭�� �߰�
		for(String key : map.keySet()) {
			XYChart.Data data = new XYChart.Data<>(key, map.get(key));
			// �ش� ������ �迭 ��ü�� �迭�� �߰�
			series.getData().add(data);
		}
		// ������Ʈ
		mbc.getData().add(series);

		 // 1. �迭 ����
		XYChart.Series series2 = new XYChart.Series<>();
		Map<String , Integer> map2 = MemberDao.memberDao.datetotal("board" , "bdate");
		for(String key : map2.keySet()) {
			XYChart.Data data = new XYChart.Data<>(key, map2.get(key));
			// �ش� ������ �迭 ��ü�� �迭�� �߰�
			series2.getData().add(data);
		}
		// 4. ��Ʈ�� �迭 �߰�
		bbc.getData().add(series2);
		
		// 1. �迭 ���� 
		XYChart.Series series3 = new XYChart.Series<>();

		series3.setName("��������"); // * �迭��

		// 2. ������ ���� ( x�ప , y�ప )
		XYChart.Data data = new XYChart.Data<>("�ŵ���", 10);
		// 3. �迭�� ������ �߰�
		series3.getData().add(data);

		XYChart.Data data2 = new XYChart.Data<>("��ȣ��", 20);
		series3.getData().add(data2);

		XYChart.Data data3 = new XYChart.Data<>("���缮", 15);
		series3.getData().add(data3);

		// 4. ��Ʈ�� �迭�߰�
		pbc.getData().add(series3);
	
		// ������Ʈ - ī�װ� ����
		// ObservableList<������Ʈ ������ �ڷ���> ����Ʈ�� ����
		ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
		// DB ī�װ� �� ����
		Map<String, Integer> map4 = MemberDao.memberDao.countcategory();
		for(String key : map4.keySet()) {
			PieChart.Data temp = new PieChart.Data(key, map4.get(key));
			list.add(temp);
		}

		ppc.setData(list);
	}
	
	
	
//	// 1. �迭 ����
//	XYChart.Series series3 = new XYChart.Series<>();
//	series3.setName("��ǰ");
//	Map<String, Integer> map3 = MemberDao.memberDao.datetotal3();
//	for (String key : map3.keySet()) {
//		XYChart.Data data = new XYChart.Data<>(key, map3.get(key));
//		// �ش� ������ �迭 ��ü�� �迭�� �߰�
//		series3.getData().add(data);
//	}
//	// 4. ��Ʈ�� �迭 �߰�
//	pbc.getData().add(series3);

}
