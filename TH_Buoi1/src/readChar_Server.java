import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class readChar_Server {
	public static void main(String[] args) {
		try {
			//Creating server socket at port 7000
			ServerSocket ss = new ServerSocket(7000);
			System.out.println("Da tao noi ket!");
			while(true) {//phuc vu nhieu client
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
					String kq = "Khong phai so nguyen";
					switch(ch) {
						case '0': kq = "Khong"; break;
						case '1': kq = "Mot"; break;
						case '2': kq = "Hai"; break;
						case '3': kq = "Ba"; break;
						case '4': kq = "Bon"; break;
						case '5': kq = "Nam"; break;
						case '6': kq = "Sau"; break;
						case '7': kq = "Bay"; break;
						case '8': kq = "Tam"; break;
						case '9': kq = "Chin"; break;
					}
					//Sending result to Client
					os.write(kq.getBytes());
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
