package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Cliente {
	public static void main(String args[]) {
		try {
			String host = null;
			int porta = 0;
			System.out.println("Rodando Código");
			host = "localhost";
			porta = 8082;

			Socket socket = new Socket(host, porta);
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String responseLine = is.readLine();
			System.out.println("Mensagem do Servidor: " + responseLine);

			while (true) {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
				String userInput = userInputBR.readLine();
				if (userInput != null) {
					out.flush();
					out.println(userInput + "\r\n");
				}
			}
		} catch (SocketException e) {
			System.out.println("!!Conexao Encerrada!!");
		} catch (IOException e) {
			System.out.println("Erro de conexao");
		}
	}
}
