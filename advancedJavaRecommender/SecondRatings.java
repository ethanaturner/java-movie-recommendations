
/**
 * Write a description of SecondRatings here.
 * 
 * Ethan Turner 
 * 1.0
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRate = new FirstRatings();
        myMovies = firstRate.loadMovies(moviefile);
        myRaters = firstRate.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        int numberRatings = 0;
        double rating = 0.0;
        for (Rater rater : myRaters) {
            if (rater.getItemsRated().contains(id)) {
                numberRatings++;
                rating += rater.getRating(id);
            }
        }
        if (numberRatings >= minimalRaters) {
            return (double)rating/numberRatings;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for (Movie mov : myMovies) {
            String movID = mov.getID();
            double average = getAverageByID(movID, minimalRaters);
            if (average != 0.0) {
                Rating rating = new Rating(movID, average);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }
    
    String getTitle(String id) {
        for (Movie mov : myMovies) {
            if (mov.getID().equals(id)) return mov.getTitle();
        }
        return "N/A";
    }
    
    String getID(String title) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) return movie.getID();
        }
        return "Couldn't find that title.";
    }
    
}
