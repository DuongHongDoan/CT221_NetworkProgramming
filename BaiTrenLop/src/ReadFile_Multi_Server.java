import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ReadFile_Multi_Server {

	public static void main(String[] args) {
		try {
			//noi ket tcp server
			ServerSocket ss = new ServerSocket(20240);
			while(true) {
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				PrintStream ps = new PrintStream(os);
				Scanner sc = new Scanner(is);
				DataOutputStream dos = new DataOutputStream(os);
				
				//nhan cau lenh READ tu client
				String caulenh = sc.nextLine();
				String tenfile = caulenh.substring(5);
				File f = new File(tenfile);
				if(f.exists() && f.isFile()) {
					int size = (int) f.length();
					ps.println(size); //gui qua client kich thuoc file
					if(size > 0) {
						//doc noi dung file vao
						FileInputStream f1 = new FileInputStream(tenfile); // tao file de doc
						DataInputStream dis = new DataInputStream(f1);
						byte b[] = new byte[size];
						dis.readFully(b); // doc het noi dung file vao mang b
						dos.write(b); // gui file qua client
						f1.close();
					}
					
				}
				else {
					ps.println("-1");
				}
				s.close();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
