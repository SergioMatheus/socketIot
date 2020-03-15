package socketIOT;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Cliente {

	public static void main(String[] args) {
		try {
			System.out.println("Digite o comando que deseja para Conectar: ");
			Scanner sc = new Scanner(System.in);
			String texto = sc.nextLine();
			String[] splited = texto.split("\\s+");
			if (texto.toLowerCase().contains("conectar")) {

				JSONParser jsonParser = new JSONParser();

				try (FileReader reader = new FileReader("lista_dispositivos.json")) {
					Object obj = jsonParser.parse(reader);
					JSONObject objeto = (JSONObject) obj;
					JSONObject conector = (JSONObject) objeto.get(splited[2]);

					String ipSelecionado = (String) conector.get("ip");

					Long portaSelecionada = (Long) conector.get("porta");

					Socket cliente = new Socket(ipSelecionado, portaSelecionada.intValue());

					while (true) {
						ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
						String mensagemDoServidor = (String) entrada.readObject();
						System.out.println(mensagemDoServidor);
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("Comando para conectar invalido");
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}