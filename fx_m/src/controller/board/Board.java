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
	private TableView<dto.Board> boardtable; // ���� �ڷ��� ����
	// fx:id �� �������ְ� �ؿ��� ������ �̸����� �޾ƿ;���
	
	public static dto.Board board;

	@FXML
	private Button btnwrite;

	@FXML
	void accwrite(ActionEvent event) {
		// * HomeŬ������ borderpane center ����
		Home.home.loadpage("/view/board/boardwrite.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// arraylist �ƴ� oberservableList ** tableview �� oberservableList �� �����
		// DB���� ��� �Խñ� ��������
		ObservableList<dto.Board> boardlist = BoardDao.boardDao.list();
		// tableview �� �߰�
		TableColumn tc = boardtable.getColumns().get(0);	
		// TableColumn : ���̺� �� / 0��° ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("bnum"));
		
		tc = boardtable.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		
		tc = boardtable.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("bwrite"));
		
		tc = boardtable.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("bdate"));
		
		tc = boardtable.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("bview"));
	
		
		
		// tableview �� list ����
		boardtable.setItems(boardlist);
			// ���̺��.setItems(ObservableList) -> ���̺� ǥ���� ����Ʈ ����(*ArrayList �Ұ�*)
		
		// tableview ���� �ش� ���� Ŭ������ �� �̺�Ʈ
		boardtable.setOnMouseClicked( e -> {
			
			board = boardtable.getSelectionModel().getSelectedItem();
			
			Home.home.loadpage("/view/board/boardview.fxml");
		}); 
		// ���̺� Ŭ������ ��
		
		// [��ȸ��] -> : ���ٽ�(�͸��Լ� : �̸� ���� �Լ� [�μ��� �����ڵ常 ����])
		// vs
		// [��Ȱ��] void �޼ҵ�(�μ�) { �����ڵ� } : �Լ�
	}
}
