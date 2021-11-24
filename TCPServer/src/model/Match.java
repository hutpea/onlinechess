package model;

import java.io.Serializable;

public class Match implements Serializable{
	private static final long serialVersionUID = 20210811006L;
	private int id;
	private int playerCasual1ID;
	private int playerCasual2ID;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlayerCasual1ID() {
		return playerCasual1ID;
	}
	public void setPlayerCasual1ID(int playerCasual1ID) {
		this.playerCasual1ID = playerCasual1ID;
	}
	public int getPlayerCasual2ID() {
		return playerCasual2ID;
	}
	public void setPlayerCasual2ID(int playerCasual2ID) {
		this.playerCasual2ID = playerCasual2ID;
	}
}
