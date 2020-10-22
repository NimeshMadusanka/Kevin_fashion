/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * CartService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kevin.model.Cart;
import kevin.util.DBConnect;

public class CartService {
	
	public static Cart setCart(String userId, int itemId, int quantity, double price) {
		
		Cart cart = new Cart();
		
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cart.setQuantity(quantity);
		cart.setPrice(price);
		
		return cart;
		
	}
	
	public static ArrayList<Cart> getCart(String userId) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		ArrayList<Cart> carts = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM cart WHERE UserId = ?");
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Cart c = new Cart();
				
				c.setId(resultSet.getInt("Id"));
				c.setUserId(resultSet.getString("UserId"));
				c.setItemId(resultSet.getInt("ItemId"));
				c.setQuantity(resultSet.getInt("Quantity"));
				c.setPrice(resultSet.getDouble("Price"));
				
				carts.add(c);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return carts;
		
	}
	
	public static Cart getCart(int id) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		Cart c = new Cart();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM cart WHERE Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				c.setId(resultSet.getInt("Id"));
				c.setUserId(resultSet.getString("UserId"));
				c.setItemId(resultSet.getInt("ItemId"));
				c.setQuantity(resultSet.getInt("Quantity"));
				c.setPrice(resultSet.getDouble("Price"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return c;
		
	}
	
	public static String saveCart(Cart cart) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO cart (UserId, ItemId, Quantity, Price) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, cart.getUserId());
			preparedStatement.setInt(2, cart.getItemId());
			preparedStatement.setInt(3, cart.getQuantity());
			preparedStatement.setDouble(4, cart.getPrice());
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Added to Cart";
			else
				status = "Failed to add to Cart";
					
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String removeCart(int id) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM cart WHERE Id = ?");
			preparedStatement.setInt(1, id);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Removed from cart";
			else
				status = "Failed to remove from cart";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String clearCart(String userId) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM cart WHERE UserId = ?");
			preparedStatement.setString(1, userId);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Cart cleared";
			else
				status = "Failed to clear the cart";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}
