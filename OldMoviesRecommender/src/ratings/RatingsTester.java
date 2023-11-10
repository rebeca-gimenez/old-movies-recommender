package ratings;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import movies.*;
import raters.RatersDatabase;

class RatingsTester {
	String moviesShort = "ratedmovies_short.csv";
	String ratersShort = "ratings_short.csv";
	
	String moviesLarger = "moviestest.csv";
	String ratersLarger = "ratingstest.csv";
	
	String moviesFull = "ratedmoviesfull.csv";	
	String ratersFull = "ratings.csv";
	
	@Test
	void testGetAverageByMovieId() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersShort, moviesShort);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 5, numMovies);
        assertEquals("Check num raters: ", 5, numRaters);
        int minimalRaters = 3;
        double avg = ratings.getAverageByMovieId("1798709",minimalRaters);
        assertEquals(8.25, avg, 0.01);
	}
	@Test
	void testAverageRatingsShort() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersShort, moviesShort);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 5, numMovies);
        assertEquals("Check num raters: ", 5, numRaters);
        int minimalRaters = 1;
        ArrayList<Rating> avgRatings = ratings.getAverageRatings(minimalRaters);
        assertEquals("Movies with at least 1 rater", 4, avgRatings.size());
        minimalRaters = 2;
        avgRatings = ratings.getAverageRatings(minimalRaters);
        assertEquals("Movies with min " + minimalRaters + " raters", 2, avgRatings.size());
        minimalRaters = 3;
        avgRatings = ratings.getAverageRatings(minimalRaters);
        assertEquals("Movies with at least 3 raters", 2, avgRatings.size());
        minimalRaters = 4;
        avgRatings = ratings.getAverageRatings(minimalRaters);
        assertEquals("Movies with at least 4 raters", 2, avgRatings.size());
	}
	@Test
	void testAverageRatingsLarger() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersLarger, moviesLarger);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 7, numMovies);
        assertEquals("Check num raters: ", 12, numRaters);
        int minimalRaters = 1;
        ArrayList<Rating> avgRatings = ratings.getAverageRatings(minimalRaters);
        assertEquals("Movies with at least 1 rater", 5, avgRatings.size());
        minimalRaters = 2;
        avgRatings = ratings.getAverageRatings(minimalRaters);
        assertEquals("Movies with min " + minimalRaters + " raters", 4, avgRatings.size());
        minimalRaters = 3;
        avgRatings = ratings.getAverageRatings(minimalRaters);
        assertEquals("Movies with min " + minimalRaters + " raters", 3, avgRatings.size());
        minimalRaters = 4;
        avgRatings = ratings.getAverageRatings(minimalRaters);
        assertEquals("Movies with min " + minimalRaters + " raters", 1, avgRatings.size());
        minimalRaters = 5;
        avgRatings = ratings.getAverageRatings(minimalRaters);
        assertEquals("Movies with min " + minimalRaters + " raters", 1, avgRatings.size());
        
	}
	@Test
	void testAverageRatings() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersFull, moviesFull);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 3143, numMovies);
        assertEquals("Check num raters: ", 1048, numRaters);
        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = ratings.getAverageRatings(minimalRaters);
        assertEquals("Movies with min " + minimalRaters + " raters", 29, avgRatings.size());
	}
	
	@Test
	void testAverageRatingsByYearAfterAndGenre() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersFull, moviesFull);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 3143, numMovies);
        assertEquals("Check num raters: ", 1048, numRaters);
        int year = 1990;
        Filter f1 = new YearAfterFilter(year);
        String genre = "Drama";
        Filter f2 = new GenreFilter(genre);
        AllFilters allF = new AllFilters();
        allF.addFilter(f1);
        allF.addFilter(f2);
        int minimalRaters = 8;
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(minimalRaters, allF);
        assertEquals("Movies after year " + year + " and genre " + genre, 132, avgRatings.size());
	}
	@Test
	void testSimilarRatings() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersFull, moviesFull);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 3143, numMovies);
        assertEquals("Check num raters: ", 1048, numRaters);
        String id = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> similarRatings = ratings.getSimilarRatings(id, numSimilarRaters, 
        minimalRaters);
        assertEquals("Movies found ", 89, similarRatings.size());
        Rating first = similarRatings.get(0);
        Rating second = similarRatings.get(1);
        String firstMovie = MovieDatabase.getTitle(first.getId());
        String secondMovie = MovieDatabase.getTitle(second.getId());
        assertEquals(1186.6, first.getRating(), 0.01);
        assertEquals(1110.66, second.getRating(), 0.01);
        assertEquals("Movies found ", "The Fault in Our Stars", firstMovie);
        assertEquals("Movies found ", "About Time", secondMovie);
    }
	
	@Test
	void testSimilarRatingsByGenre() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersFull, moviesFull);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 3143, numMovies);
        assertEquals("Check num raters: ", 1048, numRaters);
        String id = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String genre = "Action";
        Filter f1 = new GenreFilter(genre);
        ArrayList<Rating> similarRatings = ratings.getSimilarRatingsByFilter(id, numSimilarRaters, 
        minimalRaters, f1);
        assertEquals("Movies found ", 49, similarRatings.size());
        Rating first = similarRatings.get(0);
        Rating second = similarRatings.get(1);
        String firstMovie = MovieDatabase.getTitle(first.getId());
        String secondMovie = MovieDatabase.getTitle(second.getId());
        assertEquals(1094.81, first.getRating(), 0.01);
        assertEquals(1062.11, second.getRating(), 0.01);
        assertEquals("Movies found ", "Rush", firstMovie);
        assertEquals("Movies found ", "Thor: The Dark World", secondMovie);
    }
	@Test
	void testSimilarRatingsByDirector() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersFull, moviesFull);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 3143, numMovies);
        assertEquals("Check num raters: ", 1048, numRaters);
        String id = "1034";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        String director = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
        Filter f1 = new DirectorFilter(director);
        ArrayList<Rating> similarRatings = ratings.getSimilarRatingsByFilter(id, numSimilarRaters, 
        minimalRaters, f1);
        assertEquals("Movies found ", 2, similarRatings.size());
        Rating first = similarRatings.get(0);
        Rating second = similarRatings.get(1);
        String firstMovie = MovieDatabase.getTitle(first.getId());
        String secondMovie = MovieDatabase.getTitle(second.getId());
        assertEquals(653.33, first.getRating(), 0.01);
        assertEquals(281.5, second.getRating(), 0.01);
        assertEquals("Movies found ", "Unforgiven", firstMovie);
        assertEquals("Movies found ", "American Sniper", secondMovie);
    }
	@Test
	void testSimilarRatingsByGenreAndMinutes() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersFull, moviesFull);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 3143, numMovies);
        assertEquals("Check num raters: ", 1048, numRaters);
        String id = "65";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        int min = 100;
        int max = 200;
        Filter f1 = new LengthFilter(min, max);
        String genre = "Adventure";
        Filter f2 = new GenreFilter(genre);
        AllFilters allF = new AllFilters();
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> similarRatings = ratings.getSimilarRatingsByFilter(id, numSimilarRaters, 
        minimalRaters, allF);
        assertEquals("Movies found ", 15, similarRatings.size());
        Rating first = similarRatings.get(0);
        Rating second = similarRatings.get(1);
        String firstMovie = MovieDatabase.getTitle(first.getId());
        String secondMovie = MovieDatabase.getTitle(second.getId());
        assertEquals(1308.0, first.getRating(), 0.01);
        assertEquals(1248.5, second.getRating(), 0.01);
        assertEquals("Movies found ", "Interstellar", firstMovie);
        assertEquals("Movies found ", "Thor: The Dark World", secondMovie);
        
    }
	@Test
	void testSimilarRatingsByYearAfterAndMinutes() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersFull, moviesFull);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 3143, numMovies);
        assertEquals("Check num raters: ", 1048, numRaters);
        String id = "65";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        int min = 80;
        int max = 100;
        Filter f1 = new LengthFilter(min, max);
        int year = 2000;
        Filter f2 = new YearAfterFilter(year);
        AllFilters allF = new AllFilters();
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> similarRatings = ratings.getSimilarRatingsByFilter(id, numSimilarRaters, 
        minimalRaters, allF);
        assertEquals("Movies found ", 4, similarRatings.size());
        Rating first = similarRatings.get(0);
        Rating second = similarRatings.get(1);
        String firstMovie = MovieDatabase.getTitle(first.getId());
        String secondMovie = MovieDatabase.getTitle(second.getId());
        assertEquals(1173.2, first.getRating(), 0.01);
        assertEquals(1150.5, second.getRating(), 0.01);
        assertEquals("Movies found ", "The Grand Budapest Hotel", firstMovie);
        assertEquals("Movies found ", "Gravity", secondMovie);
    }
	@Test
	void tests() {
		Ratings ratings = new Ratings();
		ratings.fillDatabases(ratersFull, moviesFull);
		int numMovies = MovieDatabase.size();
        int numRaters = RatersDatabase.size();
        assertEquals("Check num movies: ", 3143, numMovies);
        assertEquals("Check num raters: ", 1048, numRaters);
        int minimalRaters = 3;
        int numSimilarRaters = 10;
        String id = "337";
        ArrayList<Rating> similarRatings = ratings.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        Rating first = similarRatings.get(0);
        String firstMovie = MovieDatabase.getTitle(first.getId());
        assertEquals(230.33, first.getRating(), 0.01);
        assertEquals("Movies found ", "Frozen", firstMovie);
        
        minimalRaters = 5;
        numSimilarRaters = 20;
        id = "964";
        String genre = "Mystery";
        Filter f1 = new GenreFilter(genre);
        similarRatings = ratings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f1);
        first = similarRatings.get(0);
        firstMovie = MovieDatabase.getTitle(first.getId());
        assertEquals(563.22, first.getRating(), 0.01);
        assertEquals("Movies found ", "Gone Girl", firstMovie);
        
        id = "71";
        similarRatings = ratings.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        first = similarRatings.get(0);
        firstMovie = MovieDatabase.getTitle(first.getId());
        assertEquals(1823.0, first.getRating(), 0.01);
        assertEquals("Movies found ", "About Time", firstMovie);

        minimalRaters = 2;
        numSimilarRaters = 10;
        id = "120";
        String director = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        f1 = new DirectorFilter(director);
        similarRatings = ratings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f1);
        first = similarRatings.get(0);
        firstMovie = MovieDatabase.getTitle(first.getId());
        assertEquals(646.5, first.getRating(), 0.01);
        assertEquals("Movies found ", "Star Trek", firstMovie);
        
        minimalRaters = 3;
        numSimilarRaters = 10;
        id = "168";
        int min = 80;
        int max = 160;
        f1 = new LengthFilter(min, max);
        genre = "Drama";
        Filter f2 = new GenreFilter(genre);
        AllFilters allF = new AllFilters();
        allF.addFilter(f1);
        allF.addFilter(f2);
        similarRatings = ratings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, allF);
        first = similarRatings.get(0);
        firstMovie = MovieDatabase.getTitle(first.getId());
        assertEquals(1218.25, first.getRating(), 0.01);
        assertEquals("Movies found ", "The Imitation Game", firstMovie);
        
        
        minimalRaters = 5;
        numSimilarRaters = 10;
        id = "314";
        min = 70;
        max = 200;
        f1 = new LengthFilter(min, max);
        int year = 1975;
        f2 = new YearAfterFilter(year);
        allF = new AllFilters();
        allF.addFilter(f1);
        allF.addFilter(f2);
        similarRatings = ratings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, allF);
        first = similarRatings.get(0);
        firstMovie = MovieDatabase.getTitle(first.getId());
        assertEquals(957.0, first.getRating(), 0.01);
        assertEquals("Movies found ", "Nightcrawler", firstMovie);
    }
}
