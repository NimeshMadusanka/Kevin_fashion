/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * TrackService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kevin.model.Track;
import kevin.util.DBConnect;

public class TrackService {
	
	public static Track setTrack(int itemId, String userId) {
		
		Track track = new Track();
		
		track.setItemId(itemId);
		track.setUserId(userId);
		
		return track;
		
	}
	
	public static String saveTrack(Track track) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO track (ItemId, UserId) VALUES (?, ?)");
			preparedStatement.setInt(1, track.getItemId());
			preparedStatement.setString(2, track.getUserId());
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Tracker Placed";
			else
				status = "Failed to Track Auction";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static ArrayList<Track> getAllTracks() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ArrayList<Track> tracks = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM track");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Track t = new Track();
				
				t.setId(resultSet.getInt("Id"));
				t.setItemId(resultSet.getInt("ItemId"));
				t.setUserId(resultSet.getString("UserId"));
				
				tracks.add(t);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return tracks;
		
	}
	
	public static String deleteTrack(int id) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM track WHERE Id = ?");
			preparedStatement.setInt(1, id);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Tracker Deleted";
			else
				status = "Failed to Delete Tracker";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static ArrayList<Track> getTracks(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ArrayList<Track> tracks = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM track WHERE UserId = ?");
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Track t = new Track();
				
				t.setId(resultSet.getInt("Id"));
				t.setItemId(resultSet.getInt("ItemId"));
				t.setUserId(resultSet.getString("UserId"));
				
				tracks.add(t);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return tracks;
		
	}

	public static Track getTrack(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		Track track = new Track();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM track WHERE Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				track.setId(resultSet.getInt("Id"));
				track.setItemId(resultSet.getInt("ItemId"));
				track.setUserId(resultSet.getString("UserId"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return track;
		
	}
	
}
