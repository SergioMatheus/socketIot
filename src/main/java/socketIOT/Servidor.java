package socketIOT;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Servidor {

	public static void main(String[] args) {

		ServerSocket servidor;
		try {
			int portaServer = 8082;
			long delay = 5000L;
			servidor = new ServerSocket(portaServer);
			System.out.println("Servidor iniciado na porta " + portaServer);
			while (servidor.isBound()) {
				Socket cliente = servidor.accept();
				System.out.println("Cliente conectado do IP " + cliente.getInetAddress().getHostAddress());
				ObjectOutputStream mensagemServidor = new ObjectOutputStream(cliente.getOutputStream());
				mensagemServidor.flush();
				mensagemServidor.writeObject("Temperatura_2 Ativado " + delay + " " + "\r\n");
				geraTemperatura(delay, cliente);
				mensagemServidor.close();
				cliente.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void geraTemperatura(long delay, Socket cliente) throws IOException {
		while (true) {
			ObjectOutputStream Temperatura = new ObjectOutputStream(cliente.getOutputStream());
			Temperatura.flush();
			Temperatura.writeObject(ThreadLocalRandom.current().nextInt(0, 100) + "\r\n");
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}