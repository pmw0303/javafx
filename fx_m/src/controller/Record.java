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
	
		// 전체 회원 수
		int mtotal = MemberDao.memberDao.counttotal("member");
		lblmtotal.setText(mtotal + " 명");
		// 전체 제품 수
		int ptotal = MemberDao.memberDao.counttotal("product");
		lblptotal.setText(ptotal + " 개");
		// 전체 게시물 수
		int btotal = MemberDao.memberDao.counttotal("board");
		lblbtotal.setText(btotal + " 개");
		
		// 날짜별 회원 가입 수
			// 데이터 생성
		XYChart.Series series = new XYChart.Series<>(); // xy축 계열 생성
		
		// DB 에서 가져오기
			// 날짜별로 회원가입 수 체크 [yyyy-MM-dd , X]
			// Map 컬렉션 -> 키 , 값으로 하나의 엔트리 구성 / 키 = 날짜 , 값 = 가입 수
		Map<String , Integer> map = MemberDao.memberDao.datetotal("member" , "msince");
	
		// 반복문
		// 데이터를 계열에 추가
		for(String key : map.keySet()) {
			XYChart.Data data = new XYChart.Data<>(key, map.get(key));
			// 해당 데이터 계열 객체를 계열에 추가
			series.getData().add(data);
		}
		// 막대차트
		mbc.getData().add(series);

		 // 1. 계열 생성
		XYChart.Series series2 = new XYChart.Series<>();
		Map<String , Integer> map2 = MemberDao.memberDao.datetotal("board" , "bdate");
		for(String key : map2.keySet()) {
			XYChart.Data data = new XYChart.Data<>(key, map2.get(key));
			// 해당 데이터 계열 객체를 계열에 추가
			series2.getData().add(data);
		}
		// 4. 차트에 계열 추가
		bbc.getData().add(series2);
		
		// 1. 계열 생성 
		XYChart.Series series3 = new XYChart.Series<>();

		series3.setName("국어점수"); // * 계열명

		// 2. 데이터 선언 ( x축값 , y축값 )
		XYChart.Data data = new XYChart.Data<>("신동엽", 10);
		// 3. 계열에 데이터 추가
		series3.getData().add(data);

		XYChart.Data data2 = new XYChart.Data<>("강호동", 20);
		series3.getData().add(data2);

		XYChart.Data data3 = new XYChart.Data<>("유재석", 15);
		series3.getData().add(data3);

		// 4. 차트에 계열추가
		pbc.getData().add(series3);
	
		// 원형차트 - 카테고리 개수
		// ObservableList<원형차트 데이터 자료형> 리스트명 선언
		ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
		// DB 카테고리 별 개수
		Map<String, Integer> map4 = MemberDao.memberDao.countcategory();
		for(String key : map4.keySet()) {
			PieChart.Data temp = new PieChart.Data(key, map4.get(key));
			list.add(temp);
		}

		ppc.setData(list);
	}
	
	
	
//	// 1. 계열 생성
//	XYChart.Series series3 = new XYChart.Series<>();
//	series3.setName("제품");
//	Map<String, Integer> map3 = MemberDao.memberDao.datetotal3();
//	for (String key : map3.keySet()) {
//		XYChart.Data data = new XYChart.Data<>(key, map3.get(key));
//		// 해당 데이터 계열 객체를 계열에 추가
//		series3.getData().add(data);
//	}
//	// 4. 차트에 계열 추가
//	pbc.getData().add(series3);

}
