package loadBalance;

import java.net.Socket;

public class LoadBalanceSocket implements Runnable {

	Socket conSock;
	ServerObject selectedServer;

	public LoadBalanceSocket(Socket conSock) {

		this.conSock = conSock;
	}

	@Override
	public void run() {

		int serverNumber=LoadBalanceListener.serverSelector;
		LoadBalanceListener.serverSelector = (LoadBalanceListener.serverSelector + 1) % LoadBalance.servers.size();
		this.selectedServer = LoadBalance.servers.get(serverNumber);
		System.out.println(selectedServer.getHost());
		// Open connection to selected server
		try {
			Socket selectedServerSocket = new Socket(selectedServer.getHost(),selectedServer.getPort());
			//Socket selectedServerSocket = new Socket("localhost", 17100);
/*
			BufferedOutputStream serverBufOutStream = new BufferedOutputStream(
					selectedServerSocket.getOutputStream());
			BufferedInputStream serverBufInStream = new BufferedInputStream(
					selectedServerSocket.getInputStream());

			BufferedInputStream clientBufInStream = new BufferedInputStream(
					conSock.getInputStream());
			BufferedOutputStream clientBufOutStream = new BufferedOutputStream(conSock.getOutputStream());
			*/
			
			/*
			BufferedReader serverBufReader = new BufferedReader(new InputStreamReader(selectedServerSocket.getInputStream()));
			BufferedReader clientBufReader = new BufferedReader(new InputStreamReader(conSock.getInputStream()));
			
			PrintStream clientPrinter = new PrintStream(conSock.getOutputStream());
			PrintStream serverPrinter= new PrintStream(selectedServerSocket.getOutputStream());
			*/
			
			
			WriteToClient clientWriter = new WriteToClient(conSock, selectedServerSocket);
 			WriteToServer serverWriter = new WriteToServer(conSock, selectedServerSocket);
			
			Thread cw = new Thread(clientWriter);
			Thread sw = new Thread(serverWriter);
			
			cw.start();
			sw.start();
				
					
	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
