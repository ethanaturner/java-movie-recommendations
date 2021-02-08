
/**
 * Write a description of FourthRatings here.
 * 
 * Ethan Turner
 * 1.0
 */

import java.util.*;

public class FourthRatings {
    
    public FourthRatings() {
        dotProductTest();
    }

    public double getAverageByID(String id, int minimalRaters) {
        int numberRatings = 0;
        double rating = 0.0;
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for (Rater rater : raters) {
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
    
    private double dotProduct(Rater me, Rater r) {
        double dProduct = 0;
        ArrayList<String> rRatingIDs = r.getItemsRated();
        ArrayList<String> meRatingsIDs = me.getItemsRated();
        for (String meRatingID : meRatingsIDs) {
            if (rRatingIDs.contains(meRatingID)) {
                dProduct += (me.getRating(meRatingID) -5) * (r.getRating(meRatingID) - 5);
            }
        }
        return dProduct;
    }
    
    private void dotProductTest() {
        double dProduct = 0;
        HashMap<String, Integer> oneFiveRatings = new HashMap<String, Integer>();
        oneFiveRatings.put("2354", 10);
        oneFiveRatings.put("3285", 6);
        oneFiveRatings.put("1297", 2);
        oneFiveRatings.put("5804", 8);
        HashMap<String, Integer> twentyRatings = new HashMap<String, Integer>();
        twentyRatings.put("3285", 4);
        twentyRatings.put("1297", 7);
        twentyRatings.put("6574", 10);
        twentyRatings.put("2354", 9);
        for (String oneFiveID : oneFiveRatings.keySet()) {
            if (twentyRatings.containsKey(oneFiveID)) {
                dProduct += (oneFiveRatings.get(oneFiveID) - 5) * (twentyRatings.get(oneFiveID) - 5);
            }
        }
        System.out.println(dProduct);
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for (Rater rater : raters) {
            String raterID = rater.getID();
            if (!raterID.equals(id)) {
                double dotProduct = dotProduct(me, rater);
                if (dotProduct >= 0) {
                    Rating rating = new Rating(raterID, dotProduct);
                    ratings.add(rating);
                }
            }
            
        }
        Collections.sort(ratings);
        Collections.reverse(ratings);
        return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movie : movies) {
            double rating = 0.0;
            int raters = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rating r = similarities.get(i);
                String raterID = r.getItem();
                double weightedRate = r.getValue();
                double movieRate = 0;
                try {
                    movieRate = RaterDatabase.getRater(raterID).getRating(movie);
                } catch(NullPointerException e) {
                    continue;
                }
                rating += weightedRate * movieRate;
                raters++;
            }
            if (raters >= minimalRaters) ratings.add(new Rating(movie, rating/raters));
        }
        Collections.sort(ratings);
        Collections.reverse(ratings);
        return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movie : movies) {
            double rating = 0.0;
            int raters = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rating r = similarities.get(i);
                String raterID = r.getItem();
                double weightedRate = r.getValue();
                double movieRate = 0;
                try {
                    movieRate = RaterDatabase.getRater(raterID).getRating(movie);
                } catch(NullPointerException e) {
                    continue;
                }
                rating += weightedRate * movieRate;
                raters++;
            }
            if (raters >= minimalRaters) ratings.add(new Rating(movie, rating/raters));
        }
        Collections.sort(ratings);
        Collections.reverse(ratings);
        return ratings;
    }
    
}
