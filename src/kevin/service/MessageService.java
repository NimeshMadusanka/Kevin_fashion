/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * MessageService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kevin.model.User;
import kevin.model.Message;
import kevin.util.DBConnect;

public class MessageService {
	
	public static Message setUserMessage(String userId, String msg, String messageDateTime, boolean client, boolean adminSeen, boolean userSeen) {
		
		Message message = new Message();
		
		message.setUserId(userId);
		message.setMessage(msg);
		message.setMessageDateTime(messageDateTime);
		message.setClient(client);
		message.setAdminSeen(adminSeen);
		message.setUserSeen(userSeen);
		
		return message;
		
	}
	
	public static ArrayList<Message> getUserMessages(String userId) {
		
		ArrayList<Message> messages = new ArrayList<Message>();
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM message WHERE UserId = ?");
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Message message = new Message();
				
				message.setMessageId(resultSet.getInt("MessageId"));
				message.setUserId(resultSet.getString("UserId"));
				message.setMessageDateTime(resultSet.getString("DateTime"));
				message.setMessage(resultSet.getString("Message"));
				message.setClient(resultSet.getBoolean("Admin"));
				message.setAdminSeen(resultSet.getBoolean("AdminSeen"));
				message.setUserSeen(resultSet.getBoolean("UserSeen"));
				
				messages.add(message);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return messages;
		
	}
	
	public static Message getLastMessage(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		Message message = new Message();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM message WHERE UserId = ? ORDER BY MessageId DESC LIMIT 1");
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				message.setMessageId(resultSet.getInt("MessageId"));
				message.setUserId(resultSet.getString("UserId"));
				message.setMessageDateTime(resultSet.getString("DateTime"));
				message.setMessage(resultSet.getString("Message"));
				message.setClient(resultSet.getBoolean("Admin"));
				message.setAdminSeen(resultSet.getBoolean("AdminSeen"));
				message.setUserSeen(resultSet.getBoolean("UserSeen"));
				
				break;
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return message;
		
	}
	
	public static ArrayList<Message> getAllUserMessages() {
		
		ArrayList<Message> messages = new ArrayList<Message>();
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM message");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Message message = new Message();
				
				message.setMessageId(resultSet.getInt("MessageId"));
				message.setUserId(resultSet.getString("UserId"));
				message.setMessageDateTime(resultSet.getString("DateTime"));
				message.setMessage(resultSet.getString("Message"));
				message.setClient(resultSet.getBoolean("Admin"));
				message.setAdminSeen(resultSet.getBoolean("AdminSeen"));
				message.setUserSeen(resultSet.getBoolean("UserSeen"));

				messages.add(message);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return messages;
		
	}
	
	public static String validateUserMessage(Message message) {
		
		if (message.getMessage().equals(""))
			return "Please enter a message";
		else
			return "Message Submitted";
		
	}
	
	public static String saveUserMessage(Message message) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO message (UserId, DateTime, Message, Admin, AdminSeen, UserSeen) VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, message.getUserId());
			preparedStatement.setString(2, message.getMessageDateTime());
			preparedStatement.setString(3, message.getMessage());
			preparedStatement.setBoolean(4, message.isClient());
			preparedStatement.setBoolean(5, message.isAdminSeen());
			preparedStatement.setBoolean(6, message.isUserSeen());
			
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Message Submitted";
			else
				status = "Message Failed";
		
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
	
	}
	
	public static boolean adminSaw(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		boolean result = false;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE message SET AdminSeen = ? WHERE UserId = ?");
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, userId);
			result = preparedStatement.execute();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
	public static boolean userSaw(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		boolean result = false;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE message SET UserSeen = ? WHERE UserId = ?");
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, userId);
			result = preparedStatement.execute();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
	public static boolean removeUserMessage(User user) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		boolean result = false;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM message WHERE UserId = ?");
			preparedStatement.setString(1, user.getEmail());
			result = preparedStatement.execute();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
}
