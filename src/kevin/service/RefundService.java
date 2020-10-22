/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * RefundService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kevin.model.Refund;
import kevin.util.DBConnect;

public class RefundService {

	public static Refund setRefund(int paymentId, String description, int status, String userId) {
		
		Refund refund = new Refund();
		
		refund.setPaymentId(paymentId);
		refund.setDescription(description);
		refund.setStatus(status);
		refund.setUserId(userId);
		
		return refund;
		
	}
	
	public static ArrayList<Refund> getRefunds() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ArrayList<Refund> refunds = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM refund");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Refund refund = new Refund();
				
				refund.setId(resultSet.getInt("Id"));
				refund.setPaymentId(resultSet.getInt("PaymentId"));
				refund.setDescription(resultSet.getString("Description"));
				refund.setStatus(resultSet.getInt("Status"));
				refund.setUserId(resultSet.getString("UserId"));
				
				refunds.add(refund);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return refunds;
		
	}
	
	public static ArrayList<Refund> getRefunds(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ArrayList<Refund> refunds = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM refund WHERE UserId = ?");
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Refund refund = new Refund();
				
				refund.setId(resultSet.getInt("Id"));
				refund.setPaymentId(resultSet.getInt("PaymentId"));
				refund.setDescription(resultSet.getString("Description"));
				refund.setStatus(resultSet.getInt("Status"));
				refund.setUserId(resultSet.getString("UserId"));
				
				refunds.add(refund);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return refunds;
		
	}
	
	public static Refund getRefund(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		Refund refund = new Refund();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM refund WHERE Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				refund.setId(resultSet.getInt("Id"));
				refund.setPaymentId(resultSet.getInt("PaymentId"));
				refund.setDescription(resultSet.getString("Description"));
				refund.setStatus(resultSet.getInt("Status"));
				refund.setUserId(resultSet.getString("UserId"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return refund;
		
	}
	
	public static String deleteRefund(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM refund WHERE Id = ?");
			preparedStatement.setInt(1, id);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Refund Deleted";
			else
				status = "Failed to Delete Refund";			
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String allowRefund(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE refund SET Status = ? WHERE Id = ?");
			preparedStatement.setInt(1, Refund.ACCEPTED);
			preparedStatement.setInt(2, id);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Refund Allowed";
			else
				status = "Failed to Allow the Refund";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String rejectRefund(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE refund SET Status = ? WHERE Id = ?");
			preparedStatement.setInt(1, Refund.REJECTED);
			preparedStatement.setInt(2, id);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Refund Allowed";
			else
				status = "Failed to Allow the Refund";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String saveRefund(Refund refund) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO refund (PaymentId, UserId, Description, Status) VALUES (?, ?, ?, ?)");
			preparedStatement.setInt(1, refund.getPaymentId());
			preparedStatement.setString(2, refund.getUserId());
			preparedStatement.setString(3, refund.getDescription());
			preparedStatement.setInt(4, refund.getStatus());
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Refund Requested";
			else
				status = "Failed to Request Refund";
			
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}
