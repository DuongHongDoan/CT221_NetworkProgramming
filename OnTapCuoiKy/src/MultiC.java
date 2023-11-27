import java.util.*;
import java.net.*;
import java.io.*;
public class MultiC {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			while(true) {
			MulticastSocket ms = new MulticastSocket(2000);
			InetAddress dc = InetAddress.getByName("230.0.0.1");
			ms.joinGroup(dc);
			byte b[] = new byte[1000];
			DatagramPacket goinhan = new DatagramPacket(b,b.length);
			ms.receive(goinhan);
			System.out.println("Bay gio la: "+
			new String(goinhan.getData(),0,goinhan.getLength()));
			ms.leaveGroup(dc);
			ms.close();
			}
		}
		catch(UnknownHostException e) {
			System.out.println(e);
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}
