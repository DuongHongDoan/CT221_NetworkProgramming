import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class getHttp_Server {

	public static void main(String[] args) {
		try {
			//khoi tao server cong 80
			ServerSocket ss = new ServerSocket(80);
			System.out.println("Server da khoi tao");
			//chap nhan noi ket
			Socket s = ss.accept();
			System.out.println("Co 1 noi ket");
			//lay 2 stream
			InputStream is = s.getInputStream();
			Scanner sc = new Scanner(is);
			OutputStream os = s.getOutputStream();
			//nhan lenh GET tu client
			System.out.println("Da nhan tu client");
			while(true) {
				String str = sc.nextLine();
				if(str.isEmpty()) break;
				System.out.println(str);
			}
			s.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
