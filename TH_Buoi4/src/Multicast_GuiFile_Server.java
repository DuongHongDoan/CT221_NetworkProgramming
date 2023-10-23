import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Multicast_GuiFile_Server {

	public static void main(String[] args) {
		try {
			// tao datagramsocket, diachi cua nhom multicast va cong client truy cap
			DatagramSocket ds = new DatagramSocket();
			InetAddress diachi = InetAddress.getByName("231.2.3.4");
			int port = 23456;

			// nhap ten file tren server
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap ten file can gui:");
			String tenfile = kb.nextLine();

			while (true) {
				// tao file cho tenfile
				File f = new File(tenfile);
				int filelen = (int) f.length(); // lay ra do dai file

				// tien hanh doc file ra de gui di (doc bang mang byte)
				FileInputStream fis = new FileInputStream(f);
				byte readFile[] = new byte[filelen];
				fis.read(readFile);

				// gui goi rong truoc
				byte b1[] = new byte[0];
				DatagramPacket goiRong = new DatagramPacket(b1, 0, diachi, port);
				ds.send(goiRong);
				System.out.println("Gui goi dau...");

				// gui goi n-1
				int fileDu = filelen / 60000; // tinh so file co du 60000byte
				if (filelen % 60000 != 0)
					fileDu++; // kiem tra file co so byte le khong, neu le thi tang fileEnough len 1

				byte b2[] = new byte[60000];
				for (int i = 0; i < fileDu - 1; i++) {
					// copy cac goi
					for (int j = 0; j < 60000; j++)
						b2[j] = readFile[i * 60000 + j];
					DatagramPacket goiTiepTheo = new DatagramPacket(b2, 60000, diachi, port);
					ds.send(goiTiepTheo);
					System.out.println("Gui goi thu " + (i+1));
				}

				// gui goi con lai cuoi cung
				int fileThua = filelen - ((fileDu - 1) * 60000);
				byte b3[] = new byte[60000];
				for (int x = 0; x < fileThua; x++) {
					b3[x] = readFile[((fileDu - 1) * 60000) + x];
				}
				DatagramPacket goiCuoi = new DatagramPacket(b3, 60000, diachi, port);
				ds.send(goiCuoi);
				System.out.println("Gui goi cuoi...");

				// thoi gian nghi giua cac lan gui
				Thread.sleep(1000);
			}
		} catch (SocketException e) {
			System.out.println("Loi Socket!");
		} catch (UnknownHostException e) {
			System.out.println("Loi dia chi server!");
		} catch (FileNotFoundException e) {
			System.out.println("Loi doc file!");
		} catch (IOException e) {
			System.out.println("Loi send - read file!");
		} catch (InterruptedException e) {
			System.out.println("Loi ngat ket noi sleep!");
		}
	}
}
