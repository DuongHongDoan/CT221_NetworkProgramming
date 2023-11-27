import java.net.MalformedURLException;
import java.rmi.*;

public class HelloServer {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		if(System.getSecurityManager() == null) 
			System.setSecurityManager(new RMISecurityManager());
		try {
			//tao doi tuong cho phep goi ham tu xa
			Hello obj = new Hello();
			System.out.println("Tao duoc doi tuong cho phep goi tu xa");
			
			//dang ky doi tuong 
			Naming.rebind("HelloObject", obj);
			System.out.println("Da dang ky goi doi tuong tu xa thanh cong!");
		}catch (RemoteException e) {
			System.out.println("Loi khoi tao doi tuong tu xa!");
		}catch (MalformedURLException e) {
			System.out.println("Loi dang ky doi tuong tu xa");
		}
	}
}
