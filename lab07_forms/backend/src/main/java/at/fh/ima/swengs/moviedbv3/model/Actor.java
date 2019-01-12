package at.fh.ima.swengs.moviedbv3.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String firstName;
    private String lastName;

    private Integer rating;

    private boolean alive = true;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dayOfBirth;

    @ManyToMany
    @JoinTable(name = "movies_actors",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies;

    //Homework 5
    private String nickName;
    private boolean hasPet = false;
    private Integer promiRating;



    @Version
    @JsonIgnore
    private long version;

    public Actor() {
    }

    public Actor(String firstName, String lastName, Date dayOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
    }

    public Actor(String firstName, String lastName, Integer rating, boolean alive, Gender gender, Date dayOfBirth, Set<Movie> movies, String nickName, boolean hasPet, Integer promiRate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.alive = alive;
        this.gender = gender;
        this.dayOfBirth = dayOfBirth;
        this.movies = movies;
        this.nickName = nickName;
        this.hasPet = hasPet;
        this.promiRating = promiRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
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

    public void setPromiRating(Integer promiRating) {
        this.promiRating = promiRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return id == actor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rating=" + rating +
                ", alive=" + alive +
                ", gender=" + gender +
                ", dayOfBirth=" + dayOfBirth +
                ", nickName='" + nickName + '\'' +
                ", hasPet=" + hasPet +
                ", promiRating=" + promiRating +
                '}';
    }
}
