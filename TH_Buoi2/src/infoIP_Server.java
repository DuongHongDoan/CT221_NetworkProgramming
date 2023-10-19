import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class infoIP_Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(4000);
			System.out.println("Da tao mot ket noi!");
			while(true) {
				Socket s = ss.accept();
				System.out.println("Server da nhan ket noi tu client tai dia chi " + s.getInetAddress()
						           + " tai cong " + s.getPort());
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				while(true) {
					byte b[] = new byte[100];
					int n = is.read(b);
					String ipAddr = new String(b, 0, n);
					System.out.println("Chuoi IP nhan duoc la: " + ipAddr);
					if(ipAddr.equals("exit")) break;
					ipAddr = ipAddr.trim();
					String x = "";
					try {
						InetAddress [] addrs = InetAddress.getAllByName(ipAddr);
						String ipAddrs = "";
						for(InetAddress addr : addrs){
							ipAddrs += addr;
						}
						int i = ipAddrs.indexOf('.');
						int lop = Integer.parseInt(ipAddrs.substring(1, i));
						if(lop>=1 && lop<=126) {
							x = "\nDia chi " + ipAddr + " la thuoc lop A";
						}
						else if (lop==127){
							x = "\nDia chi " + ipAddr + " la dia chi Loopback";
						}
						else if (lop>=128 && lop<=191){
							x = "\nDia chi " + ipAddr + " la thuoc lop B";
						}
						else if (lop>=192 && lop<=223){
							x = "\nDia chi " + ipAddr + " la thuoc lop C";
						}
						else if (lop>=224 && lop<=239){
							x = "\nDia chi " + ipAddr + " la thuoc lop D";
						}
						else if (lop>=240 && lop<=255){
							x = "\nDia chi " + ipAddr + " la thuoc lop E";
						}
						else {
							x = "\nDia chi " + ipAddr + " khong ton tai!";
						}
					}catch(NumberFormatException e){
						x = "\nKhong la dia chi IP!";
					}catch(UnknownHostException e){
						x = "\nKhong la dia chi IP!";
					}
					os.write(x.getBytes());
				}
				s.close();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
