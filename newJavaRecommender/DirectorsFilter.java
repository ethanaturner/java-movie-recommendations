
/**
 * Write a description of DirectorsFilter here.
 * 
 * Ethan Turner
 * 1.0
 */
public class DirectorsFilter implements Filter {
    private String myDirectors;
    
    
    public DirectorsFilter(String directors) {
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String directorsArray[] = myDirectors.split(",");
        for (String director : directorsArray) {
            if (MovieDatabase.getDirector(id).contains(director)) return true;
        }
        return false;
    }

}
