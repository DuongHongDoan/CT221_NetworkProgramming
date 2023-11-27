import java.util.*;
import java.net.*;
import java.io.*;
public class TCPFileC {
	public static void main(String[] args) {
		try {
			Scanner nhap = new Scanner(System.in);
			Scanner nhap1 = new Scanner(System.in);
			System.out.print("Nhap dia chi server: ");
			String address = nhap.nextLine();
			System.out.print("Nhap cong ket noi: ");
			int port = nhap1.nextInt();
			System.out.println("Nhap ten file: ");
			String filelay = nhap.nextLine();
			System.out.println("Nhap ten file luu: ");
			String fileluu = nhap.nextLine();
			Socket s = new Socket(address,port);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			Scanner sc = new Scanner(is);
			String caulenh = "READ "+ filelay;
			ps.println(caulenh);
			String str = sc.nextLine();
			int size = Integer.parseInt(str);
			if(size < 0) {
				System.out.println("File khong ton tai !");
			}else if(size==0){
				//neu file rong thi minh chi can tao ra file muon luu
				FileOutputStream f = new FileOutputStream(fileluu);
				System.out.println("Da copy file "+filelay+ " rong !");
			}else {
				FileOutputStream f = new FileOutputStream(fileluu);
				DataOutputStream dos = new DataOutputStream(f);
				DataInputStream dis = new DataInputStream(is);
				byte b[] = new byte[60000];
				int len=0;
				while(true) {
					int n=dis.read(b);
					dos.write(b,0,n);
					len=len+n;
					System.out.println("Da ghi: "+n);
					if(len==size) break;
				}
			f.close();
			System.out.println("Ghi file thanh cong: ");
			}
			s.close();//dong ket noi
		}
		catch(UnknownHostException e) {
			System.out.println(e);
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}
