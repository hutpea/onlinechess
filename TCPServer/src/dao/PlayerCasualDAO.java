package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import model.PlayerCasual;

public class PlayerCasualDAO extends DAO {

	/**
	 * search all clients in the PlayerCasual whose name contains the @key using
	 * PreparedStatement - recommended for professional coding
	 * 
	 * @param key
	 * @return list of client whose name contains the @key
	 */
	public ArrayList<PlayerCasual> getPlayerCasual(int id) {
		ArrayList<PlayerCasual> result = new ArrayList<PlayerCasual>();
		String sql = "SELECT * FROM playercasual WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PlayerCasual client = new PlayerCasual();
				client.setId(rs.getInt("id"));
				client.setPlayerID(rs.getInt("playerID"));
				client.setResult(rs.getString("result"));
				result.add(client);
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
	public boolean editPlayerCasual(PlayerCasual client) {
		String sql = "UPDATE playercasual SET id=? playerID=?, result=? WHERE id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, client.getId());
			ps.setInt(2, client.getPlayerID());
			ps.setString(3, client.getResult());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void addPlayerCasual(PlayerCasual user) {
		String sql = "INSERT INTO playercasual(playerID, result) VALUES(?, ?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, user.getPlayerID());
			ps.setString(2, user.getResult());

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

}