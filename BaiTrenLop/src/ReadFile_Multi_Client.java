import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ReadFile_Multi_Client {

	public static void main(String[] args) {
		try {
			//nhap thong tin
			Scanner kb = new Scanner(System.in);
			System.out.println("NHap dia chi server:");
			String dcServer = kb.nextLine();
			System.out.println("NHap cong server:");
			int congServer = kb.nextInt();
			kb.nextLine();
			System.out.println("NHap ten file tren server:");
			String tenfile = kb.nextLine();
			System.out.println("NHap ten file can luu tren client:");
			String tenfileluu = kb.nextLine();
			
			//noi ket tcp server
			Socket s = new Socket(dcServer, congServer);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			DataInputStream dis = new DataInputStream(is);//nhan luong du lieu lon 1 cach an toan
			Scanner sc = new Scanner(is);
			
			//gui cau lenh READ qua Server
			String caulenh = "READ " + tenfile;
			ps.println(caulenh); //gui co xuong hang
			
			//nhan n la kich thuoc file
			String str = sc.nextLine();
			int size = Integer.parseInt(str);
			if(size > 0) {
				//tao file de ghi
				FileOutputStream f = new FileOutputStream(tenfileluu);
				DataOutputStream dos = new DataOutputStream(f); //chuyen fos thanh dos cho viec ghi du lieu lon 1 cachs an toan hon
				//nhan tiep size byte
				byte b[] = new byte[10000];
				int len = 0;
				
				while(true) {
					int n = dis.read(b); //nhan n byte tu server
					if(n>0) {
						dos.write(b, 0, n); //ghi vao file n byte
						len += n;
						System.out.println("Da nhan den byte thu " + len);
						if(len == size) break;
					}
				}
				f.close();
				System.out.println("Da ghi file thanh cong!");
			}
			else {
				if(size == 0)
					System.out.println(tenfile + " rong");
				else 
					System.out.println(tenfile + " khong ton tai");
			s.close();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
