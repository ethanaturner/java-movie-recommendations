
/**
 * Write a description of ThirdRatings here.
 * 
 * Ethan Turner 
 * 1.0
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRate = new FirstRatings();
        myRaters = firstRate.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movies) {
            double averageRating = getAverageByID(movieID, minimalRaters);
            if (averageRating != 0.0) {
                Rating rating = new Rating(movieID, averageRating);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for (String movie : movies) {
            double averageRating = getAverageByID(movie, minimalRaters);
            if (averageRating != 0.0) {
                Rating rating = new Rating(movie, averageRating);
                ratings.add(rating);
            }
        }
        return ratings;
    }
    
}
