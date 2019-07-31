package com.salvoaplication.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Game {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id; //esto va primero porque lo que esta arriba no lleva coma y lo afecta.

    private Date creationDate;



    public Date getCreationDate() {
        return creationDate;
    }

   public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public  Game(){
        this.creationDate = new Date();
    }

    public Game(Date creationDate){
        this.creationDate = creationDate;
    }


    public long getId(){return id;}




    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<GamePlayer> gamePlayers = new ArrayList<>();

    @JsonIgnore
    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }


    public void addGamePlayer(GamePlayer gamePlay) {
        gamePlay.setGame(this);
        gamePlayers.add(gamePlay);
    }





}
