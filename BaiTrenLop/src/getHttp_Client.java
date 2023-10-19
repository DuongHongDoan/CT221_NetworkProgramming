import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class getHttp_Client {

	public static void main(String[] args) {
		try {
			//nhap dia chi web server
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap dia chi server:");
			String diachi = kb.nextLine();
			//nhap ten va duong dan tai nguyen can lay
			System.out.println("Nhap duong dan va tai nguyen:");
			String res = kb.nextLine();
			//noi ket web server
			Socket s = new Socket(diachi, 80);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			//gui cau lenh GET cho web server
			ps.println("GET " + res + " HTTP/1.1");
			ps.println("Host: " + diachi);
			ps.println("Connection: keep-alive");
			ps.println("sec-ch-ua: 'Google Chrome';v='117', 'Not;A=Brand';v='8', 'Chromium';v='117'");
			ps.println("sec-ch-ua-mobile: ?0");
			ps.println("sec-ch-ua-platform: 'Windows'");
			ps.println("Upgrade-Insecure-Requests: 1");
			ps.println("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36");
			ps.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
			ps.println("Sec-Fetch-Site: none");
			ps.println("Sec-Fetch-Mode: navigate");
			ps.println("Sec-Fetch-User: ?1");
			ps.println("Sec-Fetch-Dest: document");
			ps.println("Accept-Encoding: gzip, deflate, br");
			ps.println("Accept-Language: en-US,en;q=0.9,vi;q=0.8");
			//nhan kq tra ve tu web server
//			FileOutputStream f = new FileOutputStream("kq.html");
			while(true) {
				int ch = is.read();
				if(ch==-1) break;
				System.out.print((char)ch);
//				f.write(ch);
			}
//			System.out.println("Ghi file thanh cong");
//			f.close();
			//dong noi ket
			s.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
