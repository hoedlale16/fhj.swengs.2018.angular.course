package at.fh.ima.swengs.moviedbv3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String plot;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd.MM.yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    @ManyToOne
    @JsonIgnoreProperties("movies")
    private Genre genre;

    private int length;
    private boolean color;

    @ManyToMany
    @JsonIgnoreProperties("movies")
    private List<Actor> actors;

    @Version
    private long version;

    public Movie() {
    }

    public Movie(String title, String plot, Date releaseDate, int length, boolean color) {
        this.title = title;
        this.plot = plot;
        this.releaseDate = releaseDate;
        this.length = length;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }




    /*
    User should have username, admin checkbox
* Movie should have title, plot (text), release date (date), Runtime in Minutes (int), Genre (n:1), Actors(n:m), Black/White Film Checkbox, Aspect Ratio (enum)
* Genre should have title
* Actor should have name
* Rating should have user and rating [1-10]
*/

}
