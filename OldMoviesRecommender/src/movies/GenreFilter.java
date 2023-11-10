package movies;
/**
 * Filter returns true if a genre is within the movie genres.
 * 
 * @author (Rebeca Gimenez) 
 * @version (11/2023)
 */
public class GenreFilter implements Filter {
    private String genre;
    
    public GenreFilter(String aGenre) {
    	genre = aGenre;
    }
    
    @Override
    public  boolean satisfies(String id) {
        String mygenres = MovieDatabase.getGenres(id);
        if (mygenres.indexOf(genre) == -1) {
            return false;
        }
        return true;
    }
}
