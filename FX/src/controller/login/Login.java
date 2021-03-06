package controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import dto.Member;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Login implements Initializable {
	
	public static Login instance;
	public Login() {
		instance = this;
	}
	
	// 로그인 성공한 회원객체
	public static Member member;

	@FXML
	private MediaView mediaview;// fxid
	
	@FXML
	private BorderPane borderpane; //fxid
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
//		 1. 동영상 삽입하기
			// 1. 동영상파일 객체화
			Media media = new Media( getClass().getResource("/img/login.mp4").toString());
			// 2. 미디어플레이어 객체에 동영상 넣기 
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			// 3. 미디어뷰에 미디어플레이어 넣기 
			mediaview.setMediaPlayer(mediaPlayer);
			// 4. 미디어플레이어 시작
			mediaPlayer.play();
			
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				
				@Override
				public void run() {
					mediaPlayer.seek(Duration.ZERO);
					
				}
			});
			loadpage("/view/login/loginpane.fxml");
			
			
			
	}
	
	public void loadpage( String page ) {
		try {
			Parent parent = FXMLLoader.load( getClass().getResource(page) );
			borderpane.setCenter(parent);
		}catch( Exception e ) { System.out.println("해당 파일이 없습니다. "+e);}
		
		
	}
	
	
	
}










