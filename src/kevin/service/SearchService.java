/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * SearchService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kevin.model.Item;
import kevin.model.Search;
import kevin.util.DBConnect;

public class SearchService {

	public static Search setSearch(String userId, String dateTime, String query) {
		
		Search search = new Search();
		
		search.setUserId(userId);
		search.setDateTime(dateTime);
		search.setQuery(query);
		
		return search;
		
	}
	
	public static Search setSearch(String dateTime, String query) {
		
		Search search = new Search();
		
		search.setDateTime(dateTime);
		search.setQuery(query);
		
		return search;
		
	}
	
	public static ArrayList<Item> searchFor(String query) {
		
		ArrayList<Item> items = ItemService.getAllItems();
		ArrayList<Item> results = new ArrayList<>();
		
		for (Item i : items) {

			if (i.getName().toLowerCase().replaceAll("\\s+","").contains(query.toLowerCase().replaceAll("\\s+","")))
				results.add(i);

		}
		
		return results;
 		
	}
	
	public static String saveSearch(Search search) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO search (UserId, DateTime, Query) VALUES (?, ?, ?)");
			preparedStatement.setString(1, search.getUserId());
			preparedStatement.setString(2, search.getDateTime());
			preparedStatement.setString(3, search.getQuery());
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Search Successful";
			else
				status = "Search Failed";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static ArrayList<Search> getAllSearch(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ArrayList<Search> search = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM search WHERE UserId = ?");
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Search s = new Search();
				
				s.setId(resultSet.getInt("Id"));
				s.setUserId(resultSet.getString("UserId"));
				s.setDateTime(resultSet.getString("DateTime"));
				s.setQuery(resultSet.getString("Query"));
				
				search.add(s);
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return search;
		
	}
	
	public static String clearSearch(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM search WHERE Id = ?");
			preparedStatement.setInt(1, id);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Search Deleted";
			else
				status = "Failed to Delete Search";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String clearSearch(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM search WHERE UserId = ?");
			preparedStatement.setString(1, userId);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Search Cleared";
			else
				status = "Failed to Clear Search";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}
