import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPServer_Talk {

	public static void main(String[] args) {
		try {
			//tao noi ket
			DatagramSocket ds = new DatagramSocket(20233);
			System.out.println("Da tao noi ket+++");
			
			//tao goi rong -> nhan du lieu tu client
			byte b[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b, b.length);
			
			Scanner kb = new Scanner(System.in);
			while(true) {

				//nhan tinGui tu client
				ds.receive(goinhan);
				
				//mo goi nhan ra
				byte b1[] = goinhan.getData();
				int len1 = goinhan.getLength();
				String tinNhan = new String(b1, 0, len1);
				System.out.println("friend: " + tinNhan);
				
				//kiem tra dung
				if(tinNhan.equals("exit")) break;
				
				//lay dia chi va cong cua goi nhan
				InetAddress diachi = goinhan.getAddress();
				int p = goinhan.getPort();
				
				//nhap 1 tin nhan
				System.out.print("Toi: ");
				String tinGui = kb.nextLine();
				byte b2[] = tinGui.getBytes();
				int len2 = b2.length;
				DatagramPacket goigui = new DatagramPacket(b2, len2, diachi, p);
				ds.send(goigui);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
