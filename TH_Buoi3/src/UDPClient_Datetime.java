import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient_Datetime {

	public static void main(String[] args) {
		try {
			//tao udp socket
			DatagramSocket ds = new DatagramSocket();
			//dong goi udp
			byte b[] = new byte[10];
			int len = 10;
			InetAddress dc = InetAddress.getByName("127.0.0.1");
			int p = 13;
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
