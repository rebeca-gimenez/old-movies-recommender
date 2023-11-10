package movies;
/**
 * Filter returns true if a genre is within the max and minimum values, inclusive.
 * 
 * @author (Rebeca Gimenez) 
 * @version (11/2023)
 */
public class LengthFilter implements Filter {
	private int max;
    private int min;
    
    public LengthFilter(int totalMinutes) {
        min = totalMinutes;
        max = 1000;
    }
    public LengthFilter(int minMinutes, int maxMinutes) {
        max = maxMinutes;
        min = minMinutes;
    }

	@Override
	public boolean satisfies(String id) {
		int myMinutes = MovieDatabase.getMinutes(id);
        if (myMinutes >= min && myMinutes <= max) {
            return true;
        }
		return false;
	}
}
