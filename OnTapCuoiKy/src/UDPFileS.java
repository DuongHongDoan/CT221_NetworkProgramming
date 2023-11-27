import java.io.*;
import java.net.*;
public class UDPFileS {
	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket(7777);
			byte b[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b,60000);
			System.out.println("Co ket noi den UDP");
			while(true) {
				ds.receive(goinhan);
				InetAddress dc = goinhan.getAddress();
				int p = goinhan.getPort();
				int n = goinhan.getLength();
				String yeucau = new String(b,0,n);
				String tenfile = yeucau.substring(8);
				File f = new File(tenfile);
				int len = (int)f.length();
				if(!f.exists()) {
					String n2 = "-1";
					byte[] b2 = n2.getBytes();
					DatagramPacket goigui1 = new DatagramPacket(b2,b2.length,dc,p);
					ds.send(goigui1);
				}else if( f.exists() && f.isFile() && len >0) {
					String l = Integer.toString(len);
					byte[] b2 = l.getBytes();
					DatagramPacket goigui1 = new DatagramPacket(b2,b2.length,dc,p);
					ds.send(goigui1);
					FileInputStream f1 = new FileInputStream(tenfile);
					int size = 0;
					if(len>0) {
						while(true) {
							byte b1[] = new byte[60000];
							int h = f1.read(b1);
							DatagramPacket goigui = new DatagramPacket(b1,h,dc,p);
							ds.send(goigui);
							size = size + h;
							System.out.println("Da gui: "+h);
							if(size == len ) break;
						}
						System.out.println("Da gui file thanh cong");
					}
					f1.close();
				}
			}
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
}
