
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * Ethan Turner 
 * 1.0
 */

import java.util.*;

public class MovieRunnerAverage {
    
    public MovieRunnerAverage() {
        printAverageRatings();
        getAverageRatingOneMovie();
    }
    
    public void printAverageRatings() {
        SecondRatings secondRates = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("Number of movies: " + secondRates.getMovieSize());
        System.out.println("Number of raters: " + secondRates.getRaterSize());
        ArrayList<Rating> averageRatings = secondRates.getAverageRatings(12);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + secondRates.getTitle(rating.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings secondRates = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String movieTitle = "Vacation";
        String movieID = secondRates.getID(movieTitle);
        System.out.println("Average for " + movieTitle + " is: " + secondRates.getAverageByID(movieID, 3));
    }
    
}
