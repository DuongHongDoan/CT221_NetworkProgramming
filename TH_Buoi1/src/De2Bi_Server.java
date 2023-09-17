import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class De2Bi_Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(7001);
			System.out.println("Da tao mot ket noi!");
			while(true) {
				Socket s = ss.accept();
				System.out.println("Da chap nhan mot ket noi!");
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				while(true) {
					byte b[] = new byte[50];
					int n = is.read(b);
					String str1 = new String(b, 0, n);
					System.out.println("Da nhan so nguyen: " + str1);
					if (str1.equals("@")) break;
					String kq="";
					try {
						int str = Integer.parseInt(str1);
						if(str>0) {
							while(str>0) {
								kq = (str % 2) + kq;
								str /= 2;
							}
						}else {
							kq = str + " la so am";
						}
					}catch (Exception e) {
						kq = "Khong la so nguyen";
					}
					os.write(kq.getBytes());
				}
				s.close();
			}
			
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
