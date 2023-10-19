import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class echoClient_UDP {

	public static void main(String[] args) {
		try {
			//tao udp socket
			DatagramSocket ds = new DatagramSocket();
			while(true) {
				//nhap chuoi str
				Scanner kb = new Scanner(System.in);
				String str = kb.nextLine();
				//kiem tra thoat
				if(str.equals("exit")) break;
				//dong goi udp
				byte b[] = str.getBytes();
				int len = b.length;
				InetAddress dc = InetAddress.getByName("127.0.0.1");
				int p = 7;
				DatagramPacket goigui = new DatagramPacket(b, len, dc, p);
				//gui goi udp qua server
				ds.send(goigui);
				//tao goi udp rong
				byte b1[] = new byte[60000];
				DatagramPacket goinhan = new DatagramPacket(b1, 60000);
				//nhan goi udp kq tu server ve
				ds.receive(goinhan);
				//lay du lieu tu goi udp ra
				byte b2[] = goinhan.getData();
				int len2 = goinhan.getLength();
				//hien thi kq ra screen
				String kq = new String(b2, 0, len2);
				System.out.println("Ket qua nhan duoc: " + kq);
			}
			//dong socket
			ds.close();
		}catch (SocketException e) {
			System.out.println(e.getMessage());
		}catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
