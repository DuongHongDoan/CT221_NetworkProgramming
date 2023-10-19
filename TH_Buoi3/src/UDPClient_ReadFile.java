import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient_ReadFile {

	public static void main(String[] args) {
		try {
			//tao ket noi udp
			DatagramSocket ds = new DatagramSocket();
			
			//nhap ten file can de lay va luu
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap ten file can lay:");
			String tenfilelay = kb.nextLine();
			System.out.println("Nhap ten file luu:");
			String tenfileluu = kb.nextLine();
			
			//dong goi udp yeu cau can gui di
			String yeucau = "READUDP " + tenfilelay;
			byte b[] = yeucau.getBytes();
			int len = b.length;
			System.out.println("Nhap dia chi server:");
			String dc = kb.nextLine();
			InetAddress diachi = InetAddress.getByName(dc);
			int p = 3000;
			DatagramPacket goigui = new DatagramPacket(b, len, diachi, p);
			
			//gui goi udp qua server
			ds.send(goigui);
			
			//tao goi udp rong -> chuan bi nhan goi udp kq tu server ve
			byte b1[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b1, b1.length);
			
			//nhan goi udp tu server gui ve
			ds.receive(goinhan);
			
			//mo goi udp ra va lay du lieu da duoc xu ly
			byte b2[] = goinhan.getData();
			int len2 = goinhan.getLength();
			if(len2 > 0) {
				//luu kq vao file luu
				FileOutputStream f = new FileOutputStream(tenfileluu);
				f.write(b2, 0, len2);
				f.close();
				System.out.println("Da ghi file thanh cong...");
			}else {
				System.out.println("File khong ton tai:<");
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
