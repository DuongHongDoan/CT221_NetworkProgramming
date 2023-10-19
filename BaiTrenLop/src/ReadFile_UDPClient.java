import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ReadFile_UDPClient {

	public static void main(String[] args) {
		try {
			//tao udp socket
			DatagramSocket ds = new DatagramSocket();
			//nhap chuoi str
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap ten file can lay:");
			String tenfile = kb.nextLine();
			System.out.println("Nhap ten file luu:");
			String tenfileluu = kb.nextLine();
			//dong goi udp
			String yeucau = "READUDP " + tenfile;
			byte b[] = yeucau.getBytes();
			int len = b.length;
			InetAddress dc = InetAddress.getByName("127.0.0.1");
			int p = 5000;
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
			if(len2 > 0) {
				//luu kq vao file luu
				FileOutputStream f = new FileOutputStream(tenfileluu);
				f.write(b2);
				f.close();
				System.out.println("Da ghi file thanh cong...");
			}
			else {
				System.out.println("File khong ton tai");
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
