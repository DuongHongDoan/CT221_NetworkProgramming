import java.net.*;
import java.util.Date;

public class DatetimeMultiCast_Server {

	public static void main(String[] args) {
		try {
			//tao udp socket
			DatagramSocket ds = new DatagramSocket();
			InetAddress dc = InetAddress.getByName("230.0.0.1");
			int p = 6789;
			
			while(true) {
				//lay thong tin time
				Date d = new Date();
				String kq = d.toString();
				byte b[] = kq.getBytes();
				int len = b.length;
				
				//tao goi udp
				DatagramPacket goigui = new DatagramPacket(b, len, dc, p);
				ds.send(goigui);
				System.out.println("Da nhan");
				Thread.sleep(1000);//cho time nghi giua cac lan gui
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
