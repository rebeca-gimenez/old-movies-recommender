package movies;
/**
 * Filter returns true if a movie's year is equal or larger than a year.
 * 
 * @author (Rebeca Gimenez) 
 * @version (11/2023)
 */
public class YearAfterFilter implements Filter {
	private int year;
    public YearAfterFilter(int aYear) {
        year = aYear;
    }
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= year;
	}

}
