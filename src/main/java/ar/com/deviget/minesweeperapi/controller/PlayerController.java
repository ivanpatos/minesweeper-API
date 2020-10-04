package ar.com.deviget.minesweeperapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.deviget.minesweeperapi.dto.PlayerRequestDto;
import ar.com.deviget.minesweeperapi.dto.PlayerResponseDto;
import ar.com.deviget.minesweeperapi.exception.InvalidPlayerException;
import ar.com.deviget.minesweeperapi.service.PlayerService;

@Controller
@RequestMapping(value = "/player", consumes = "application/json", produces = "application/json")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PlayerResponseDto createPlayer(@RequestBody PlayerRequestDto playerRequestDto) throws InvalidPlayerException {
        return playerService.createPlayer(playerRequestDto);
    }
	
	@RequestMapping(value = "/{idPlayer}", method = RequestMethod.GET)
    @ResponseBody
    public PlayerResponseDto getGame(@PathVariable("idPlayer") int idPlayer) throws InvalidPlayerException {
		return playerService.getPlayer(idPlayer);
    }

}
