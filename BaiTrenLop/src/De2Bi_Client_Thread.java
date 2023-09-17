import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class De2Bi_Client_Thread {

	public static void main(String[] args) {
		try {
			//nhap dia chi tu ban phim
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap vao dia chi server:");
			String diachi = kb.nextLine();
			//Tao socket
			Socket s = new Socket(diachi, 5000);
			//Input, output
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				//Nhap ban phim 1 so nguyen
				System.out.println("Nhap 1 so nguyen:");
				String str = kb.nextLine();
				//ghi qua server
				os.write(str.getBytes());
				if(str.equals("exit")) break;
				//Nhan kq
				byte b[] = new byte[100];
				int n = is.read(b);//n la so byte(so ky tu nhan duoc)
				//Hien thi kq
				String str1 = new String(b, 0, n);
				System.out.println("Kq nhan duoc la: " + str1);
			}
			//dong noi ket
			s.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
