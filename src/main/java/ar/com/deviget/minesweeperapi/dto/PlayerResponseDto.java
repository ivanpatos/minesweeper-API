package ar.com.deviget.minesweeperapi.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.deviget.minesweeperapi.model.Player;

public class PlayerResponseDto {
	
	private String id;
	
	private String username;
	
	private String email;
	
	private List<Integer> gameIds = new ArrayList<Integer>();
	
	public PlayerResponseDto(Player player) {
		id = String.valueOf(player.getId());
		username = String.valueOf(player.getUsername());
		email = String.valueOf(player.getEmail());
		player.getGames().stream().forEach(x -> {
			gameIds.add(x.getId());
		});
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getGameIds() {
		return gameIds;
	}

	public void setGameIds(List<Integer> gameIds) {
		this.gameIds = gameIds;
	}

}
