package socketIOT;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {

		ServerSocket servidor;
		try {
			int portaServer = 8082;
			servidor = new ServerSocket(portaServer);
			System.out.println("Servidor iniciado na porta " + portaServer);
			while (servidor.isBound()) {
				Socket cliente = servidor.accept();
				System.out.println("Cliente conectado do IP " + cliente.getInetAddress().getHostAddress());
				new Thread(new Temperatura_Sergio(cliente)).run();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}