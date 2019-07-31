package com.salvoaplication.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.timestamp.TSRequest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {


    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private ShipRepository shipRepository;

    //localhost:8080/api/games
    //8.D
    @RequestMapping("/games")
    public List<Object> getAll() {
        return gameRepository.findAll()
                .stream()
                .map(game -> gameDTO(game))
                .collect(Collectors.toList());
    }

    private Map<String, Object> gameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        //Agregar un item a dto
        dto.put("id", game.getId());
        dto.put("creationDate", game.getCreationDate());
        dto.put("players", getGamePlayersLista(game.getGamePlayers()));
        return dto;
    }



    //game view
    @RequestMapping("/game_view/{gamePlayer_id}")
    private Map<String, Object> findGameViewId(@PathVariable  Long gamePlayer_id){
        //System.out.println("ID de GAMEPLAYER"+gamePlayer_id);
        GamePlayer gameplay = gamePlayerRepository.findOne(gamePlayer_id);
        Map<String, Object> dto = new LinkedHashMap<>();
        Game game = gameplay.getGame();
        dto.put("id", game.getId());
        dto.put("creation date", game.getCreationDate());
        dto.put("gamePlayers", getGamePlayersLista(game.getGamePlayers()));
        dto.put("ships", getShipsLista(gameplay.getShips()));
        dto.put("salvoes", getSalvosData(game));
        return  dto;

    }

    //manipulación sobre objeto player

    private Map<String,Object> getPlayerData(Player player){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id" ,player.getId());
        dto.put("email", player.getMail());
        return dto;

    }

    //manipulación sobre lista de ships

    private List<Object> getShipsLista(List<Ship> ships){
        return ships
                .stream()
                .map(ship -> shipsDTO(ship))
                .collect(Collectors.toList());
    }

    private Map<String, Object> shipsDTO(Ship ship){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("ship type", ship.getShipType());
        dto.put("locations", ship.getShipLocations());
        return dto;
    }

    //GAMEPLAYERS LISTA

    private List<Object> getGamePlayersLista(List<GamePlayer> gamePlayers){
        return gamePlayers
                .stream()
                .map(gamePlayer -> gamePlayersDTO(gamePlayer))
                .collect(Collectors.toList());
    }

    private Map<String,Object> gamePlayersDTO(GamePlayer gamePlayer ){
        Map<String,Object> dto = new LinkedHashMap<String,Object>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", getPlayerData(gamePlayer.getPlayer()));
        return dto;
    }

    //Salvos dto

    private List<Map<String, Object>> getSalvosData(Game game){
        List<Map<String, Object>> finalList = new ArrayList<>();
        game.getGamePlayers().forEach(gamePlayer -> finalList.addAll(getSalvo(gamePlayer.getSalvoes())));
        return finalList;
    }

    private List<Map<String,Object>> getSalvo(List<Salvo> salvoes){
        return salvoes
                .stream()
                .map(salvo -> salvoesDTO(salvo))
                .collect(Collectors.toList());
    }
    private Map<String, Object> salvoesDTO(Salvo salvo){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("player", salvo.getGamePlayer().getId());
        dto.put("turn", salvo.getTurnNumber());
        dto.put("locations", salvo.getLocations());
        return dto;
    }
}

