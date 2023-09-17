import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class dirList_Client {

	public static void main(String[] args) {
		try {
			//nhap dia chi tu ban phim
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap vao dia chi server:");
			String diachi = kb.nextLine();
			//noi ket
			Socket s = new Socket(diachi, 5001);
			//lay 2 stream in out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			//Doi thanh 2 stream lop con ho tro gui nhan chuoi
			Scanner sc = new Scanner(is);
			PrintStream ps = new PrintStream(os);
			//Nhap ten thu muc can List
			System.out.println("NHap ten thu muc can list:");
			String thumuc = kb.nextLine();
			//gui cau lenh List
			String caulenh = "LIST " + thumuc;
			ps.println(caulenh);
			//nhan kq tra ve va hien thi
			String str = sc.nextLine(); 
			int n = Integer.parseInt(str);//nhan so luong thanh phan
			if(n==-1)
				System.out.println("Thu muc khong ton tai");
			else
				if(n==0) 
					System.out.println("Thu muc rong");
				else {
					System.out.println("Danh sach cac thanh phan thu muc: " + thumuc);
					for(int i=0; i<n; i++) {
						String kq = sc.nextLine(); // nhan tung dong
						System.out.println(kq);
					}
				}
			//dong noi ket
			s.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
