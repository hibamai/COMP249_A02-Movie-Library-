package DriverA02;
import java.io.Serializable;

public class Movie implements Serializable {
    private int year;
    private String title;
    private int duration;
    private String genres;
    private String rating;
    private double score;
    private String director;
    private String actor1;
    private String actor2;
    private String actor3; 

    //getters and setters
    public int getYear() {
        return year; 
    }  
    public String getTitle() {
        return title;
    }   
    public int getDuration() {
        return duration;
    }   
    public String getGenres() {
        return genres;
    }
    public String getRating() {
        return rating;
    }   
    public double getScore() {
        return score;
    }
    public String getDirector() {
        return director;
    }   
    public String getActor1() {
        return actor1;
    }
    public String getActor2() {
        return actor2;
    }   
    public String getActor3() {
        return actor3;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public void setTitle(String title) {
        this.title = title;
    }   
    public void setDuration(int duration) {
        this.duration = duration;
    }   
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }
    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }
    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        else {
            Movie movie = (Movie) obj;
            if (this.year == movie.year && this.title.equals(movie.title) && this.duration == movie.duration && this.genres.equals(movie.genres) && this.rating.equals(movie.rating) 
                && this.score == movie.score && this.director.equals(movie.director) && this.actor1.equals(movie.actor1) && this.actor2.equals(movie.actor2) && this.actor3.equals(movie.actor3)) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "Year: " + year + ", Title: " + title + ", Duration: " + duration + ", Genres: " + genres + ", Rating: " + rating + ", Score: " + score 
        + ", Director: " + director + ", Actor1: " + actor1 + ", Actor2: " + actor2 + ", Actor3: " + actor3;
    }


}
