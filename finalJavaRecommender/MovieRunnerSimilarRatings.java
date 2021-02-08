
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * Ethan Turner 
 * 1.0
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public MovieRunnerSimilarRatings() {
        printSimilarRatingsByYearAfterAndMinutes();
    }

    public void printAverageRatings(int minimalRaters) {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        ArrayList<Rating> averageRatings = fourthRates.getAverageRatings(minimalRaters);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfter(int minimalRaters, int year) {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        ArrayList<Rating> averageRatings = fourthRates.getAverageRatingsByFilter(minimalRaters, new YearAfterFilter(year));
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(int minimalRaters, String genre) {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        ArrayList<Rating> averageRatings = fourthRates.getAverageRatingsByFilter(minimalRaters, new GenreFilter(genre));
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(int minimalRaters, int minMinutes, int maxMinutes) {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        ArrayList<Rating> averageRatings = fourthRates.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(minMinutes, maxMinutes));
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " (" + MovieDatabase.getMinutes(rating.getItem()) + " minutes)");
        }
    }
    
    public void printAverageRatingsByDirectors(int minimalRaters, String directors) {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        ArrayList<Rating> averageRatings = fourthRates.getAverageRatingsByFilter(minimalRaters, new DirectorsFilter(directors));
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " (" + MovieDatabase.getMinutes(rating.getItem()) + " minutes)" + "\n\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(int minimalRaters, int year, String genre) {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(year));
        filters.addFilter(new GenreFilter(genre));
        ArrayList<Rating> averageRatings = fourthRates.getAverageRatingsByFilter(minimalRaters, filters);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " (" + MovieDatabase.getYear(rating.getItem()) + ")\n\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(int minimalRaters, String directors, int minMinutes, int maxMinutes) {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        AllFilters filters = new AllFilters();
        filters.addFilter(new DirectorsFilter(directors));
        filters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        ArrayList<Rating> averageRatings = fourthRates.getAverageRatingsByFilter(minimalRaters, filters);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.size() + " results found.");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " (" + MovieDatabase.getMinutes(rating.getItem()) + " minutes)\n\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printSimilarRatings() {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        ArrayList<Rating> ratings = fourthRates.getSimilarRatings("71", 20, 5);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        ArrayList<Rating> ratings = fourthRates.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + "\n\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector() {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        ArrayList<Rating> ratings = fourthRates.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + "\n\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter("Drama"));
        filter.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> ratings = fourthRates.getSimilarRatingsByFilter("168", 10, 3, filter);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " (" + MovieDatabase.getMinutes(rating.getItem()) + " minutes) \n\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fourthRates = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1975));
        filter.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> ratings = fourthRates.getSimilarRatingsByFilter("314", 10, 5, filter);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " (" + MovieDatabase.getYear(rating.getItem()) + ", " + MovieDatabase.getMinutes(rating.getItem()) + " minutes)");
        }
    }

}
