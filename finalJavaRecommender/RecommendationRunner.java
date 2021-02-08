import java.util.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender {

    public ArrayList<String> getItemsToRate() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> moviesFull = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> filteredMovies = new ArrayList<String>();
        while (filteredMovies.size() <= 25) {
            Random rand = new Random();
            String randElement = moviesFull.get(rand.nextInt(moviesFull.size()));
            if (!filteredMovies.contains(randElement)) {
                filteredMovies.add(randElement);
            }
        }
        return filteredMovies;
    }
    
    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> results = fourthRatings.getSimilarRatings(webRaterID, 25, 2);
        if (results.size() > 0) {
            String tableStyles = "<style>table { font-family: 'Helvetica', sans-serif; border-collapse: collapse; width: 100%; } td, th { border: 1px solid #000000; text-align: left; padding: 4px; } tr:nth-child(even) { background-color: #FAFAFA; }</style>";
            String tableHeader = "<table><tr><th>Title</th><th>Year</th><th>Director</th><th>Rating</th></tr>";
            String tableBody = "";
            for (int i = 0; i < results.size() && i <= 25; i++) {
                Rating currentRating = results.get(i);
                String movieID = currentRating.getItem();
                String movieTitle = MovieDatabase.getTitle(movieID);
                int movieYear = MovieDatabase.getYear(movieID);
                String movieDirectors = MovieDatabase.getDirector(movieID);
                double ratingValue = currentRating.getValue();
                String bodyItem = "<tr><td>" + movieTitle + "</td><td>" + Integer.toString(movieYear) + "</td><td>" + movieDirectors + "</td><td>" + Double.toString(ratingValue) + "</td></tr>";
                tableBody += bodyItem;
            }
            System.out.println(tableStyles + tableHeader + tableBody + "</table>");
        } else {
            System.out.println("<h1>Sorry, we couldn't find any movies to recommend.</h1>");
        }
    }
}
