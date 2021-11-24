package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import model.Player;

public class PlayerDAO extends DAO {

	/**
	 * search all clients in the player whose name contains the @key using
	 * PreparedStatement - recommended for professional coding
	 * 
	 * @param key
	 * @return list of client whose name contains the @key
	 */
	public Player getPlayer(int id) {
		Player result = new Player();
		String sql = "SELECT * FROM player WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Player client = new Player();
				client.setId(rs.getInt("id"));
				client.setUsername(rs.getString("username"));
				client.setPassword(rs.getString("password"));
				client.setTotalScore(rs.getFloat("totalScore"));
				client.setStatus(rs.getString("status"));
				result = client;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkLogin(Player user) {
		boolean result = false;
		String sql = "SELECT  id FROM player WHERE username = ? AND password = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * update the @client
	 * 
	 * @param client
	 */
	public boolean editPlayer(Player client) {
		String sql = "UPDATE player SET id=? username=?, password=?, totalScore=?, status=? WHERE id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, client.getId());
			ps.setString(2, client.getUsername());
			ps.setString(3, client.getPassword());
			ps.setFloat(4, client.getTotalScore());
			ps.setString(5, client.getStatus());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void addPlayer(Player user) {
		String sql = "INSERT INTO player(username, password, totalScore, status) VALUES(?, ?, ?, ?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setFloat(3, user.getTotalScore());
			ps.setString(4, user.getStatus());

			ps.executeUpdate();

			// get id of the new inserted client
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				user.setId(generatedKeys.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Player> getAllPlayers() {
		ArrayList<Player> result = new ArrayList<Player>();
		String sql = "SELECT * FROM player";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Player client = new Player();
				client.setId(rs.getInt("id"));
				client.setUsername(rs.getString("username"));
				client.setPassword(rs.getString("password"));
				client.setTotalScore(rs.getFloat("totalScore"));
				client.setStatus(rs.getString("status"));
				result.add(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}