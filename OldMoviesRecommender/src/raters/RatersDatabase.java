package raters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * It contains information on all the Raters.
 * Uses a HashMap to store <String, Rater>, the String is the raterId, the Rater object is the rater. 
 * All methods and fields in the class are static to allow operations without creating new objects.
 * 
 * @author (Rebeca Gimenez) 
 * @version (11/2023)
 */

public class RatersDatabase {
	private static HashMap<String, Rater> rtrsDatabase;
	public static void startDatabase(String file) {
		if (rtrsDatabase == null) {
			rtrsDatabase = new HashMap<String, Rater>();
			try {
				loadRaters("data/" + file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Couldn't load ratings file");
				e.printStackTrace();
			}
		}
	}
	public static void startDatabase() {
		if (rtrsDatabase == null) {
			rtrsDatabase = new HashMap<String, Rater>();
			try {
				loadRaters("data/ratings.csv");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Couldn't load ratings file");
				e.printStackTrace();
			}
		}
	}
	
	public static void reStartDatabase(String file) {
		rtrsDatabase = null;
		startDatabase(file);
	}
	
	public static void loadRaters(String fileName) throws IOException {
		try {
			Reader movieFile = new FileReader(fileName);
			CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
			        .setHeader().setSkipHeaderRecord(true).build();
			CSVParser parser = CSVParser.parse(movieFile, format);
			for (CSVRecord record : parser) {
				//System.out.print(record);
				String raterId = record.get("rater_id");
				//System.out.println(id);
	            String movieId = record.get("movie_id");
	            double rating = Double.parseDouble(record.get("rating"));
	            Rater aRater = new Rater(raterId, movieId, rating);
	            if (rtrsDatabase.containsKey(raterId)) {
	            	aRater = rtrsDatabase.get(raterId);
	            	// Add new rating
	            	aRater.addRating(movieId, rating);
	            }
	            rtrsDatabase.put(raterId, aRater);
			}
		} catch (FileNotFoundException e) {
			// If you fail reading the file, print out the error
			System.err.println("File not found: " + fileName);
			e.printStackTrace();
		}		
	}
	//Methods to retrieve information about the database
	public static int size() {
		return rtrsDatabase.size();
	}
	public static boolean hasId(String id) {
		return rtrsDatabase.containsKey(id);
	}
	public static ArrayList<Rater> getRaters() {
		ArrayList<Rater> list = new ArrayList<Rater>();
        for (Rater rater : rtrsDatabase.values()) {
            list.add(rater);
        }
		return list;

	}

	//Methods to retrieve movie information using the id
	public static Rater getRater(String id) {
		return rtrsDatabase.get(id);
	}

}
