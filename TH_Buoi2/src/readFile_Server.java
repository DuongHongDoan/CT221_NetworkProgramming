import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class readFile_Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(3001);
			System.out.println("Server da duoc khoi dong!");
			while(true) {
				Socket s = ss.accept();
				System.out.println("Da nhan noi ket tu client " + s.getInetAddress()
				                   + " tai cong " + s.getPort());
				InputStream is = s.getInputStream();
				Scanner sc = new Scanner(is);
				OutputStream os = s.getOutputStream();
				PrintStream ps = new PrintStream(os);
				
				DataOutputStream dos = new DataOutputStream(os);
				String caulenh = sc.nextLine();
				String tenFile = caulenh.substring(5);
				System.out.println("File can xu ly:" + tenFile);
				
				File f = new File(tenFile);
				if(f.exists() && f.isFile()) {
					int size = (int)f.length();
					ps.println(size);
					if(size > 0) {
						FileInputStream f1 = new FileInputStream(tenFile);
						DataInputStream dis = new DataInputStream(f1);
						byte b[] = new byte[size];
						int len = 0;
						while(true) {
							int n = dis.read(b);
							dos.write(b, 0, n);
							len += n;
							System.out.println("Da gui duoc: " + n + " byte");
							if(len == size) break;
						}
					}
					else if (size==0)
						ps.println("0");
				}else
					ps.println("-1");
				System.out.println("Chuyen noi dung file thanh cong");
				s.close();
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
