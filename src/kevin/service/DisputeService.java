/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * DisputeService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kevin.model.Dispute;
import kevin.util.DBConnect;

public class DisputeService {

	public static Dispute setDispute(String userId, String description, String imagePath, String response) {
		
		Dispute dispute = new Dispute();
		
		dispute.setUserId(userId);
		dispute.setDescription(description);
		dispute.setImagePath(imagePath);
		dispute.setResponse(response);
		
		return dispute;
		
	}
	
	public static ArrayList<Dispute> getDisputes() {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		ArrayList<Dispute> disputes = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatemnet = connection.prepareStatement("SELECT * FROM dispute");
			resultSet = preparedStatemnet.executeQuery();
			
			while (resultSet.next()) {
				
				Dispute dispute = new Dispute();
				
				dispute.setId(resultSet.getInt("Id"));
				dispute.setUserId(resultSet.getString("UserId"));
				dispute.setDescription(resultSet.getString("Description"));
				dispute.setImagePath(resultSet.getString("ImagePath"));
				dispute.setResponse(resultSet.getString("Response"));
				
				disputes.add(dispute);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return disputes;
		
	}
	
	public static ArrayList<Dispute> getDisputes(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		ArrayList<Dispute> disputes = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatemnet = connection.prepareStatement("SELECT * FROM dispute WHERE UserId = ?");
			preparedStatemnet.setString(1, userId);
			resultSet = preparedStatemnet.executeQuery();
			
			while (resultSet.next()) {
				
				Dispute dispute = new Dispute();
				
				dispute.setId(resultSet.getInt("Id"));
				dispute.setUserId(resultSet.getString("UserId"));
				dispute.setDescription(resultSet.getString("Description"));
				dispute.setImagePath(resultSet.getString("ImagePath"));
				dispute.setResponse(resultSet.getString("Response"));
				
				disputes.add(dispute);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return disputes;
		
	}
	
	public static Dispute getDispute(int id) {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		Dispute dispute = new Dispute();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatemnet = connection.prepareStatement("SELECT * FROM dispute WHERE Id = ?");
			preparedStatemnet.setInt(1, id);
			resultSet = preparedStatemnet.executeQuery();
			
			while (resultSet.next()) {
				
				dispute.setId(resultSet.getInt("Id"));
				dispute.setUserId(resultSet.getString("UserId"));
				dispute.setDescription(resultSet.getString("Description"));
				dispute.setImagePath(resultSet.getString("ImagePath"));
				dispute.setResponse(resultSet.getString("Response"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return dispute;
		
	}
	
	public static String deleteDispute(int id) {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatemnet = connection.prepareStatement("DELETE FROM dispute WHERE Id = ?");
			preparedStatemnet.setInt(1, id);
			int rows = preparedStatemnet.executeUpdate();
			
			if (rows > 0)
				status = "Complaint Deleted";
			else
				status = "Failed to Delete Complaint";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String updateDispute(Dispute dispute, int id) {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		String status = null;
		int count = 0;
		
		try {
			
			connection = DBConnect.getDBConnection();
			
			if (dispute.getDescription() != null) {
				
				preparedStatemnet = connection.prepareStatement("UPDATE dispute SET Description = ? WHERE Id = ?");
				preparedStatemnet.setString(1, dispute.getDescription());
				preparedStatemnet.setInt(2, id);
				preparedStatemnet.execute();
				
				count++;
				
			}
			
			if (dispute.getResponse() != null) {
				
				preparedStatemnet = connection.prepareStatement("UPDATE dispute SET Response = ? WHERE Id = ?");
				preparedStatemnet.setString(1, dispute.getResponse());
				preparedStatemnet.setInt(2, id);	
				preparedStatemnet.execute();
				
				count++;
				
			}
			
			if (count > 0)
				status = "Complaint Updated";
			else
				status = "Failed to Update Complaint";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String saveDispute(Dispute dispute) {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatemnet = connection.prepareStatement("INSERT INTO dispute (UserId, Description, ImagePath, Response) VALUES (?, ?, ?, ?)");
			preparedStatemnet.setString(1, dispute.getUserId());
			preparedStatemnet.setString(2, dispute.getDescription());
			preparedStatemnet.setString(3, dispute.getImagePath());
			preparedStatemnet.setString(4, dispute.getResponse());
			int rows = preparedStatemnet.executeUpdate();
			
			if (rows > 0)
				status = "Complaint Successful";
			else
				status = "Complaint Failed";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}
