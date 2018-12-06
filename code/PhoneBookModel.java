import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;

/*
 * Model data for the phone book application.  
 */

public class PhoneBookModel {

	PhoneBookView phonebookview;

	// The following are various states captured by the model
	public static String ADD_NAME_STATE = "ADD_NAME";
	public static String ADD_NUMBER_STATE = "ADD_NUMBER";
	public static String SEARCH_STATE = "SEARCH";
	public static String IDLE_STATE = "IDLE";
	public static String SEARCH_RESULT_STATE = "SEARCH_RESULT";
	public static String ERROR_STATE = "ERROR";
	public static String EXIT_STATE = "EXIT";
    public static String UPDATE_NAME_STATE = "UPDATE_NAME";
	public static String UPDATE_NUMBER_STATE = "UPDATE_NUMBER";
    public static String PERSON_NOT_FOUND_STATE = "PERSON_NOT_FOUND";
    public static String DELETE_STATE = "DELETE";
	
	// Private fields used to track various model data
	String state = IDLE_STATE;
	String searchResult = null;
	Hashtable<String,String> phoneBook = null;

	/**
	 * set the state
	 * @param aState
	 */
	public void setState(String aState) {
		state = aState;
        if(state == EXIT_STATE){
            writeDatabase();
        }
		phonebookview.stateHasChanged(this, state);
        
	}
	
	/**
	 * add a phone entry
	 * @param name
	 * @param number
	 */
	public void addAnEntry(String name, String number) {
		phoneBook.put(name, number);
	}

    /**
     * remove a phone entry
     * @param name
     */
    public void deleteAnEntry(String name){
        phoneBook.remove(name);
    }

    /**
	 * update someone's phone number
	 * @param name
	 * @param number
	 */
	public void updateAnEntry(String name, String number) {
		phoneBook.put(name, number);
	}

    public void writeDatabase(){}


	/**
	 * search the phone number and set the searchResult field
	 * @param name
	 */
	public void searchPhoneNumber(String name) {
		searchResult = (String) phoneBook.get(name);
	}

	/**
	 * return the search result
	 */
	public String getSearchResult() {
		return searchResult;
	}

	/**
	 * get the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * constructor
	 * @param view
	 */
	public PhoneBookModel(PhoneBookView view) {
		phonebookview = view;
		phoneBook = new Hashtable<String,String>();
	}
	
}
