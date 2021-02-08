import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * FirstRatings
 * 
 * Ethan Turner 
 * 1.0
 */
public class FirstRatings {
    
    public FirstRatings() {
    }
    
    ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource file = new FileResource(filename);
        for (CSVRecord record : file.getCSVParser()) {
            Movie mov = new Movie(record.get("id"),record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));;
            movies.add(mov);
        }
        return movies;
    }
    
    void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Total movies found: " + movies.size());
        /*
        for (Movie mov : movies) {
            System.out.println(mov);
        }
        */
        int containsComedy = 0;
        int minutesOver150 = 0;
        HashMap<String, Integer> dirMovies = new HashMap<String,Integer>();
        for (Movie mov : movies) {
            if (mov.getGenres().contains("Comedy")) containsComedy++;
            if (mov.getMinutes() > 150) minutesOver150++;
            String director = mov.getDirector();
            if (!dirMovies.containsKey(director)) {
                dirMovies.put(director, 1);
            } else {
                int n = dirMovies.get(director);
                dirMovies.put(director, n+1);
            }
        }
        System.out.println("# of Comedy movies: " + containsComedy);
        System.out.println("# of movies longer than 150 minutes: " + minutesOver150);
        int maxMovies = 0;
        int maxDirMovies = 0;
        for (String director : dirMovies.keySet()) {
            if (dirMovies.get(director) > maxMovies) maxMovies = dirMovies.get(director);
            if (dirMovies.get(director) == maxMovies) maxDirMovies++;
        }
        System.out.println("Maximum # of movies by a single director: " + maxMovies);
        System.out.println("There are " + maxDirMovies + " that directed the maximum number of movies (" + maxMovies + "): ");
        for (String director: dirMovies.keySet()) {
            if (dirMovies.get(director) == maxMovies) System.out.println(director);
        }
    }
    
    ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> raters = new ArrayList<EfficientRater>();
        FileResource file = new FileResource(filename);
        for (CSVRecord record : file.getCSVParser()) {
            String raterID = record.get("rater_id");
            boolean added = false;
            for (int i = 0; i < raters.size(); i++) {
                if (raters.get(i).getID().equals(raterID)) {
                    EfficientRater rater = raters.get(i);
                    rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                    raters.set(i, rater);
                    added = true;
                }
            }
            if (!added) {
                EfficientRater rater = new EfficientRater(raterID);
                rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                raters.add(rater);
            }
        }
        return raters;
    }
    
    void testLoadRaters() {
        ArrayList<EfficientRater> raters = loadRaters("data/ratings.csv");
        System.out.println("Total raters found: " + raters.size());
        int maxRates = 0;
        for (EfficientRater rater : raters) {
            /*
            ArrayList<String> moviesRated = rater.getItemsRated();
            System.out.println("Rater " + rater.getID() + ": " + moviesRated.size());
            */
            if (rater.getID().equals("" + 193)) {
                System.out.println("Rater " + rater.getID() + " has rated " + rater.getItemsRated().size() + " movies.");
            }
            if (rater.getItemsRated().size() > maxRates) maxRates = rater.getItemsRated().size();
        }
        System.out.println("The maximum # of ratings by a rater is: " + maxRates);
        int maxRaters = 0;
        for (EfficientRater rater : raters) {
            if (rater.getItemsRated().size() == maxRates) {
                maxRaters++;
            }
        }
        System.out.println("There are " + maxRaters + " raters who rated the maximum ("  + maxRates + ") amount of movies:");
        for(EfficientRater rater : raters) {
            if (rater.getItemsRated().size() == maxRates) {
                System.out.println("Rater with ID #" + rater.getID());
            }
        }
        String movieID = "1798709";
        int numberRatings = 0;
        for (EfficientRater rater : raters) {
            if (rater.getItemsRated().contains(movieID)) numberRatings++;
        }
        System.out.println("There are " + numberRatings + " ratings for the movie with ID #" + movieID);
        ArrayList<String> ratedMovies = new ArrayList<String>();
        for (EfficientRater rater : raters) {
            for (int i = 0; i < rater.getItemsRated().size(); i++) {
                if (!ratedMovies.contains(rater.getItemsRated().get(i))) ratedMovies.add(rater.getItemsRated().get(i));
            }
        }
        System.out.println("The total number of different movies rated by all raters is: " + ratedMovies.size());
    }
}