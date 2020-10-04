package ar.com.deviget.minesweeperapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.deviget.minesweeperapi.dto.GameRequestDto;
import ar.com.deviget.minesweeperapi.dto.GameResponseDto;
import ar.com.deviget.minesweeperapi.exception.InvalidGameException;
import ar.com.deviget.minesweeperapi.exception.InvalidPlayerException;
import ar.com.deviget.minesweeperapi.model.Game;
import ar.com.deviget.minesweeperapi.model.Player;
import ar.com.deviget.minesweeperapi.respository.GameRepository;
import ar.com.deviget.minesweeperapi.respository.PlayerRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private PlayerRepository playerRepository;

	public GameResponseDto createGame(GameRequestDto gameRequestDto) throws InvalidGameException, InvalidPlayerException {
		if (!gameRequestDto.isValid()) {
			throw new InvalidGameException("Missing mandatory parameters");
		}
		Player player = playerRepository.findById(Integer.parseInt(gameRequestDto.getPlayerId())).orElseThrow(() -> new InvalidPlayerException("Player not found"));
		Game game = new Game(gameRequestDto, player);
		game.initialize();
		gameRepository.save(game);
		return new GameResponseDto(game);
	}

	public GameResponseDto getGame(int idGame) throws InvalidGameException {
		Game game = gameRepository.findById(idGame).orElseThrow(() -> new InvalidGameException("Game not found"));
		return new GameResponseDto(game);
	}

	public GameResponseDto revealCell(int idGame, int idCell) throws InvalidGameException {
		Game game = gameRepository.findById(idGame).orElseThrow(() -> new InvalidGameException("Game not found"));
		if (game.isFinished() || game.isPaused()) {
			throw new InvalidGameException("Game is not running");
		}
		game.reveal(idCell);
		game.setUpdateTime();
		gameRepository.save(game);
		return new GameResponseDto(game);
	}

	public GameResponseDto flagCell(int idGame, int idCell) throws InvalidGameException {
		Game game = gameRepository.findById(idGame).orElseThrow(() -> new InvalidGameException("Game not found"));
		if (game.isFinished() || game.isPaused()) {
			throw new InvalidGameException("Game is not running");
		}
		game.flag(idCell);
		game.setUpdateTime();
		gameRepository.save(game);
		return new GameResponseDto(game);
	}

	public GameResponseDto pauseGame(int idGame) throws InvalidGameException {
		Game game = gameRepository.findById(idGame).orElseThrow(() -> new InvalidGameException("Game not found"));
		if (game.isFinished()) {
			throw new InvalidGameException("Game is not running");
		}
		game.pause();
		game.setUpdateTime();
		gameRepository.save(game);
		return new GameResponseDto(game);
	}

}
