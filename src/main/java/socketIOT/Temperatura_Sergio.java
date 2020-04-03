package socketIOT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Temperatura_Sergio implements Runnable {

	private Socket cliente;
	private Long delay = 2000L;
	private boolean condicaoLoop = true;

	public Temperatura_Sergio(Socket cliente) {
		super();
		this.cliente = cliente;
	}

	@Override
	public void run() {
		while (condicaoLoop) {
			try {
				BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				String mensagemCliente = entrada.readLine();
				if (validateMsgCliente(mensagemCliente)) {
					System.out.println("Comando para conectar: " + mensagemCliente);
					String[] msg = mensagemCliente.split(" ");
					if (msg[1].equalsIgnoreCase("conectar") && msg[2].equalsIgnoreCase("Temperatura_Sergio")) {
						PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
						out.flush();
						out.println("Temperatura_Sergio" + " ATIVADO " + delay / 1000);
						new Thread(new EnvioMensagemTemperatura(cliente, delay)).run();
					}
				} else {
					condicaoLoop = false;
				}
			} catch (Exception e) {
				System.out.println("DESCONECTADO");
				condicaoLoop = false;
			}
		}
	}

	private boolean validateMsgCliente(String mensagemCliente) {
		return mensagemCliente != null
				& (!mensagemCliente.contains("0.tcp.ngrok.io") || !mensagemCliente.contains("\u0015"));
	}

}
