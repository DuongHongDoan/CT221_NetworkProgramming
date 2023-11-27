import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Hello extends UnicastRemoteObject implements Hello_ITF{
	public Hello() throws RemoteException {
		super();
	}
	public String sayHello() {
		return "Hello RMI";
	}
}
