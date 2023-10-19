import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient_Talk {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket();
			
			System.out.println("Nhap dia chi server:");
			Scanner kb = new Scanner(System.in);
			String diachi = kb.nextLine();
			InetAddress dc = InetAddress.getByName(diachi);
			int p = 20233;
			
			while(true) {
				//nhap tin nhan
				System.out.print("Toi: ");
				String tinGui = kb.nextLine();
				
				//kiem tra thoat cuoc tro chuyen
				if(tinGui.equals("exit")) break;
				
				//dong goi tin nhan
				byte b[] = tinGui.getBytes();
				int len = b.length;
				DatagramPacket goigui = new DatagramPacket(b, len, dc, p);
				
				//gui tin nhan di
				ds.send(goigui);
				
				//tao goi rong -> cho nhan tin nhan ve
				byte b1[] = new byte[60000];
				DatagramPacket goinhan = new DatagramPacket(b1, b1.length);
				
				//nhan goi tin ve
				ds.receive(goinhan);
				
				//mo goi nhan ra
				byte b3[] = goinhan.getData();
				int len3 = goinhan.getLength();
				String tinNhan = new String(b3, 0, len3);
				System.out.println("friend: " + tinNhan);
			}
			ds.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
