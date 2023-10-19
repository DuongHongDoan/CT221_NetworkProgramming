import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class UDPServer_Datetime {

	public static void main(String[] args) {
		try {
			//tao udp socket
			DatagramSocket ds = new DatagramSocket(13);
			System.out.println("Da tao mot ket noi++++");
			//tao goi udp rong
			byte b[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b, 60000);
			while(true) {
				//nhan goi udp tu client, khong phan biet client nao gui toi
				ds.receive(goinhan);
				//xu ly yeu cau
				Date d = new Date();
				String kq = d.toString();
				//dong goi kq
				byte b2[] = kq.getBytes();
				int len2 = b2.length;
				InetAddress dc = goinhan.getAddress();
				int p = goinhan.getPort();
				DatagramPacket goigui = new DatagramPacket(b2, len2, dc, p);
				//gui goi kq cho client, dia chi cua no se dc gan o trong ham send nay
				ds.send(goigui);
			}
		}catch(SocketException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
