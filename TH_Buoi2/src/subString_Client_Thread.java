import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class subString_Client_Thread {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 3000);
			System.out.println("Da ket noi voi server " + s.getInetAddress());
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				System.out.println("Nhap vao mot chuoi ho va ten:");
				Scanner kb = new Scanner(System.in);
				String str = kb.nextLine();
				os.write(str.getBytes());
				if(str.equals("exit")) break;
				byte b[] = new byte[50];
				int n = is.read(b);
				String kq = new String(b, 0, n);
				System.out.println("Ho va ten sau khi duoc cat: " + kq);
			}
			s.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
