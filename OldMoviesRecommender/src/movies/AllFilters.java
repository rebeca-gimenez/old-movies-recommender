package movies;
import java.util.*;
/**
 * Stores several filters.
 * 
 * @author (Rebeca Gimenez) 
 * @version (11/2023)
 */
public class AllFilters implements Filter {
	ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

	@Override
	public boolean satisfies(String id) {
		for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        }
        return true;
	}
}
