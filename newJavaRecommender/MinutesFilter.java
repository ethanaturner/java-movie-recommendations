
/**
 * Write a description of MinutesFilter here.
 * 
 * Ethan Turner
 * 1.0
 */
public class MinutesFilter implements Filter {
    private int myMinMinutes;
    private int myMaxMinutes;
    
    public MinutesFilter(int minMinutes, int maxMinutes) {
        myMinMinutes = minMinutes;
        myMaxMinutes = maxMinutes;
    }
    
    @Override
    public boolean satisfies(String id) {
        int movieMinutes = MovieDatabase.getMinutes(id);
        if (movieMinutes >= myMinMinutes && movieMinutes <= myMaxMinutes) return true;
        return false;
    }
    
}
