package ar.com.deviget.minesweeperapi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.deviget.minesweeperapi.dto.GameRequestDto;
import ar.com.deviget.minesweeperapi.exception.InvalidGameException;

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

	private GameState state;
	
	private Date startingTime;
	
	private Date updateTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
	private Player player;

	public Game() {
	}

	public Game(GameRequestDto gameRequestDto, Player player) {
		columns = Integer.parseInt(gameRequestDto.getColumns());
		rows = Integer.parseInt(gameRequestDto.getRows());
		mines = Integer.parseInt(gameRequestDto.getMines());
		this.player = player;
	}

	public void initialize() {

		if (!cells.isEmpty()) {
			cells.clear();
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cell = new Cell(i, j, this);
				cells.add(cell);
			}
		}

		Collections.shuffle(cells);
		cells.stream().limit(mines).forEach(c -> c.setMine(true));
		cells.sort((a, b) -> {
			if (a.getX() - b.getX() == 0)
				return a.getY() - b.getY();
			else
				return a.getX() - b.getX();
		});

		cells.stream().filter(c -> !c.isMine()).forEach(c -> c.setAdjacentMinesCount(cells));

		state = GameState.RUNNING;
		startingTime = new Date();
		updateTime = startingTime;

	}

	public void reveal(int idCell) throws InvalidGameException {
		
		Cell cell = cells.stream().filter(c -> c.getId().equals(idCell)).findAny().orElseThrow(() -> new InvalidGameException("Cell not found"));

		if (cell.getFlagState() == FlagState.RED_FLAG || cell.isRevealed()) {
			return;
		}

		if (cell.isMine()) {
			state = GameState.LOSE;
			return;
		}

		cell.reveal(cells);

		if (cells.stream().filter(c -> !c.isMine()).allMatch(c -> c.isRevealed())) {
			state = GameState.WIN;
		}

	}
	
	public void flag(int idCell) throws InvalidGameException {
		Cell cell = cells.stream().filter(c -> c.getId().equals(idCell)).findAny().orElseThrow(() -> new InvalidGameException("Cell not found"));
		if (!cell.isRevealed()) {
			switch (cell.getFlagState()) {
			case NO_FLAG:
				cell.setFlagState(FlagState.RED_FLAG);
				break;
			case RED_FLAG:
				cell.setFlagState(FlagState.QUESTION_MARK);
				break;
			case QUESTION_MARK:
				cell.setFlagState(FlagState.NO_FLAG);
				break;
			}
		}
	}
	
	public void pause() {
		if (state == GameState.RUNNING) {
			state = GameState.PAUSED;
		}
		else if (state == GameState.PAUSED) {
			state = GameState.RUNNING;
		}
	}
	
	public boolean isPaused() {
		return state == GameState.PAUSED;
	}
	
	public boolean isFinished() {
		return state == GameState.WIN || state == GameState.LOSE;
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

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Date getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public void setUpdateTime() {
		updateTime = new Date();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
