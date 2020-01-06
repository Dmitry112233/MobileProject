package models;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Limit implements Serializable {

    private int followedNumber;
    private int likedNumber;
    private LocalDate currentDay;

    public Limit(int followedNumber, int likedNumber, LocalDate date){
        this.followedNumber = followedNumber;
        this.likedNumber = likedNumber;
        this.currentDay = date;
    }
}
