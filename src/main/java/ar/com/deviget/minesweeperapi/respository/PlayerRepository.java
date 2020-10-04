package ar.com.deviget.minesweeperapi.respository;

import org.springframework.data.repository.CrudRepository;

import ar.com.deviget.minesweeperapi.model.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

}
