package ar.com.deviget.minesweeperapi.respository;

import org.springframework.data.repository.CrudRepository;

import ar.com.deviget.minesweeperapi.model.Game;

public interface GameRepository extends CrudRepository<Game, Integer> {

}
