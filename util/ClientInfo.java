package util;
import java.io.Serializable;

public class ClientInfo implements Serializable{

	/**This class is used to get the client information such as name, its IGameClient object, score etc.
	 * 
	 */
	private static final long serialVersionUID = 1651101774565253684L;
	
	private String name;
	private int points;
	private IGameClient gameClient;
	
	public ClientInfo(String name, int points, IGameClient client){
		this.name = name;
		this.points = points;
		this.gameClient = client;
	}
	
	public String getName(){
		return name;
	}
	
	public IGameClient getClientInterface(){
		return gameClient;
	}
	
	public int getPoints(){
		return points;
	}
	
	public void addPoint(int amount){
		points += amount;
	}
	
	public String toString(){
		return name;
	}
}
