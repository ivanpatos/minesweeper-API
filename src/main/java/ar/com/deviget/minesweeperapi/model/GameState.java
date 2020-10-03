package ar.com.deviget.minesweeperapi.model;

public enum GameState {

	WIN("You win :)"), LOSE("Game over :("), PAUSED("Game paused Zzz"), RUNNING("Game running...");

	private final String description;

	private GameState(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
