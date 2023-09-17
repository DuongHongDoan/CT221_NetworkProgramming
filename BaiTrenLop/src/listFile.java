import java.util.Scanner;
import java.io.*;
public class listFile {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Nhap vao ten thu muc:");
		String dictName = kb.nextLine();
		File f = new File(dictName);
		if(f.exists()) {
			if(f.isDirectory()) {
				String ds[] = f.list();
				for (int i=0; i<ds.length; i++) {
					File f2 = new File(dictName + "/" + ds[i]);
					if(f2.isFile()) {
						System.out.println(ds[i]);
					}
					else {
						System.out.println("[" + ds[i] + "]");
					}
				}
			}
			else {
				System.out.println("It is not directory!");
			}
		}
		else {
			System.out.println("Directory is not exist!");
		}
	}
}
