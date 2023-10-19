import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class infoIP_Client {

	public static void main(String[] args) {
		try {
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap dia chi server de ket noi:");
			String diachi = kb.nextLine();
			Socket s = new Socket(diachi, 4000);
			System.out.println("Da nhan ket noi tu server " + s.getInetAddress());
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				System.out.println("\nNhap vao 1 dia chi IP:");
				String ipAddr = kb.nextLine();
				os.write(ipAddr.getBytes());
				if(ipAddr.equals("exit")) break;
				byte b[] = new byte[100];
				int n = is.read(b);
				String kq = new String(b, 0, n);
				System.out.println("\nCau tra loi: " + kq);
			}
			s.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
