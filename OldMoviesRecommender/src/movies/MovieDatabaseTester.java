package movies;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class MovieDatabaseTester {
	String fileOne = "ratedmovies_short.csv";
	String fileTwo = "moviestest.csv";
	String fileThree = "ratedmoviesfull.csv";
	
	// Test loading fileOne
	@Test
	void testOne() {
		MovieDatabase.startDatabase(fileOne);
		assertEquals("Check size: ", 5, MovieDatabase.size());
		assertEquals("Check movie title: ", "The Godfather", MovieDatabase.getTitle("0068646"));
		assertEquals("Check genres: ", "Biography, Drama", MovieDatabase.getGenres("0790636"));
		assertEquals("Check year: ", 2013, MovieDatabase.getYear("1798709"));
	}
	@Test
	void testTwo() {
		MovieDatabase.reStartDatabase(fileTwo);
		assertEquals("Check size: ", 7, MovieDatabase.size());
		assertEquals("Check size: ", true, MovieDatabase.hasId("2042568"));
	}
	@Test
	void testThree() {
		MovieDatabase.reStartDatabase(fileThree);
		assertEquals("Check size: ", 3143, MovieDatabase.size());
		assertEquals("Check size: ", true, MovieDatabase.hasId("0029583"));
	}

}
