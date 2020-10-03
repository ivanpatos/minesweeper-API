package ar.com.deviget.minesweeperapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.deviget.minesweeperapi.dto.GameRequestDto;
import ar.com.deviget.minesweeperapi.dto.GameResponseDto;
import ar.com.deviget.minesweeperapi.service.GameService;

@Controller
@RequestMapping(value = "/game", consumes = "application/json", produces = "application/json")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public GameResponseDto createGame(@RequestBody GameRequestDto gameRequestDto) {
        return gameService.createGame(gameRequestDto);
    }

}
