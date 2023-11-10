package movies;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * It contains information on all the movies.
 * Uses a HashMap to store <String, Movie>, the String is the movieId, the Movie object is the movie 
 * All methods and fields in the class are static to allow operations without creating new objects.
 * 
 * @author (Rebeca Gimenez) 
 * @version (11/2023)
 */
public class MovieDatabase {
	private static HashMap<String, Movie> mvDatabase;
	public static void startDatabase(String file) {
		if (mvDatabase == null) {
			mvDatabase = new HashMap<String, Movie>();
			try {
				loadOldMovies("data/" + file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Couldn't load old movies");
				e.printStackTrace();
			}
		}
	}
	public static void startDatabase() {
		if (mvDatabase == null) {
			mvDatabase = new HashMap<String, Movie>();
			try {
				loadOldMovies("data/ratedmoviesfull.csv");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Couldn't load old movies");
				e.printStackTrace();
			}
		}
	}
	
	public static void reStartDatabase(String file) {
		mvDatabase = null;
		startDatabase(file);
	}
	public static void loadOldMovies(String fileName) throws IOException {
		try {
			Reader movieFile = new FileReader(fileName);
			CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
			        .setHeader().setSkipHeaderRecord(true).build();
			CSVParser parser = CSVParser.parse(movieFile, format);
			for (CSVRecord record : parser) {
				//System.out.print(record);
				String id = record.get("id");
				//System.out.println(id);
	            String title = record.get("title");
	            String year = record.get("year");
	            String genres = record.get("genre");
	            String director = record.get("director");
	            String country = record.get("country");
	            String poster = record.get("poster");
	            String minutes = record.get("minutes");
	            Movie aMovie = new Movie(id, title, year, country, genres, 
	            		director, minutes, poster);
	            if (!mvDatabase.containsKey(id)) {
	            	mvDatabase.put(id, aMovie);
	            }     		
			}
		} catch (FileNotFoundException e) {
			// If you fail reading the file, print out the error
			System.err.println("File not found: " + fileName);
			e.printStackTrace();
		}		
	}
	//Methods to retrieve information about the database
	public static int size() {
		return mvDatabase.size();
	}
	public static boolean hasId(String id) {
		return mvDatabase.containsKey(id);
	}
	//Methods to retrieve movie information using the id
	public static Movie getMovie(String id) {
		return mvDatabase.get(id);
	}
	public static String getTitle(String id) {
		return mvDatabase.get(id).getTitle();
	}
	public static int getYear(String id) {
		return mvDatabase.get(id).getYear();
	}
	public static String getCountry(String id) {
		return mvDatabase.get(id).getCountry();
	}
	public static String getGenres(String id) {
		return mvDatabase.get(id).getGenres();
	}
	public static String getDirector(String id) {
		return mvDatabase.get(id).getDirector();
	}
	public static int getMinutes(String id) {
		return mvDatabase.get(id).getMinutes();
	}
	public static String getPoster(String id) {
		return mvDatabase.get(id).getPoster();
	}
	public static Collection<Movie> getAllMovies() {
		return mvDatabase.values();
	}
	// Adding method to filter
	/**
	 * @param f Filter
	 * @return movie IDs
	 * */
	public static ArrayList<String> filterBy(Filter f) {
		if (mvDatabase == null) {
			startDatabase();
		}
        ArrayList<String> filteredList = new ArrayList<String>();
        for(String id : mvDatabase.keySet()) {
            if (f.satisfies(id)) {
            	filteredList.add(id);
            }
        }  
        return filteredList;
    }
}
