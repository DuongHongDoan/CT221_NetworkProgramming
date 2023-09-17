import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class echoClient {
	public static void main(String[] args) {
		try {
			//Connecting to Server at port 7
			Socket s = new Socket("127.0.0.1", 7);
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
				//Receiving result from Server
				int ch1 = is.read();
				//Display
				System.out.println("Nhan duoc: " + (char)ch1);
			}
			//Close connect
			s.close();
		}
		catch (IOException e) {
			System.out.println("Loi!");
		}
	}
}
