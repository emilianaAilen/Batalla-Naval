package com.salvoaplication.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private int turn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name = "locations")
    private List<String> locations = new ArrayList<>();

    public List<String> getLocations() {
        return locations;
    }

    public  void addShipLocations(String newLocation) {
        locations.add(newLocation);
    }

    public Salvo(){

    }

    public Salvo( int turnNumber,  GamePlayer gamePlayer, List<String> locations){
        this.turn = turnNumber;
        this.gamePlayer = gamePlayer;
        this.locations = locations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public int getTurnNumber() {
        return turn;
    }

    public void setTurnNumber(int turnNumber) {
        this.turn = turnNumber;
    }


}
