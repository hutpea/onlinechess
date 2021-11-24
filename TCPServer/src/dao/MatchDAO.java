package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import model.Match;

public class MatchDAO extends DAO {

	/**
	 * search all clients in the Match whose name contains the @key using
	 * PreparedStatement - recommended for professional coding
	 * 
	 * @param key
	 * @return list of client whose name contains the @key
	 */
	public ArrayList<Match> getMatch(int id) {
		ArrayList<Match> result = new ArrayList<Match>();
		String sql = "SELECT * FROM casualmatch WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Match client = new Match();
				client.setId(rs.getInt("id"));
				client.setPlayerCasual1ID(rs.getInt("playercasual1ID"));
				client.setPlayerCasual2ID(rs.getInt("playercasual2ID"));
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
	public boolean editMatch(Match client) {
		String sql = "UPDATE casualmatch SET id=? playercasual1ID=?, playercasual2ID=? WHERE id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, client.getId());
			ps.setInt(2, client.getPlayerCasual1ID());
			ps.setInt(3, client.getPlayerCasual2ID());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void addMatch(Match user) {
		String sql = "INSERT INTO casualmatch(playercasual1ID, playercasual2ID) VALUES(?, ?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, user.getPlayerCasual1ID());
			ps.setInt(2, user.getPlayerCasual2ID());

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