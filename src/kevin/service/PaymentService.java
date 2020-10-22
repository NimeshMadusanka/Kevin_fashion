/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * PaymentService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kevin.model.Payment;
import kevin.util.DBConnect;

public class PaymentService {
	
	public static Payment setPayment(String userId, long creditCardNo, double price, String date) {
		
		Payment payment = new Payment();
		
		payment.setUserId(userId);
		payment.setCreditCardNo(creditCardNo);
		payment.setPrice(price);
		payment.setDate(date);
		
		return payment;
		
	}
	
	public static ArrayList<Payment> getPayments() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ArrayList<Payment> payments = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM payment");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Payment p = new Payment();
				
				p.setId(resultSet.getInt("Id"));
				p.setUserId(resultSet.getString("UserId"));
				p.setPrice(resultSet.getDouble("Price"));
				p.setCreditCardNo(resultSet.getLong("CreditCard"));
				p.setDate(resultSet.getString("Date"));
				
				payments.add(p);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return payments;
		
	}
	
	public static ArrayList<Payment> getPayments(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ArrayList<Payment> payments = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM payment WHERE UserId = ?");
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Payment p = new Payment();
				
				p.setId(resultSet.getInt("Id"));
				p.setUserId(resultSet.getString("UserId"));
				p.setPrice(resultSet.getDouble("Price"));
				p.setCreditCardNo(resultSet.getLong("CreditCard"));
				p.setDate(resultSet.getString("Date"));
				
				payments.add(p);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return payments;
		
	}
	
	public static Payment getPayment(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		Payment p = new Payment();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM payment WHERE Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				p.setId(resultSet.getInt("Id"));
				p.setUserId(resultSet.getString("UserId"));
				p.setPrice(resultSet.getDouble("Price"));
				p.setCreditCardNo(resultSet.getLong("CreditCard"));
				p.setDate(resultSet.getString("Date"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return p;
		
	}
	
	public static String savePayment(Payment payment) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO payment (UserId, CreditCard, Price, Date) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, payment.getUserId());
			preparedStatement.setLong(2, payment.getCreditCardNo());
			preparedStatement.setDouble(3, payment.getPrice());
			preparedStatement.setString(4, payment.getDate());
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Payment Successful";
			else
				status = "Payment Failed";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String removePayment(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM payment WHERE Id = ?");
			preparedStatement.setInt(1, id);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Payment Removed";
			else
				status = "Failed to Remove Payment";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String clearPayments() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM payment");
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Payments  Cleared";
			else
				status = "Failed to Clear Paymnet Log";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}
