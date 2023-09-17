import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
class threadServer extends Thread {
	Socket s;
	public threadServer(Socket s1) {
		s = s1;
	}
	public void run () {
		try {
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				byte b[] = new byte[50];
				int n = is.read(b);
				String str = new String(b, 0, n);
				System.out.println("Chuoi ho ten nhan duoc: " + str);
				if(str.equals("exit")) break;
				str = str.trim();
				int i = str.lastIndexOf(' ');
				String ten = str.substring(i+1);
				os.write(ten.getBytes());
			}
			s.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
public class subString_Server_Thread {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(3000);
			while(true) {
				Socket s = ss.accept();
				System.out.println("Co mot client " + s.getInetAddress() + " ket noi tai cong "
						           + s.getPort());
				
				threadServer ts = new threadServer(s);
				ts.start();
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
