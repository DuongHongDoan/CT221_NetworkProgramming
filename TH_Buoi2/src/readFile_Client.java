import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class readFile_Client {

	public static void main(String[] args) {
		try {
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap dia chi server: ");
			String diachi = kb.nextLine();
			Socket s = new Socket (diachi, 3001);
			System.out.println("Da tao mot ket noi den server: " + s.getInetAddress());
			
			InputStream is = s.getInputStream();
			Scanner sc = new Scanner(is);
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			
			System.out.println("Nhap duong dan den file can doc:");
			String tenFileDoc = kb.nextLine();
			System.out.println("Nhap duong dan file kq tra ve tu server:");
			String tenFileGhi = kb.nextLine();
			
			String caulenh = "READ " + tenFileDoc;
			ps.println(caulenh);
			
			String str = sc.nextLine();
			int size = Integer.parseInt(str);
			
			if(size == -1)
				System.out.println("File khong ton tai");
			else if(size == 0)
				System.out.println("File rong");
			else {
				FileOutputStream f = new FileOutputStream(tenFileGhi);
				DataInputStream dis = new DataInputStream(is);
				DataOutputStream dos = new DataOutputStream(f);
				byte b[] = new byte[size];
				int len=0;
				while(true) {
					int n = dis.read(b);
					dos.write(b, 0, n);
					len += n;
					if(len==size) break;
				}
				f.close();
				System.out.println("Ghi file thanh cong");
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
