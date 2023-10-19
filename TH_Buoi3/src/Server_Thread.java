import java.io.IOException;
	import java.net.DatagramPacket;
	import java.net.DatagramSocket;
	import java.net.InetAddress;
	import java.util.ArrayList;
	import java.util.List;

	public class Server_Thread {
	    private DatagramSocket serverSocket;
	    private List<InetAddress> clientAddresses;
	    private List<Integer> clientPorts;

	    public Server_Thread(int port) throws IOException {
	        serverSocket = new DatagramSocket(port);
	        clientAddresses = new ArrayList<>();
	        clientPorts = new ArrayList<>();
	    }

	    public void run() throws IOException {
	        System.out.println("Chat Server is running...");

	        while (true) {
	            byte[] receiveData = new byte[1024];
	            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	            serverSocket.receive(receivePacket);

	            String message = new String(receivePacket.getData()).trim();
	            InetAddress clientAddress = receivePacket.getAddress();
	            int clientPort = receivePacket.getPort();

	            if (!clientAddresses.contains(clientAddress)) {
	                clientAddresses.add(clientAddress);
	                clientPorts.add(clientPort);
	            }

	            sendToAllClients(message, clientAddress, clientPort);
	            System.out.println("Received message: " + message);
	        }
	    }

	    private void sendToAllClients(String message, InetAddress senderAddress, int senderPort) throws IOException {
	        for (int i = 0; i < clientAddresses.size(); i++) {
	            InetAddress clientAddress = clientAddresses.get(i);
	            int clientPort = clientPorts.get(i);

	            if (!(clientAddress.equals(senderAddress) && clientPort == senderPort)) {
	                byte[] sendData = message.getBytes();
	                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
	                serverSocket.send(sendPacket);
	            }
	        }
	    }

	    public static void main(String[] args) {
	        try {
	            Server_Thread server = new Server_Thread(5000);
	            server.run();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

