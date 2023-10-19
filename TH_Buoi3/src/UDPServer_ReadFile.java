import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer_ReadFile {

	public static void main(String[] args) {
		try {
			//tao ket noi udp
			DatagramSocket ds = new DatagramSocket(3000);
			
			//tao goi udp rong -> nhan yeu cau tu client
			byte b[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b, b.length);
			
			//tao loop cho server luoon status hoat dong
			while(true) {
				//nhan goi gui tu client
				ds.receive(goinhan);
				
				//mo goi nhan va lay du lieu ra de xu ly
				byte b1[] = goinhan.getData();
				int len1 = goinhan.getLength();
				String yeucau = new String(b1, 0, len1); //doc du lieu 
				//lay ra dia chi va cong cua chu goi da nhan
				InetAddress diachi = goinhan.getAddress();
				int p = goinhan.getPort();
				
				//xu ly yeu cau
				String tenfile = yeucau.substring(8);
				File f = new File(tenfile);
				int n = (int) f.length();
				if(f.isFile() && f.exists()) {
					FileInputStream f1 = new FileInputStream(tenfile);
					byte b2[] = new byte[n];
					f1.read(b2);
					f1.close();
					DatagramPacket goigui = new DatagramPacket(b2, n, diachi, p);
					ds.send(goigui);
				}else {
					byte b2[] = new byte[10];
					int len2 = 0;
					DatagramPacket goigui = new DatagramPacket(b2, len2, diachi, p);
					ds.send(goigui);
				}
			}
		}catch (SocketException e) {
			System.out.println(e.getMessage());
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
