import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient_Talk_Thread {

//    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
    	String diachi = "127.0.0.1";
		InetAddress dc = InetAddress.getByName(diachi);
        // Tạo socket UDP
        DatagramSocket socket = new DatagramSocket();

        // Tạo 2 thread chạy song song
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Thread 1 dùng cho việc nhập và gửi tin nhắn
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    // Nhập tin nhắn từ bàn phím
                    System.out.print("Ban: ");
                    String message = scanner.nextLine();

                    // Đóng gói tin nhắn và gửi cho server
                    DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), dc, SERVER_PORT);
                    try {
						socket.send(packet);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Thread 2 dùng để nhận và hiển thị tin nhắn
                while (true) {
                    // Nhận gói tin từ server
                    byte[] bytes = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                    try {
						socket.receive(packet);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

                    // Hiển thị tin nhắn lên màn hình
                    String message = new String(bytes, 0, packet.getLength());
                    System.out.println("Hang xom: "+message);
                }
            }
        });

        // Bắt đầu 2 thread
        thread1.start();
        thread2.start();
    }
}
