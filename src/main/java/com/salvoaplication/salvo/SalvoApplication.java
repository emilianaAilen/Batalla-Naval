package com.salvoaplication.salvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class SalvoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class);
	}


	@Bean
	public CommandLineRunner otherData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository repository, ShipRepository shipRepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository){
		return (args) -> {

			//PLAYERS
			Player player1 = new Player("hola", "seba@gmail.com");
			Player player2 = new Player("contrasena", "julio@gmail.com");

			playerRepository.save(player1);
			playerRepository.save(player2);

			//DATES
			Date creationDate = new Date();
			creationDate = Date.from(new Date().toInstant().plusSeconds(3600));

			Date creationDate2 = new Date();
			creationDate2 = Date.from(new Date().toInstant().plusSeconds(7200));

			Date finishDate = new Date();

			//GAME
			Game game1 = new Game();
			game1.setCreationDate(creationDate);

			Game game2 = new Game();
			game2.setCreationDate(creationDate2);

			gameRepository.save(game1);
			gameRepository.save(game2);

			//GAMEPLAYERS
			GamePlayer gamePlayer1 = new GamePlayer(player1, game1);
			GamePlayer gamePlayer2 = new GamePlayer(player2, game1);
			repository.save(gamePlayer1);
			repository.save(gamePlayer2);

			//SHIP LOCATIONS
			List<String> locations1 = new ArrayList<>();
			locations1.add("G2");
			locations1.add("A1");
			locations1.add("H2");
			locations1.add("G9");

			List<String> locations2 = new ArrayList<>();
			locations2.add("A1");
			locations2.add("G5");
			locations2.add("H3");
			locations2.add("H9");

			List<String> locations3 = new ArrayList<>();
			locations3.add("G6");
			locations3.add("I9");
			locations3.add("G9");

			List<String> locations4 = new ArrayList<>();
			locations4.add("H5");
			locations4.add("G5");

			//SHIPS
			Ship ship1 = new Ship("crucero", gamePlayer1,  locations1);
			Ship ship2 = new Ship("pesquero", gamePlayer2, locations4);

			shipRepository.save(ship1);
			shipRepository.save(ship2);

			//SALVOES
			Salvo salvo1 = new Salvo(1, gamePlayer1, locations2);
			Salvo salvo2 = new Salvo(2, gamePlayer2, locations3);
			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);

			gamePlayer1.addSalvo(salvo1);
			gamePlayer2.addSalvo(salvo2);

			//SCORES

			Score score1 = new Score(finishDate, 5, game1, player1);
			scoreRepository.save(score1);

		};
	}

}






