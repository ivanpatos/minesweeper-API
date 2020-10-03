package ar.com.deviget.minesweeperapi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.deviget.minesweeperapi.dto.GameRequestDto;

@Entity
@Table(name = "game")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int columns;

	private int rows;

	private int mines;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "game", cascade = CascadeType.ALL)
	private List<Cell> cells = new ArrayList<Cell>();

	public Game() {	}

	public Game(GameRequestDto gameRequestDto) {
		columns = Integer.parseInt(gameRequestDto.getColumns());
		rows = Integer.parseInt(gameRequestDto.getRows());
		mines = Integer.parseInt(gameRequestDto.getMines());
	}
	
	public void initialize() {

		if (!cells.isEmpty()) {
			cells.clear();
		}
			
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				Cell cell = new Cell(i, j, this);
				cells.add(cell);
			}
		}	
		
		Collections.shuffle(cells);
		cells.stream().limit(mines).forEach(c -> c.setMine(true));
		cells.sort((a,b) -> {
			if (a.getX() - b.getX() == 0)
				return a.getY() - b.getY();
			else
				return a.getX() - b.getX();
		}); 
		
		cells.stream().filter(c -> !c.isMine()).forEach(c -> c.setAdjacentMinesCount(cells));
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getMines() {
		return mines;
	}

	public void setMines(int mines) {
		this.mines = mines;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

}
