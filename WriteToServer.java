package loadBalance;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


public class WriteToServer implements Runnable {

	BufferedOutputStream serverBufOutStream;
	BufferedInputStream clientBufInStream;
	Socket clientSock;
	Socket serverSock;

	public WriteToServer(Socket clientSocket, Socket serverSocket) {
		super();
		this.clientSock = clientSocket;
		this.serverSock = serverSocket;
		
		
		try {
			
			BufferedInputStream clientBufInStream = new BufferedInputStream(clientSock.getInputStream());
			BufferedOutputStream serverBufOutStream = new BufferedOutputStream(serverSock.getOutputStream());
			this.serverBufOutStream = serverBufOutStream;
			this.clientBufInStream = clientBufInStream;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (!Thread.interrupted()) {
			try {
				int message = clientBufInStream.read();
				//System.out.println(message);
				if(message != -1)
				{
					serverBufOutStream.write(message);
					serverBufOutStream.flush();
				}
				else
				{
					serverSock.close();
					clientSock.close();
					return;
				}
				

			} catch (IOException e) {
				System.err.println("Client connection to server closed");
				
				return;

			}
		}
	}
	
}
