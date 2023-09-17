import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class De2Bi_Client {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 7001);
			System.out.println("Da ket noi voi Server");
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			Scanner kb = new Scanner(System.in);
			while(true) {
				System.out.println("Nhap vao 1 chuoi so nguyen:");
				String str = kb.nextLine();
				byte str1[] = str.getBytes();
				os.write(str1);
				if (str.equals("@")) break;
				byte b[] = new byte[100];
				int n = is.read(b);
				String kq = new String(b, 0, n);
				System.out.println("Nhan duoc: " + kq);
			}
			s.close();
		}
		catch (IOException e) {
			System.out.println("Loi!");
		}
	}

}
