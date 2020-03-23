package principal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
//				ObjectOutputStream mensagemServidor = new ObjectOutputStream(cliente.getOutputStream());
//				mensagemServidor.flush();
//				mensagemServidor.writeObject("String aleatoria enviada ao Cliente." + "\r\n");
				PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
				out.flush();
				out.println("String aleatoria enviada ao Cliente." + "\r\n");
				Scanner entrada = new Scanner(cliente.getInputStream());
				while(entrada.hasNext()) {
					System.out.println(entrada.nextLine());
				}
//				mensagemServidor.close();
//		        cliente.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
