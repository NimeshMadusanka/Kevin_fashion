/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * ReviewService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kevin.model.Review;
import kevin.model.User;
import kevin.util.DBConnect;

public class ReviewService {

	public static Review setReview(String userId, int rating, String description, String dateTime) {
		
		Review review = new Review();
		
		review.setUserId(userId);
		review.setRating(rating);
		review.setDescription(description);
		review.setDateTime(dateTime);
		
		return review;
		
	}
	
	public static Review getReview(String userId) {
		
		Review rev = new Review();
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM review WHERE UserId = ?");
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				rev.setUserId(resultSet.getString("UserId"));
				rev.setRating(resultSet.getInt("Rating"));
				rev.setDescription(resultSet.getString("Description"));
				rev.setReply(resultSet.getString("Reply"));
				rev.setDateTime(resultSet.getString("DateTime"));
				
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return rev;
		
	}
	
	public static ArrayList<Review> getReviews() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM review");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Review rev = new Review();
				
				rev.setUserId(resultSet.getString("UserId"));
				rev.setRating(resultSet.getInt("Rating"));
				rev.setDescription(resultSet.getString("Description"));
				rev.setReply(resultSet.getString("Reply"));
				rev.setDateTime(resultSet.getString("DateTime"));

				reviews.add(rev);
				
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return reviews;
		
	}
	
	public static ArrayList<Review> getReviews(String startDate, String endDate) {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM review WHERE DateTime >= ? AND DateTime <= ?");
			preparedStatement.setString(1, startDate);
			preparedStatement.setString(2, endDate);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Review rev = new Review();
				
				rev.setUserId(resultSet.getString("UserId"));
				rev.setRating(resultSet.getInt("Rating"));
				rev.setDescription(resultSet.getString("Description"));
				rev.setReply(resultSet.getString("Reply"));
				rev.setDateTime(resultSet.getString("DateTime"));

				reviews.add(rev);
				
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return reviews;
		
	}
	
	public static String validateReview(Review review) {
		
		if (review.getRating() == 0)
			return "Please select a rating";
		else if (review.getDescription().equals(""))
			return "Please enter a description";
		else
			return "Review Submitted";
		
	}
	
	public static String saveReview(Review review) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO review (UserId, Rating, DateTime, Description) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, review.getUserId());
			preparedStatement.setInt(2, review.getRating());
			preparedStatement.setString(3, review.getDateTime());
			preparedStatement.setString(4, review.getDescription());
			
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Review Submitted";
			else
				status = "Review Failed";
		
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
	
	}
	
	public static String validateReviewEdit(Review review) {
		
		if (review.getRating() == 0 && review.getDescription().equals(""))
			return "Please select a rating or enter a description";
		else
			return "Review Updated";
		
	}
	
	public static Review updateReview(Review currentReview, Review review) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			
			connection = DBConnect.getDBConnection();
			
			if (review.getRating() != 0) {
				
				preparedStatement = connection.prepareStatement("UPDATE review SET Rating = ?, DateTime = ? WHERE UserId = ?");
				preparedStatement.setInt(1, review.getRating());
				preparedStatement.setString(2, review.getDateTime());
				preparedStatement.setString(3, currentReview.getUserId());
				preparedStatement.execute();
				preparedStatement.close();
				
				currentReview.setRating(review.getRating());
				
			}
			
			if (!review.getDescription().equals("")) {
				
				preparedStatement = connection.prepareStatement("UPDATE review SET Description = ?, DateTime = ? WHERE UserId = ?");
				preparedStatement.setString(1, review.getDescription());
				preparedStatement.setString(2, review.getDateTime());
				preparedStatement.setString(3, currentReview.getUserId());
				preparedStatement.execute();
				preparedStatement.close();
				
				currentReview.setDescription(review.getDescription());
				
			}
			
			connection.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return currentReview;
		
	}
	
	public static boolean removeReview(User user) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		boolean result = false;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM review WHERE UserId = ?");
			preparedStatement.setString(1, user.getEmail());
			result = preparedStatement.execute();
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
	public static String sendReply(String userId, String reply) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE review SET Reply = ? WHERE UserId = ?");
			preparedStatement.setString(1, reply);
			preparedStatement.setString(2, userId);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Reply Submitted";
			else
				status = "Reply Failed";
			
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String removeReply(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE review SET Reply = ? WHERE UserId = ?");
			preparedStatement.setString(1, null);
			preparedStatement.setString(2, userId);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Reply Deleted";
			else
				status = "Reply Deletion Failed";
			
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}