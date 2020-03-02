package principal;

import java.io.ObjectInputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		try {
			Socket cliente = new Socket("10.8.172.12", 8082);
			ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
			String mensagemDoServidor = (String) entrada.readObject();
			System.out.println(mensagemDoServidor);
			entrada.close();
			System.out.println("Conexão encerrada");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}
}
