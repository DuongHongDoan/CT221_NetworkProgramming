import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client_Thread {
    private DatagramSocket clientSocket;
    private InetAddress serverAddress;
    private int serverPort;

    public Client_Thread(String serverAddress, int serverPort) throws IOException {
        clientSocket = new DatagramSocket();
        this.serverAddress = InetAddress.getByName(serverAddress);
        this.serverPort = serverPort;
    }

    public void run() {
        Thread sendMessageThread = new Thread(() -> {
            try {
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.print("Enter message: ");
                    String message = scanner.nextLine();

                    byte[] sendData = message.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                    clientSocket.send(sendPacket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread receiveMessageThread = new Thread(() -> {
            try {
                while (true) {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);

                    String message = new String(receivePacket.getData()).trim();
                    System.out.println("Received message: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        sendMessageThread.start();
        receiveMessageThread.start();
    }

    public static void main(String[] args) {
        try {
            Client_Thread client = new Client_Thread("localhost", 5000);
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}