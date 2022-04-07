package application.Day26;

import java.net.InetAddress;

public class Day26_1 {

	// 네트워크 : 두 대 이상의 컴퓨터들을 연결하고 통신할 수 있게 하는 것
		// 통신망 : 전자 신호를 통해 통신하는 모든 기기가 서로 통신하기 위한 하나의 망
			// 프로토콜 : 컴퓨터 내부/컴퓨터사 데이터 교환 방식을 정의하는 규칙
				// SMTP : 메일 전송 프로토콜
				// IP : 인터넷 프로토콜
	// TCP/IP
		// TCP : Transmission Control Protocol : 통신 제어
		// IP : Internet Protocol address : 통신 인식 번호
			// 우리집 <----우편----> 다른 집
			// 					집 주소
			// 집PC <---------> 다른PC
			//					www.naver.com [도메인 주소]
	
	public static void main(String[] args) {
		// 현재 pc의 ip 주소 확인
			// cmd -> ipconfig
		// 자바에서 ip 주소 확인
		try {
		InetAddress inetAddress = InetAddress.getLocalHost();
			// InetAddress 클래스
				// InetAddress.getLocalHost(); 로컬(현pc) 정보 호출
					// 자바 외 통신할 경우 일반 예외 발생 -> try-catch
		System.out.println("현재 PC 네트워크 객체 : " + inetAddress);
		System.out.println("현재 PC 이름 : " + inetAddress.getHostName());
		System.out.println("현재 PC 주소 : " + inetAddress.getHostAddress());
		
		// 네이버의 ip 주소 확인
		InetAddress inetAddress2 = InetAddress.getByName("www.naver.com");
		System.out.println("네이버 PC 네트워크 객체 : " + inetAddress2);
		System.out.println("네이버 PC 이름 : " + inetAddress2.getHostName());
		System.out.println("네이버 PC 주소 : " + inetAddress2.getHostAddress());
		
		// 페이스북 ip 주소 확인
		InetAddress inetAddress3 = InetAddress.getByName("www.facebook.com");
		System.out.println("페이스북 PC 네트워크 객체 : " + inetAddress3);
		System.out.println("페이스북 PC 이름 : " + inetAddress3.getHostName());
		System.out.println("페이스북 PC 주소 : " + inetAddress3.getHostAddress());
		}catch(Exception e){
			
		}
		
	}
}
