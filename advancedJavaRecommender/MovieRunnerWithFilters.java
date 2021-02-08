
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * Ethan Turner
 * 1.0
 */

import java.util.*;

public class MovieRunnerWithFilters {
    
    public MovieRunnerWithFilters() {
        printAverageRatingsByDirectorsAndMinutes(3, "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack", 90, 180);
    }

    public void printAverageRatings(int minimalRaters) {
        ThirdRatings thirdRates = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> averageRatings = thirdRates.getAverageRatings(minimalRaters);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfter(int minimalRaters, int year) {
        ThirdRatings thirdRates = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> averageRatings = thirdRates.getAverageRatingsByFilter(minimalRaters, new YearAfterFilter(year));
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(int minimalRaters, String genre) {
        ThirdRatings thirdRates = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> averageRatings = thirdRates.getAverageRatingsByFilter(minimalRaters, new GenreFilter(genre));
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(int minimalRaters, int minMinutes, int maxMinutes) {
        ThirdRatings thirdRates = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> averageRatings = thirdRates.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(minMinutes, maxMinutes));
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " (" + MovieDatabase.getMinutes(rating.getItem()) + " minutes)");
        }
    }
    
    public void printAverageRatingsByDirectors(int minimalRaters, String directors) {
        ThirdRatings thirdRates = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> averageRatings = thirdRates.getAverageRatingsByFilter(minimalRaters, new DirectorsFilter(directors));
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " (" + MovieDatabase.getMinutes(rating.getItem()) + " minutes)" + "\n\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(int minimalRaters, int year, String genre) {
        ThirdRatings thirdRates = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(year));
        filters.addFilter(new GenreFilter(genre));
        ArrayList<Rating> averageRatings = thirdRates.getAverageRatingsByFilter(minimalRaters, filters);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " (" + MovieDatabase.getYear(rating.getItem()) + ")\n\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes(int minimalRaters, String directors, int minMinutes, int maxMinutes) {
        ThirdRatings thirdRates = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        AllFilters filters = new AllFilters();
        filters.addFilter(new DirectorsFilter(directors));
        filters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        ArrayList<Rating> averageRatings = thirdRates.getAverageRatingsByFilter(minimalRaters, filters);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " (" + MovieDatabase.getMinutes(rating.getItem()) + " minutes)\n\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
}
