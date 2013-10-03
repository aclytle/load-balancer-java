package loadBalance;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


public class WriteToClient implements Runnable {

	BufferedOutputStream clientBufOutStream;
	BufferedInputStream serverBufInStream;
	Socket clientSock;
	Socket serverSock;

	public WriteToClient(Socket clientSocket, Socket serverSocket) {
		super();
		this.clientSock = clientSocket;
		this.serverSock = serverSocket;
		
		
		try {
			
			BufferedInputStream serverBufInStream = new BufferedInputStream(serverSock.getInputStream());
			BufferedOutputStream clientBufOutStream = new BufferedOutputStream(clientSock.getOutputStream());
			this.clientBufOutStream = clientBufOutStream;
			this.serverBufInStream = serverBufInStream;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (!Thread.interrupted()) {
			try {
				int message = serverBufInStream.read();
				//System.out.println(message);
				if(message != -1)
				{
					clientBufOutStream.write(message);
					clientBufOutStream.flush();
				}
				else
				{
					serverSock.close();
					clientSock.close();
					return;
				}
				

			} catch (IOException e) {
				System.err.println("Server connection to client closed");
				
				return;

			}
		}
	}
	
}
