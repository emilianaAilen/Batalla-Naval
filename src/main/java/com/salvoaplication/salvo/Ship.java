package com.salvoaplication.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;




    private String shipType;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;



    public Ship(){}

    public Ship(String shipType, GamePlayer gamePlayer, List<String> shipLocations){
        this.gamePlayer = gamePlayer;
        this.shipType = shipType;
        this.shipLocations = shipLocations;
    }


    //ship locations
    @ElementCollection
    @Column(name = "shipCollection")
    private List<String> shipLocations = new ArrayList<>();

    public List<String> getShipLocations() {
        return shipLocations;
    }

    public  void addShipLocations(String newShipLocation){
       shipLocations.add(newShipLocation);
    }

    //set y get functions

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public GamePlayer  getGamePlayer(){
        return gamePlayer;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

}
