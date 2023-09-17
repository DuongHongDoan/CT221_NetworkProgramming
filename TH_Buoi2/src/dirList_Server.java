import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class dirList_Server {

	public static void main(String[] args) {
		try {
			//tao serversocket
			ServerSocket ss = new ServerSocket(5001);
			while(true) {
				//chap nhan noi ket
				Socket s = ss.accept();
				//lay 2 stream in out
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				//Doi thanh 2 stream lop con ho tro gui nhan chuoi
				Scanner sc = new Scanner(is);
				PrintStream ps = new PrintStream(os);
				//nhan yeu cau (cau lenh LIST)
				String caulenh = sc.nextLine();
				//xu ly yeu cau
				String thumuc = caulenh.substring(5); //lay ten thu muc, bo qua cau lenh LIST phia truoc
				//gui kq cho client
				File f = new File(thumuc);
				if(f.exists() && f.isDirectory()) {
					String kq[] = f.list();
					int n = kq.length;
					ps.println("" + n);
					for(int i=0; i<n; i++) {
						File f1 = new File(thumuc + "/" + kq[i]);
						if(f1.isFile())
							ps.println(kq[i]);
						else 
							ps.println("[" + kq[i] + "]");
					}
				}else {
					ps.println("-1");
				}
				//dong noi ket
				s.close();
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
