import java.util.*;
import java.net.*;
import java.io.*;
public class ParallelC {
	public static void main(String[] args) {
	try {
			Scanner nhap = new Scanner(System.in);
			Scanner nhap1 = new Scanner(System.in);
			System.out.print("Nhap dia chi server: ");
			String address = nhap.nextLine();
			System.out.print("Nhap cong server: ");
			int port = nhap1.nextInt();
			Socket s = new Socket(address,port);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			Scanner sc = new Scanner(is);
			while(true) {
				System.out.println("Nhap chuoi can dao: ");
				String str = nhap.nextLine();
				ps.println(str);
				if(str.equals("EXIT")) break;
				String kq = sc.nextLine();
				System.out.println("Chuoi da duoc dao: "+kq);
			}
			s.close();
	}
	catch(UnknownHostException e) {
		System.out.println(e);
	}
	catch(IOException e1) {
		System.out.println(e1);
	}
	}
}
