package de.hda.fbi.db2.stud.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * a Game.
 */

@Entity
public class Game {

    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int gameId;
  private  int numberOfChosenCategories;
  private  int numberOfQuestions;
  private int correctAnswerCounter;
  private String endTime;
  private String startTime;
    //Constructor
    public Game() {
    }

    public Game(int numberOfChosenCategories, int numberOfQuestions,
                int correctAnswerCounter, String endTime, String startTime) {
        this.numberOfChosenCategories = numberOfChosenCategories;
        this.numberOfQuestions = numberOfQuestions;
        this.correctAnswerCounter = correctAnswerCounter;
        this.endTime = endTime;
        this.startTime = startTime;
    }

    //Getter & Setter
    public int getGameId() {
        return gameId;
    }

    public int getNumberOfChosenCategories() {
        return numberOfChosenCategories;
    }

    public void setNumberOfChosenCategories(int numberOfChosenCategories) {
        this.numberOfChosenCategories = numberOfChosenCategories;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getCorrectAnswerCounter() {
        return correctAnswerCounter;
    }

    public void setCorrectAnswerCounter(int correctAnswerCounter) {
        this.correctAnswerCounter = correctAnswerCounter;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    //Equals & hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        return gameId == game.gameId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId);
    }

    //String represantation


    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", numberOfChosenCategories=" + numberOfChosenCategories +
                ", numberOfQuestions=" + numberOfQuestions +
                ", correctAnswerCounter=" + correctAnswerCounter +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
