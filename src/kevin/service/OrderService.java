/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * OrderService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kevin.model.Order;
import kevin.util.DBConnect;

public class OrderService {

	public static Order setOrder(String userId, String description, int quantity, String imagePath, boolean accepted, double cost, boolean payed, Long creditCardNo) {
		
		Order order = new Order();
		
		order.setUserId(userId);
		order.setDescription(description);
		order.setQuantity(quantity);
		order.setImagePath(imagePath);
		order.setAccepted(accepted);
		order.setCost(cost);
		order.setPayed(payed);
		order.setCreditCardNo(creditCardNo);
		
		return order;
		
	}
	
	public static ArrayList<Order> getOrders() {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		ArrayList<Order> orders = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatemnet = connection.prepareStatement("SELECT * FROM special_order");
			resultSet = preparedStatemnet.executeQuery();
			
			while (resultSet.next()) {
				
				Order order = new Order();
				
				order.setId(resultSet.getInt("Id"));
				order.setUserId(resultSet.getString("UserId"));
				order.setQuantity(resultSet.getInt("Quantity"));
				order.setDescription(resultSet.getString("Description"));
				order.setImagePath(resultSet.getString("ImagePath"));
				order.setCost(resultSet.getDouble("Cost"));
				order.setPayed(resultSet.getBoolean("Payed"));
				order.setAccepted(resultSet.getBoolean("Accepted"));
				order.setCreditCardNo(resultSet.getLong("CreditCard"));
				
				orders.add(order);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return orders;
		
	}
	
	public static ArrayList<Order> getOrders(String userId) {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		ArrayList<Order> orders = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatemnet = connection.prepareStatement("SELECT * FROM special_order WHERE UserId = ?");
			preparedStatemnet.setString(1, userId);
			resultSet = preparedStatemnet.executeQuery();
			
			while (resultSet.next()) {
				
				Order order = new Order();
				
				order.setId(resultSet.getInt("Id"));
				order.setUserId(resultSet.getString("UserId"));
				order.setQuantity(resultSet.getInt("Quantity"));
				order.setDescription(resultSet.getString("Description"));
				order.setImagePath(resultSet.getString("ImagePath"));
				order.setCost(resultSet.getDouble("Cost"));
				order.setPayed(resultSet.getBoolean("Payed"));
				order.setAccepted(resultSet.getBoolean("Accepted"));
				order.setCreditCardNo(resultSet.getLong("CreditCard"));
				
				orders.add(order);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return orders;
		
	}
	
	public static Order getOrder(int id) {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		Order order = new Order();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatemnet = connection.prepareStatement("SELECT * FROM special_order WHERE Id = ?");
			preparedStatemnet.setInt(1, id);
			resultSet = preparedStatemnet.executeQuery();
			
			while (resultSet.next()) {
				
				order.setId(resultSet.getInt("Id"));
				order.setUserId(resultSet.getString("UserId"));
				order.setQuantity(resultSet.getInt("Quantity"));
				order.setDescription(resultSet.getString("Description"));
				order.setImagePath(resultSet.getString("ImagePath"));
				order.setCost(resultSet.getDouble("Cost"));
				order.setPayed(resultSet.getBoolean("Payed"));
				order.setAccepted(resultSet.getBoolean("Accepted"));
				order.setCreditCardNo(resultSet.getLong("CreditCard"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return order;
		
	}
	
	public static String deleteOrder(int id) {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatemnet = connection.prepareStatement("DELETE FROM special_order WHERE Id = ?");
			preparedStatemnet.setInt(1, id);
			int rows = preparedStatemnet.executeUpdate();
			
			if (rows > 0)
				status = "Order Deleted";
			else
				status = "Failed to Delete Order";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String updateOrder(Order order, int id) {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		String status = null;
		int count = 0;
		
		try {
			
			connection = DBConnect.getDBConnection();
			
			if (order.getQuantity() != -1) {
				
				preparedStatemnet = connection.prepareStatement("UPDATE special_order SET Quantity = ? WHERE Id = ?");
				preparedStatemnet.setInt(1, order.getQuantity());
				preparedStatemnet.setInt(2, id);
				preparedStatemnet.execute();
				
				count++;
				
			}
			
			if (order.getCost() != -1) {
				
				preparedStatemnet = connection.prepareStatement("UPDATE special_order SET Cost = ? WHERE Id = ?");
				preparedStatemnet.setDouble(1, order.getCost());
				preparedStatemnet.setInt(2, id);	
				preparedStatemnet.execute();
				
				count++;
				
			}
			
			if (order.isAccepted() != true) {
				
				preparedStatemnet = connection.prepareStatement("UPDATE special_order SET Accepted = ? WHERE Id = ?");
				preparedStatemnet.setBoolean(1, !order.isAccepted());
				preparedStatemnet.setInt(2, id);
				preparedStatemnet.execute();
				
				count++;
				
			}
			
			if (order.isPayed() != true) {
				
				preparedStatemnet = connection.prepareStatement("UPDATE special_order SET Payed = ? WHERE Id = ?");
				preparedStatemnet.setBoolean(1, !order.isPayed());
				preparedStatemnet.setInt(2, id);
				preparedStatemnet.execute();
				
				count++;
				
			}
			
			if (count > 0)
				status = "Order Updated";
			else
				status = "Failed to Update Order";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String saveOrder(Order order) {
		
		Connection connection;
		PreparedStatement preparedStatemnet;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatemnet = connection.prepareStatement("INSERT INTO special_order (UserId, Description, Quantity, ImagePath, Accepted, Cost, Payed, CreditCard) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatemnet.setString(1, order.getUserId());
			preparedStatemnet.setString(2, order.getDescription());
			preparedStatemnet.setInt(3, order.getQuantity());
			preparedStatemnet.setString(4, order.getImagePath());
			preparedStatemnet.setBoolean(5, order.isAccepted());
			preparedStatemnet.setDouble(6, order.getCost());
			preparedStatemnet.setBoolean(7, order.isPayed());
			preparedStatemnet.setObject(8, order.getCreditCardNo());
			int rows = preparedStatemnet.executeUpdate();
			
			if (rows > 0)
				status = "Order Successful";
			else
				status = "Order Failed";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}
