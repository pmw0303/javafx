package application.Day26;

import java.net.InetAddress;

public class Day26_1 {

	// ��Ʈ��ũ : �� �� �̻��� ��ǻ�͵��� �����ϰ� ����� �� �ְ� �ϴ� ��
		// ��Ÿ� : ���� ��ȣ�� ���� ����ϴ� ��� ��Ⱑ ���� ����ϱ� ���� �ϳ��� ��
			// �������� : ��ǻ�� ����/��ǻ�ͻ� ������ ��ȯ ����� �����ϴ� ��Ģ
				// SMTP : ���� ���� ��������
				// IP : ���ͳ� ��������
	// TCP/IP
		// TCP : Transmission Control Protocol : ��� ����
		// IP : Internet Protocol address : ��� �ν� ��ȣ
			// �츮�� <----����----> �ٸ� ��
			// 					�� �ּ�
			// ��PC <---------> �ٸ�PC
			//					www.naver.com [������ �ּ�]
	
	public static void main(String[] args) {
		// ���� pc�� ip �ּ� Ȯ��
			// cmd -> ipconfig
		// �ڹٿ��� ip �ּ� Ȯ��
		try {
		InetAddress inetAddress = InetAddress.getLocalHost();
			// InetAddress Ŭ����
				// InetAddress.getLocalHost(); ����(��pc) ���� ȣ��
					// �ڹ� �� ����� ��� �Ϲ� ���� �߻� -> try-catch
		System.out.println("���� PC ��Ʈ��ũ ��ü : " + inetAddress);
		System.out.println("���� PC �̸� : " + inetAddress.getHostName());
		System.out.println("���� PC �ּ� : " + inetAddress.getHostAddress());
		
		// ���̹��� ip �ּ� Ȯ��
		InetAddress inetAddress2 = InetAddress.getByName("www.naver.com");
		System.out.println("���̹� PC ��Ʈ��ũ ��ü : " + inetAddress2);
		System.out.println("���̹� PC �̸� : " + inetAddress2.getHostName());
		System.out.println("���̹� PC �ּ� : " + inetAddress2.getHostAddress());
		
		// ���̽��� ip �ּ� Ȯ��
		InetAddress inetAddress3 = InetAddress.getByName("www.facebook.com");
		System.out.println("���̽��� PC ��Ʈ��ũ ��ü : " + inetAddress3);
		System.out.println("���̽��� PC �̸� : " + inetAddress3.getHostName());
		System.out.println("���̽��� PC �ּ� : " + inetAddress3.getHostAddress());
		}catch(Exception e){
			
		}
		
	}
}
