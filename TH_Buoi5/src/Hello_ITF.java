import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello_ITF extends Remote {
	public String sayHello() throws RemoteException;
}
