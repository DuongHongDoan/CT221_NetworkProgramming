import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class echoServer {
	public static void main(String[] args) {
		try {
			//Creating server socket at port 7
			ServerSocket ss = new ServerSocket(7);
			System.out.println("Da tao noi ket!");
			while(true) {
				Socket s = ss.accept();
				System.out.println("++ Da co 1 client noi ket");
				//Get 2 stream in-out
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				while(true) {
					//Receiving connect from Client (1 char)
					int ch = is.read();
					System.out.println("Da nhan: " + (char)ch);
					//Check
					if(ch=='@') break;
					//Handle
					int ch1 = ch;
					//Sending result to Client
					os.write(ch1);
				}
				//Close connect and Close server
				s.close();
			}
		}
		catch (IOException e) {
			System.out.println("Loi + " + e);
		}
	}
}
