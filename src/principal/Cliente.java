package principal;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		try {
			Socket cliente = new Socket("10.8.172.14", 8082);
			ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
//			PrintWriter saida = new PrintWriter(cliente.getOutputStream(),true);
			String mensagemDoServidor = (String) entrada.readObject();
			System.out.println(mensagemDoServidor);
			Scanner sc = new Scanner(cliente.getInputStream());
			while(sc.hasNextLine()) {
//				saida.flush();
//				saida.print(sc.nextLine());
			}
//			cliente.close();
//			System.out.println("Conexão encerrada");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}
}