import java.util.*;
import java.net.*;
import java.io.*;
public class TCPFileS{
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(7777);
		    while(true) {
				Socket s = ss.accept();
				System.out.println("Co client ket noi");
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				PrintStream ps = new PrintStream(os);
				Scanner sc = new Scanner(is);
				DataOutputStream dos = new DataOutputStream(os);
				String caulenh = sc.nextLine(); //nhan yeu cau
				String tenfile = caulenh.substring(5);
				File f = new File(tenfile);
				if (f.exists() && f.isFile()) {
					int size = (int)f.length();
					ps.println(size);//gui kich thuoc
					if(size > 0) {
						FileInputStream f1 = new FileInputStream(tenfile);
						DataInputStream dis = new DataInputStream(f1);
						byte[] b = new byte[60000];
						int len=0;
						while(true) {
							int n = dis.read(b);
							dos.write(b,0,n);
							len = len+n;
							System.out.println("Da gui: "+n);
							if(len==size) break;
						}
					}
					else if(size==0) {
						ps.println("0");
					}
				}else {
					ps.println("-1");
				}
				System.out.println("Da phan hoi thanh cong");
				s.close();
		    }
		}
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
}
