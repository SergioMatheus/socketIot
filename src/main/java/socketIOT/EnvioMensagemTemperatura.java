package socketIOT;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class EnvioMensagemTemperatura implements Runnable {

	private Socket socket;
	private Long delay;
	private boolean condicaoLoop = true;

	public EnvioMensagemTemperatura(Socket socket, Long delay) {
		super();
		this.socket = socket;
		this.delay = delay;
	}

	public boolean isCondicaoLoop() {
		return condicaoLoop;
	}

	public void setCondicaoLoop(boolean condicaoLoop) {
		this.condicaoLoop = condicaoLoop;
	}

	@Override
	public void run() {
		while (condicaoLoop) {
			try {
				PrintWriter Temperatura = new PrintWriter(socket.getOutputStream(), true);
				Temperatura.flush();
				Temperatura.println(ThreadLocalRandom.current().nextInt(0, 60) + " oC");
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				condicaoLoop = false;
				e.printStackTrace();
			} catch (IOException e) {
				condicaoLoop = false;
				e.printStackTrace();
			}
		}
	}

}