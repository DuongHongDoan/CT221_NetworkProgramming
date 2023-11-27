import java.util.*;
import java.net.*;
import java.io.*;
class PhucVu extends Thread {
	private Socket s;
	public PhucVu(Socket s) {
		this.s = s;
	}
	public void run() {
		try {
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			Scanner sc = new Scanner(is);
			while(true) {
				String str = sc.nextLine();
				if(str.equals("EXIT")) break;
				String str1 = new String();
				int i;
				for(i=str.length()-1;i>=0;i--) {
					str1=str1+str.charAt(i);
				}
				ps.println(str1);
			}
			s.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
};

class ParallelS{	
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(7777);
			while(true) {
				Socket s = ss.accept();
				PhucVu pv = new PhucVu(s);
				pv.start();
			}
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}
