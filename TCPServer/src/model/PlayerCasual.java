package model;

import java.io.Serializable;

public class PlayerCasual implements Serializable{
	private static final long serialVersionUID = 20210811005L;
	private int id;
	private int playerID;
	private String result;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}	
}
