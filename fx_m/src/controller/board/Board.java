package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import dao.BoardDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Board implements Initializable{

	@FXML
	private TableView<dto.Board> boardtable; // 넣을 자료형 선택
	// fx:id 를 설정해주고 밑에서 설정한 이름으로 받아와야함
	
	public static dto.Board board;

	@FXML
	private Button btnwrite;

	@FXML
	void accwrite(ActionEvent event) {
		// * Home클래스내 borderpane center 변경
		Home.home.loadpage("/view/board/boardwrite.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// arraylist 아닌 oberservableList ** tableview 가 oberservableList 를 사용함
		// DB에서 모든 게시글 가져오기
		ObservableList<dto.Board> boardlist = BoardDao.boardDao.list();
		// tableview 에 추가
		TableColumn tc = boardtable.getColumns().get(0);	
		// TableColumn : 테이블 열 / 0번째 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("bnum"));
		
		tc = boardtable.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		
		tc = boardtable.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("bwrite"));
		
		tc = boardtable.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("bdate"));
		
		tc = boardtable.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("bview"));
	
		
		
		// tableview 에 list 연결
		boardtable.setItems(boardlist);
			// 테이블명.setItems(ObservableList) -> 테이블에 표시할 리스트 설정(*ArrayList 불가*)
		
		// tableview 에서 해당 셀을 클릭했을 때 이벤트
		boardtable.setOnMouseClicked( e -> {
			
			board = boardtable.getSelectionModel().getSelectedItem();
			
			Home.home.loadpage("/view/board/boardview.fxml");
		}); 
		// 테이블 클릭했을 때
		
		// [일회용] -> : 람다식(익명함수 : 이름 없는 함수 [인수와 실행코드만 존재])
		// vs
		// [재활용] void 메소드(인수) { 실행코드 } : 함수
	}
}
