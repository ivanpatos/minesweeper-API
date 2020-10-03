package ar.com.deviget.minesweeperapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.deviget.minesweeperapi.dto.GameRequestDto;
import ar.com.deviget.minesweeperapi.dto.GameResponseDto;
import ar.com.deviget.minesweeperapi.model.Game;
import ar.com.deviget.minesweeperapi.respository.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;

	public GameResponseDto createGame(GameRequestDto gameRequestDto) {
		Game game = new Game(gameRequestDto);
		game.initialize();
		gameRepository.save(game);
		return new GameResponseDto(game);
	}

}
