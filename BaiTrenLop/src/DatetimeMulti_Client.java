import java.net.*;

public class DatetimeMulti_Client {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			//tao multicast socket
			MulticastSocket ms = new MulticastSocket(6789);
			
			//ggia nhap nhom
			InetAddress dc = InetAddress.getByName("230.0.0.1");
			ms.joinGroup(dc);
			
			//nhan goi phuc vu
			byte b[] = new byte[1000];
			DatagramPacket goinhan = new DatagramPacket(b, 1000);
			ms.receive(goinhan);
			
			//hien thi thong tin time
			byte b1[] = goinhan.getData();
			int len1 = goinhan.getLength();
			String kq = new String(b1, 0, len1);
			System.out.println("Bay gio la " + kq);
			
			//thoat nhom
			ms.leaveGroup(dc);
			
			//dong socket
			ms.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
