import java.util.Scanner;
import java.io.*;
public class readFile {
	public static void main(String[] args) {
		try {
			Scanner kb = new Scanner(System.in);
			System.out.print("Nhap ten file: ");
			String tenFile = kb.nextLine();
			FileInputStream f = new FileInputStream(tenFile);
			while(true) {
				int ch = f.read();
				if(ch==-1)
					break;
				System.out.print((char)ch);
			}
			f.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Khong tim thay file!, Kiem tra lai ten file");
		}
		catch (IOException e) {
			System.out.println("Loi dong file");
		}
	}
}
