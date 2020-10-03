package ar.com.deviget.minesweeperapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cell")
public class Cell {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	private int x;
	
	private int y;
	
	private boolean mine;
	
	private boolean revealed;
	
	private FlagState flagState;
	
	private int adjacentMinesCount;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
	private Game game;
	
	public Cell() { }
	
	public Cell(int x, int y, Game game) {
		this.game = game;
		this.x = x;
		this.y = y;
		mine = false;
		revealed = false;
		flagState = FlagState.NO_FLAG;
		adjacentMinesCount = 0;
	}
	
	private boolean isAdjacent(Cell cell) {
		return !this.equals(cell) && Math.abs(this.x - cell.getX()) <= 1 && Math.abs(this.y - cell.getY()) <= 1;
	}
	
	public void setAdjacentMinesCount(List<Cell> cells) {
		this.adjacentMinesCount = (int) cells.stream().filter(c -> c.isAdjacent(this) && c.isMine()).count();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

	public FlagState getFlagState() {
		return flagState;
	}

	public void setFlagState(FlagState flagState) {
		this.flagState = flagState;
	}

	public int getAdjacentMinesCount() {
		return adjacentMinesCount;
	}

	public void setAdjacentMinesCount(int adjacentMinesCount) {
		this.adjacentMinesCount = adjacentMinesCount;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
