import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UDPServer_Talk_Thread {

    private static final int PORT = 9999;

    private List<ClientInfo> clients;

    public UDPServer_Talk_Thread() {
        clients = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        UDPServer_Talk_Thread server = new UDPServer_Talk_Thread();
        server.run();
    }

    private void run() throws IOException {
        // Khởi tạo socket UDP
        DatagramSocket socket = new DatagramSocket(PORT);

        // Vòng lặp lắng nghe các gói tin UDP
        while (true) {
            // Nhận gói tin UDP từ Client
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            // Lấy thông tin của Client gửi
            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            // Thêm Client vào danh sách nếu Client chưa tồn tại
            ClientInfo client = new ClientInfo(address, port);
            if (!clients.contains(client)) {
                clients.add(client);
            }

            // Lấy tin nhắn trong gói tin
            String message = new String(packet.getData(), 0, packet.getLength());

            // Gửi tin nhắn đến tất cả Client (ngoại trừ Client gửi)
            for (ClientInfo client2 : clients) {
                if (client2.address != address || client2.port != port) {
                    DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), client2.address, client2.port);
                    socket.send(sendPacket);
                }
            }
        }
    }

    private static class ClientInfo {
        private InetAddress address;
        private int port;

        public ClientInfo(InetAddress address, int port) {
            this.address = address;
            this.port = port;
        }
    }
}
