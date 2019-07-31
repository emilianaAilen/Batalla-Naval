package com.salvoaplication.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    public Long getId() {
        return id;
    }


    private Date finishDate;

    private int score;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    public Score(){}

    public Score(Date finishDate, int score, Game game, Player player){
        this.finishDate = finishDate;
        this.score = score;
        this.game = game;
        this.player = player;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() { return score; }

    public void setScore(int score) {
        this.score = score;
    }

    @JsonIgnore
    public Game getGame() {return game;}

    public void setGame(Game game) {
        this.game = game;
    }

    @JsonIgnore
    public Player getPlayer() { return player;}

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
