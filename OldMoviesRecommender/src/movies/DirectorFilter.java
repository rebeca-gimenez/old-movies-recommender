package movies;
/**
 * Filter returns true if there are any matches between a list of directors and a movie director.
 * We assume each movie has only one director.
 * 
 * @author (Rebeca Gimenez) 
 * @version (11/2023)
 */
public class DirectorFilter implements Filter {
	private String[] directors;
	
    public DirectorFilter(String names) {
        directors = names.split(",");
    }
    
	@Override
	public boolean satisfies(String id) {
		String myDirector = MovieDatabase.getDirector(id);;
        for (String director : directors) {
            if (myDirector.indexOf(director) != -1) {
            return true;
            }
        }
        return false;
	}
}
