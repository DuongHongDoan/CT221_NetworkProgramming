import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DaySo_Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(7002);
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
					String kq = "";
					try {
						int t = Integer.parseInt(str1);
						for(int i=0; i<str1.length(); i++) {
							String kq1="";
							switch(str1.charAt(i)) {
								case '0': kq1 = "Khong"; break;
								case '1': kq1 = "Mot"; break;
								case '2': kq1 = "Hai"; break;
								case '3': kq1 = "Ba"; break;
								case '4': kq1 = "Bon"; break;
								case '5': kq1 = "Nam"; break;
								case '6': kq1 = "Sau"; break;
								case '7': kq1 = "Bay"; break;
								case '8': kq1 = "Tam"; break;
								case '9': kq1 = "Chin"; break;
							}
							kq += kq1;
						}
					}catch (Exception e) {
						kq = "Khong phai so nguyen";
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
