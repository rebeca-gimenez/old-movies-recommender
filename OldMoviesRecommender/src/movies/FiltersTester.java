package movies;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class FiltersTester {
	String fileOne = "ratedmovies_short.csv";
	String fileTwo = "moviestest.csv";
	String fileThree = "ratedmoviesfull.csv";

	@Test
	void testOne() {
		MovieDatabase.startDatabase(fileOne);
		
		Filter director = new DirectorFilter("Charles Chaplin,Francis Ford Coppola,Michael Mann");
		ArrayList<String> movies = MovieDatabase.filterBy(director);
		assertEquals("Check size: ", 3, movies.size());
		assertEquals("Check name: ", "0006414", movies.get(0));
		assertEquals("Check name: ", "0068646", movies.get(1));
		assertEquals("Check name: ", "0113277", movies.get(2));
		Filter genre = new GenreFilter("Drama");
		movies = MovieDatabase.filterBy(genre);
		assertEquals("Check size: ", 4, movies.size());
		assertEquals("Check name: ", "0068646", movies.get(0));
		assertEquals("Check name: ", "0113277", movies.get(3));
		assertEquals("Check name: ", "1798709", movies.get(1));
		assertEquals("Check name: ", "0790636", movies.get(2));
		Filter duration = new LengthFilter(100,170);
		movies = MovieDatabase.filterBy(duration);
		assertEquals("Check size: ", 3, movies.size());
		assertEquals("Check name: ", "0113277", movies.get(2));
		assertEquals("Check name: ", "1798709", movies.get(0));
		assertEquals("Check name: ", "0790636", movies.get(1));
		AllFilters all = new AllFilters();
		all.addFilter(director);
		all.addFilter(genre);
		all.addFilter(duration);
		movies = MovieDatabase.filterBy(all);
		assertEquals("Check size: ", 1, movies.size());
		assertEquals("Check name: ", "0113277", movies.get(0));
		Filter year = new YearAfterFilter(2013);
		movies = MovieDatabase.filterBy(year);
		assertEquals("Check size: ", 2, movies.size());
		assertEquals("Check name: ", "1798709", movies.get(0));
		assertEquals("Check name: ", "0790636", movies.get(1));
	}
}
	