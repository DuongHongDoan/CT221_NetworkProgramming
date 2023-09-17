import java.util.Scanner;
import java.io.*;
public class readFileByte {
	public static void main(String[] args) {
		try {
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap ten file:");
			String readfile = kb.nextLine();
			String writefile = kb.nextLine();
			FileInputStream rf = new FileInputStream(readfile);
			FileOutputStream wf = new FileOutputStream(writefile);
			while(true) {
				int ch = rf.read();
				if (ch==-1) break;
				wf.write(ch);
			}
			rf.close();
			wf.close();
			System.out.println("Reading file is success!");
		}
		catch (FileNotFoundException e) {
			System.out.println("Khong tim thay file!, Kiem tra lai ten file");
		}
		catch (IOException e) {
			System.out.println("Loi dong file");
		}
	}
}
