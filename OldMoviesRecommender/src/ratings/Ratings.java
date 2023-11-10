package ratings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import movies.MovieDatabase;
import movies.Filter;
import movies.AllMovies;
import raters.Rater;
import raters.RatersDatabase;


/**
 * @author (Rebeca Gimenez) 
 * @version (09/2023)
 */
public class Ratings {
	// MoviesMap is a HashMap of the movieId (String) and a list of rating values;
	private HashMap<String, ArrayList<Double>> moviesMap;
	
	/** Fills the moviesMap
	 * @param fileName the file needs to be in the data folder, example: "ratedmoviesfull.csv";
	 * @return void
	 * */
	public void fillDatabases(String ratingsFile, String moviesFile) {
		RatersDatabase.reStartDatabase(ratingsFile);
		MovieDatabase.reStartDatabase(moviesFile);
		buildMoviesMap(RatersDatabase.getRaters());
	}
	/** Fills the moviesMap
	 * @param array list of raters (likely from RatersDatabase.values()).
	 * @return void
	 * */
	private void buildMoviesMap(Collection<Rater> raters) {
		moviesMap = new HashMap<String, ArrayList<Double>>();
        for (Rater rater : raters) {
            ArrayList<String> ratedMovies = rater.getMovieIds();
            for (String movieId : ratedMovies) {
                double rating = rater.getMovieRating(movieId);
                ArrayList<Double> ratings = new ArrayList<Double>();
                if (moviesMap.containsKey(movieId)) {
                    ratings = moviesMap.get(movieId);
                }
                ratings.add(rating);
                moviesMap.put(movieId, ratings);
            }
        }
	}
	
	/** Computes the average ratings for a movie if a minimum raters condition if met.
	 * @param movieId the movie ID.
	 * @param minimalRaters the minimum raters needed to compute the average.
	 * @return the average if the minimum raters condition is met, 0.0 otherwise.
	 * */
	public double getAverageByMovieId(String movieId, int minimalRaters) {
		if (!(moviesMap==null)) {
			ArrayList<Double> movieRatings = moviesMap.get(movieId);
			if (!(movieRatings==null)) {
				int raters = movieRatings.size();
				double sum = 0.0;
				//System.out.println(movieId + "\t" + raters);
		        if (raters >= minimalRaters) {
		        	//System.out.println(movieId + "\t" + raters);
		            for (double rating : movieRatings) {
		                sum += rating;
		            }
		            return sum/raters;
		        }
			}
	        // If the minimum raters condition is not met or movie is not rated, return 0.0
	        return 0.0;
		}
		// If the moviesMap is empty, return 0.0
		return 0.0;
	}
	
	/** Gets the average ratings if a minimum raters condition if met.
	 * @param minimalRaters the minimum raters needed to compute the average.
	 * @return a list of the averages if the minimum raters condition is met.
	 * */
	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        return getAverageRatingsByFilter(minimalRaters, new AllMovies());
    }
	
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgs = new ArrayList<Rating>();
        for (String movieId : movies) {
        	//System.out.println(movieId);
            double avg = getAverageByMovieId(movieId, minimalRaters);
            if (avg > 0.0) {
                avgs.add(new Rating(movieId, avg));
            }
        }
        Collections.sort(avgs);
        return avgs;
    }
	
	// Measure of the similarities
	private double dotProduct(Rater a, Rater b) {
        ArrayList<String> aMovies = a.getMovieIds();
        ArrayList<String> bMovies = b.getMovieIds();
        double sum = 0;
        for (String movie : aMovies) {
            if (bMovies.contains(movie)) {
                //translate a rating from the scale 0 to 10 to the scale -5 to 5
                double product = (a.getMovieRating(movie) - 5)*(b.getMovieRating(movie) - 5);
                sum += product;
            }
        }
        return sum;
    }
	
	//List of raters with their similarity score
	private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater a = RatersDatabase.getRater(id);
        for (Rater b : RatersDatabase.getRaters()) {
            if (b!= a) {
                list.add(new Rating(b.getRaterId(),dotProduct(a, b)));
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, 
    int minimalRaters) {
        return getSimilarRatingsByFilter(id, numSimilarRaters, 
        minimalRaters, new AllMovies());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, 
    int minimalRaters, Filter filterCriteria) {
        // In list, rating is (rater ID, dotProduct)
        ArrayList<Rating> list = getSimilarities(id);
        // In ret, rating is (movie ID, weighted average ratings)
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieId : movies) {
            // For a movie find all the raters in "list" and average their ratings
            double sum = 0.0;
            int count = 0;
            // Get ratings made by raters in the top numSimilarRaters
            for (int k=0; k < numSimilarRaters; k++) {
                // rating r: (rater ID, dotProduct)
                Rating r = list.get(k);
                String rtrID = r.getId();
                Rater rtr = RatersDatabase.getRater(rtrID);
                //Not all raters will have all movies
                double movieRating = 0.0;
                try {
                	movieRating = rtr.getMovieRating(movieId);
                }
                catch (Exception e) {
                	continue;
                }
                if (movieRating > 0) {
                    // For each rater, multiply their similarity rating by the rating 
                	// they gave that movie. 
                    sum += r.getRating()*rtr.getMovieRating(movieId);
                    count += 1;
                }
            }
            if (count >= minimalRaters) {
                double wAvg = sum/count;
                ret.add(new Rating(movieId, wAvg));
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }

}
