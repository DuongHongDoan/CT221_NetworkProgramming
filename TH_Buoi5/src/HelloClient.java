import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class HelloClient {

	public static void main(String[] args) {
		try {
			//do tim doi tuong tu xa
			Hello_ITF ref = (Hello_ITF) Naming.lookup("rmi://127.0.0.1/HelloObject");
			//goi ham tren doi tuong
			String result = ref.sayHello();
			System.out.println("Ket qua nhan duoc la: " + result);
		}catch (NotBoundException e) {
			System.out.println("Khong tim thay doi tuong tu xa!");
		}catch (RemoteException e) {
			System.out.println("Loi goi ham tu xa!");
		} catch (MalformedURLException e) {
			System.out.println("Sai dinh dang URL!");
		}
	}

}
