package raters;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;


class RatersDatabaseTester {
	String fileOne = "ratings_short.csv";
	String fileTwo = "ratingstest.csv";
	String fileThree = "ratings.csv";
	
	// Test loading fileOne
	@Test
	void testOne() {
		RatersDatabase.startDatabase(fileOne);
		assertEquals("Check size: ", 5, RatersDatabase.size());
		assertEquals("Check num movies: ", 2, RatersDatabase.getRater("1").getNumRatedMovies());
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("1").hasMovie("0068646"));
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("1").hasMovie("0113277"));
		
		assertEquals("Check num movies: ", 3, RatersDatabase.getRater("2").getNumRatedMovies());
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("2").hasMovie("1798709"));
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("2").hasMovie("0790636"));
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("2").hasMovie("0068646"));
		
		assertEquals("Check num movies: ", 1, RatersDatabase.getRater("3").getNumRatedMovies());
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("3").hasMovie("1798709"));
		
		assertEquals("Check num movies: ", 2, RatersDatabase.getRater("4").getNumRatedMovies());
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("4").hasMovie("0068646"));
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("4").hasMovie("1798709"));
		
		assertEquals("Check num movies: ", 2, RatersDatabase.getRater("5").getNumRatedMovies());
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("5").hasMovie("0068646"));
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("5").hasMovie("1798709"));
	}
	@Test
	void testTwo() {
		RatersDatabase.reStartDatabase(fileTwo);
		assertEquals("Check size: ", 12, RatersDatabase.size());
		assertEquals("Check num movies: ", 2, RatersDatabase.getRater("51").getNumRatedMovies());
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("51").hasMovie("0416960"));
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("51").hasMovie("2042568"));
		
		assertEquals("Check num movies: ", 2, RatersDatabase.getRater("193").getNumRatedMovies());
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("193").hasMovie("0061512"));
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("193").hasMovie("0477348"));
		assertEquals("Check num movies: ", false, RatersDatabase.getRater("193").hasMovie("2042568"));
		
		assertEquals("Check num movies: ", 1, RatersDatabase.getRater("3").getNumRatedMovies());
		assertEquals("Check num movies: ", true, RatersDatabase.getRater("3").hasMovie("1790864"));
		
	}
	@Test
	void testThree() {
		RatersDatabase.reStartDatabase(fileThree);
		assertEquals("Check size: ", 1048, RatersDatabase.size());
		assertEquals("Check num movies: ", 20, RatersDatabase.getRater("2").getNumRatedMovies());
	}

}
