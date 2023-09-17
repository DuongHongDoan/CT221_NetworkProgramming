import java.util.Scanner;
public class subStringName {
	public static void main(String[] args) {
		Scanner ten = new Scanner(System.in);
		System.out.print("Nhap vao ho ten: ");
		String hoTen = ten.nextLine();
		hoTen = hoTen.trim();
		int i = hoTen.lastIndexOf(' ');
		String a = hoTen.substring(i+1, i+3);
		System.out.println(a);
	}
}
