package ar.com.deviget.minesweeperapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.deviget.minesweeperapi.dto.GameRequestDto;
import ar.com.deviget.minesweeperapi.dto.GameResponseDto;
import ar.com.deviget.minesweeperapi.exception.InvalidGameException;
import ar.com.deviget.minesweeperapi.exception.InvalidPlayerException;
import ar.com.deviget.minesweeperapi.service.GameService;

@Controller
@RequestMapping(value = "/game", consumes = "application/json", produces = "application/json")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public GameResponseDto createGame(@RequestBody GameRequestDto gameRequestDto) throws InvalidGameException, InvalidPlayerException {
        return gameService.createGame(gameRequestDto);
    }
	
	@RequestMapping(value = "/{idGame}", method = RequestMethod.GET)
    @ResponseBody
    public GameResponseDto getGame(@PathVariable("idGame") int idGame) throws InvalidGameException {
        return gameService.getGame(idGame);
    }
	
	@RequestMapping(value = "/{idGame}/cell/{idCell}/reveal", method = RequestMethod.PUT)
    @ResponseBody
    public GameResponseDto revealCell(@PathVariable("idGame") int idGame, @PathVariable("idCell") int idCell) throws InvalidGameException {
        return gameService.revealCell(idGame, idCell);
    }
	
	@RequestMapping(value = "/{idGame}/cell/{idCell}/flag", method = RequestMethod.PUT)
    @ResponseBody
    public GameResponseDto flagCell(@PathVariable("idGame") int idGame, @PathVariable("idCell") int idCell) throws InvalidGameException {
        return gameService.flagCell(idGame, idCell);
    }
	
	@RequestMapping(value = "/{idGame}/pause", method = RequestMethod.PUT)
    @ResponseBody
    public GameResponseDto pauseGame(@PathVariable("idGame") int idGame) throws InvalidGameException {
        return gameService.pauseGame(idGame);
    }

}
