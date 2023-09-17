import java.util.Scanner;
import java.util.InputMismatchException;
public class giaiThua {
	public static void main(String[] args) {
		while(true) {
			try {
				Scanner kb = new Scanner(System.in);
				System.out.println("Nhap vao so nguyen a: ");
				int a = kb.nextInt();
				int kq=1, i;
				if(a <= 0) {
					System.out.println("a nhap la so am, khong tinh duoc!");
					return;
				}
				else {
					for(i=1; i<=a; i++) {
						kq = kq * i;
					}
				}
				System.out.println(a + "! = " + kq);
				break;
			}
			catch (InputMismatchException e) {
				System.out.println("a nhap vao phai la so nguyen duong, vui long nhap lai!");
			}
		}
	}
}
