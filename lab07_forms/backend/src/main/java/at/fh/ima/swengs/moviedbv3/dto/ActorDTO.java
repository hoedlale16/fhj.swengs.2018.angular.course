package at.fh.ima.swengs.moviedbv3.dto;


import at.fh.ima.swengs.moviedbv3.model.Gender;

import java.util.Date;
import java.util.Set;

public class ActorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private boolean alive = true;
    private Integer rating;
    private Gender gender;
    private Date dayOfBirth;
    private Set<Long> movies;

    //Homework 5
    private String nickName;
    private boolean hasPet = false;
    private Integer promiRating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Set<Long> getMovies() {
        return movies;
    }

    public void setMovies(Set<Long> movies) {
        this.movies = movies;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isHasPet() {
        return hasPet;
    }

    public void setHasPet(boolean hasPet) {
        this.hasPet = hasPet;
    }

    public Integer getPromiRating() {
        return promiRating;
    }

    public void setPromiRating(Integer promiRate) {
        this.promiRating = promiRate;
    }
}
