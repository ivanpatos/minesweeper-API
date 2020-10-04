package ar.com.deviget.minesweeperapi.dto;

public class GameRequestDto {
	
	private String playerId;
	
	private String columns;

	private String rows;

	private String mines;

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getMines() {
		return mines;
	}

	public void setMines(String mines) {
		this.mines = mines;
	}

}
