package ar.com.deviget.minesweeperapi.dto;

import ar.com.deviget.minesweeperapi.model.Cell;

public class CellResponseDto {

	private String id;

	private String x;

	private String y;

	private String mine;

	private String revealed;

	private String flagState;

	private String adjacentMinesCount;
	
	public CellResponseDto(Cell cell) {
		id = String.valueOf(cell.getId());
		x = String.valueOf(cell.getX());
		y = String.valueOf(cell.getY());
		mine = String.valueOf(cell.isMine());
		revealed = String.valueOf(cell.isRevealed());
		flagState = String.valueOf(cell.getFlagState());
		adjacentMinesCount = String.valueOf(cell.getAdjacentMinesCount());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getMine() {
		return mine;
	}

	public void setMine(String mine) {
		this.mine = mine;
	}

	public String getRevealed() {
		return revealed;
	}

	public void setRevealed(String revealed) {
		this.revealed = revealed;
	}

	public String getFlagState() {
		return flagState;
	}

	public void setFlagState(String flagState) {
		this.flagState = flagState;
	}

	public String getAdjacentMinesCount() {
		return adjacentMinesCount;
	}

	public void setAdjacentMinesCount(String adjacentMinesCount) {
		this.adjacentMinesCount = adjacentMinesCount;
	}

}
