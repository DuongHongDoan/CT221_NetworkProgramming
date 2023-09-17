import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class readChar_Client {
	public static void main(String[] args) {
		try {
			//Connecting to Server at port 7000
			Socket s = new Socket("127.0.0.1", 7000);
			System.out.println("Da ket noi voi Server");
			//Get 2 stream in-out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			//Enter 1 char
			Scanner kb = new Scanner(System.in);
			while(true) {
				System.out.println("Nhap vao 1 ky tu:");
				String str = kb.nextLine();
				int ch = (int)str.charAt(0);
				//Sending char to Server
				os.write(ch);
				//Check condi to exit
				if (ch=='@') break;
				//Receiving result from Server (n char)
				byte b[] = new byte[100];
				int n = is.read(b); //n la so byte nhan duoc
				//Display
				String kq = new String(b, 0, n);
				System.out.println("Nhan duoc: " + kq);
			}
			//Close connect
			s.close();
		}
		catch (IOException e) {
			System.out.println("Loi!");
		}
	}
}
