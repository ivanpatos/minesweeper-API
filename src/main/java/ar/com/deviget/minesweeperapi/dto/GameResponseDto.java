package ar.com.deviget.minesweeperapi.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ar.com.deviget.minesweeperapi.model.Game;

public class GameResponseDto {
	
	private String id;
	
	private String columns;
	
	private String rows;
	
	private String mines;
	
	private List<CellResponseDto> cells = new ArrayList<CellResponseDto>();
	
	private String state;
	
	private String startingTime;
	
	private String updateTime;
	
	public GameResponseDto(Game game) {
		id = String.valueOf(game.getId());
		columns = String.valueOf(game.getColumns());
		rows = String.valueOf(game.getRows());
		mines = String.valueOf(game.getMines());
		game.getCells().stream().forEach(x -> {
			cells.add(new CellResponseDto(x));
		});
		state = game.getState().getDescription();		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		startingTime = df.format(game.getStartingTime());
		updateTime = df.format(game.getUpdateTime());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<CellResponseDto> getCells() {
		return cells;
	}

	public void setCells(List<CellResponseDto> cells) {
		this.cells = cells;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
