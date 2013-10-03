package loadBalance;

public class ServerObject {
	
	private int port;
	private String host;
	private int maxConnections;
	private double rating;
	
	public ServerObject(int port, String host, int maxConnections, double rating) {
		super();
		this.port = port;
		this.host = host;
		this.maxConnections = maxConnections;
		this.rating = rating;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getMaxConnections() {
		return maxConnections;
	}
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	

}
