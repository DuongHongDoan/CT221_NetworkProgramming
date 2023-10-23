import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
//import java.util.Scanner;

public class Multicast_GuiFile_Client {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			// tao multicast socket tai cong cua server, diachi va cong cua server
			InetAddress diachi = InetAddress.getByName("231.2.3.4");
			int port = 23456;
			MulticastSocket ms = new MulticastSocket(port);

			// gia nhap vao nhom dia chi multicast
			ms.joinGroup(diachi);

			// nhap ten file luu
//			Scanner kb = new Scanner(System.in);
//			System.out.println("Nhap ten file luu:");
//			String tenfileluu = kb.nextLine();

			// tao file luu va tien hanh ghi file
			File f = new File("result");
			FileOutputStream fos = new FileOutputStream(f);
			FileInputStream fis = new FileInputStream(f);
			
//			File tmp = new File("tmp");
//			FileOutputStream tam = new FileOutputStream(tmp);
			int cnt = 0;
			
			// nhan goi tu server
			while (true) {
				byte b[] = new byte[1];
				DatagramPacket goiNhan1 = new DatagramPacket(b, 1);
				ms.receive(goiNhan1);
				int len1 = goiNhan1.getLength();
				boolean complete = false;

				while (len1 == 0) {
					System.out.println("Dang ghi file...");
					byte b1[] = new byte[60000];
					DatagramPacket goiNhan2 = new DatagramPacket(b1, b1.length);
					ms.receive(goiNhan2);
					int len2 = goiNhan2.getLength();
					if (len2 == 0) {
						complete = true;
						break;
					}
					fos.write(goiNhan2.getData());
					cnt += goiNhan2.getLength();
				}
				if (complete) {
					byte b2[] = new byte[cnt];
					fis.read(b2);
					@SuppressWarnings("resource")
					FileOutputStream tam = new FileOutputStream("kq");
					tam.write(b2, 0, cnt);
					break;
				}
			}
			ms.close();
			fis.close();
			fos.close();
			f.delete();
			System.out.println("Da luu file thanh cong!");
		} catch (UnknownHostException e) {
			System.out.println("Loi dia chi server!");
		} catch (FileNotFoundException e) {
			System.out.println("Loi doc file!");
		} catch (IOException e) {
			System.out.println("Loi send - read file!");
		}
	}

}
