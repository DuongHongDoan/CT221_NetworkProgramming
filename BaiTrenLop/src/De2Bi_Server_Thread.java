import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class PhucVu_De2Bi extends Thread {
	Socket s;
	public PhucVu_De2Bi (Socket s1) {
		s = s1;
	}
	public void run() {
		try {
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				//Nhan yeu cau tu client
				byte b[] = new byte[100];
				int n = is.read(b);
				String str = new String(b, 0, n);
				if(str.equals("exit")) break;
				//Xu ly yeu cau (doi so nguyen thanh nhi phan)
				String kq = new String();
				try {
					int x = Integer.parseInt(str);
					kq = Integer.toBinaryString(x);
				}catch (NumberFormatException e) {
					kq = "Khong la so nguyen";
				}
				//gui kq cho client
				os.write(kq.getBytes());
				//dong noi ket cua client
			}
			s.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

public class De2Bi_Server_Thread {

	public static void main(String[] args) {
		try {
			//tao serversocket
			ServerSocket ss = new ServerSocket(5000);
			System.out.println("Da tao mot ket noi!");
			while(true) {
				//chap nhan noi ket
				Socket s = ss.accept();
				System.out.println("Co 1 client ket noi tai dia chi " + s.getInetAddress().toString() +
						"tai cong " + s.getPort());
				PhucVu_De2Bi pv = new PhucVu_De2Bi(s);
				pv.start();
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
