package socketIOT;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {

		ServerSocket servidor;
		try {
			int portaServer = 8082;
			long delay = 50L;
			servidor = new ServerSocket(portaServer);
			System.out.println("Servidor iniciado na porta " + portaServer);
			while (servidor.isBound()) {
				Socket cliente = servidor.accept();
				System.out.println("Cliente conectado do IP " + cliente.getInetAddress().getHostAddress());
				PrintWriter mensagemServidor = new PrintWriter(cliente.getOutputStream(),true);
				mensagemServidor.flush();
				mensagemServidor.println("Temperatura Ativado " + delay + " " + "\r\n");
				new Thread(new EnvioMensagemTemperatura(cliente, delay)).run();
				mensagemServidor.close();
				cliente.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}