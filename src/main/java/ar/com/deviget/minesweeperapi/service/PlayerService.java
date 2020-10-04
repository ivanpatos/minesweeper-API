package ar.com.deviget.minesweeperapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.deviget.minesweeperapi.dto.PlayerRequestDto;
import ar.com.deviget.minesweeperapi.dto.PlayerResponseDto;
import ar.com.deviget.minesweeperapi.exception.InvalidPlayerException;
import ar.com.deviget.minesweeperapi.model.Player;
import ar.com.deviget.minesweeperapi.respository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	public PlayerResponseDto createPlayer(PlayerRequestDto playerRequestDto) {
		Player player = new Player(playerRequestDto);
		playerRepository.save(player);
		return new PlayerResponseDto(player);
	}
	
	public PlayerResponseDto getPlayer(int idPlayer) throws InvalidPlayerException {
		Player player = playerRepository.findById(idPlayer).orElseThrow(() -> new InvalidPlayerException("Player not found"));
		return new PlayerResponseDto(player);
	}

}
