import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ReadFile_UDPServer {

	public static void main(String[] args) {
		try {
			//tao udp socket
			DatagramSocket ds = new DatagramSocket(5000);
			//tao goi udp rong
			byte b[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b, 60000);
			while(true) {
				//nhan goi udp tu client, khong phan biet client nao gui toi
				ds.receive(goinhan);
				//lay du lieu ra
				byte b1[] = goinhan.getData();
				int len1 = goinhan.getLength();
				String yeucau = new String(b1, 0, len1);
//				byte b2[] = yeucau.getBytes();
//				int len2 = b2.length;
				InetAddress dc = goinhan.getAddress();
				int p = goinhan.getPort();
				//xu ly yeu cau
				String tenfile = yeucau.substring(8);
				File f = new File(tenfile);
				int n = (int) f.length();
				if (f.isFile() && f.exists()) {
					FileInputStream f1 = new FileInputStream(tenfile);
					byte b21[] = new byte[n];
					f1.read(b21);
					f1.close();
					DatagramPacket goigui = new DatagramPacket(b21, n, dc, p);
					ds.send(goigui);
				}
				else {
					//gui 1 goi du lieu voi length = 0
					byte b21[] = new byte[10];
					int len21=0;
					DatagramPacket goigui = new DatagramPacket(b21, len21, dc, p);
					ds.send(goigui);
				}
			}
		}catch(SocketException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
