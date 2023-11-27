import java.util.*;
import java.io.*;
import java.net.*;
public class MultiS {
	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket();
			InetAddress dc = InetAddress.getByName("230.0.0.1");
			int p=2000;
			int dem=0;
			while(true) {
				Date d = new Date();
				String kq = d.toString();
				byte[] b = kq.getBytes();
				int len = b.length;
				DatagramPacket goigui =
						new DatagramPacket(b,len,dc,p);
				ds.send(goigui);
				System.out.println("Da gui goi thu: "+ ++dem);
				Thread.sleep(10000);
			}
		}
		catch(SocketException e) {
			System.out.println(e);
		}
		catch(UnknownHostException e) {
			System.out.println(e);
		}
		catch(IOException e) {
			System.out.println(e);
		}
		catch(InterruptedException e) {
			System.out.println(e);
		}
	}
}
