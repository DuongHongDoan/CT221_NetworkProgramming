import java.util.*;
import java.io.*;
import java.net.*;
public class UDPFileC {
	public static void main(String[] args) {
		try {
			System.out.print("Nhap dia chi server: ");
			Scanner nhap = new Scanner(System.in);
			String dic = nhap.nextLine();
			Scanner nhap1 = new Scanner(System.in);
			System.out.print("Nhap cong server: ");
			int cong = nhap1.nextInt();
			System.out.print("Nhap file can lay: ");
			String filelay = nhap.nextLine();
			System.out.print("Nhap ten file se duoc luu: ");
			String fileluu = nhap.nextLine();
			DatagramSocket ds = new DatagramSocket();
			String yeucau = "READUDP "+ filelay;
			byte bgui[] = yeucau.getBytes();
			InetAddress dc = InetAddress.getByName(dic);
			DatagramPacket goigui = new DatagramPacket(bgui,bgui.length,dc,cong);
			ds.send(goigui);
			byte[] bnhan = new byte[60000];
			DatagramPacket goinhan1 = new DatagramPacket(bnhan,bnhan.length);
			ds.receive(goinhan1);
			byte[] dd = goinhan1.getData();
			int dd1 = goinhan1.getLength();
			String dodai = new String(dd,0,dd1); 
			System.out.println("Do dai file: "+dodai);
			int chieudai = Integer.parseInt(dodai);
			if (chieudai == -1 ) {
				System.out.println("File khong ton tai");
			}
			else if(chieudai == 0) {
				System.out.println("file rong");
			}
			else{
				int size=0;
				FileOutputStream fluu = new FileOutputStream(fileluu);
				while(true){
					byte[] bnhan1 = new byte[60000];
					DatagramPacket goinhan = new DatagramPacket(bnhan1,bnhan1.length);
					ds.receive(goinhan);
					int len2 = goinhan.getLength();
					fluu.write(bnhan1,0,len2);
					size=size+len2;
					if(size==chieudai) break;
				}
				System.out.println("Ghi file thanh cong");
			}
			ds.close();
		}
		catch (SocketException e) {
			System.out.println(e);
		}
		catch (UnknownHostException e2) {
			System.out.println(e2);
		}
		catch (IOException e1) {
			System.out.println(e1);
		}
	}
}
