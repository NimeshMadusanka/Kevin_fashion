/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * PromoService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;

import kevin.model.Promotion;
import kevin.util.DBConnect;

public class PromoService {
	
	public static Promotion setPromo(int itemId, double percentage, String name) {
		
		Promotion promo = new Promotion();
		
		promo.setItemId(itemId);
		promo.setDiscountPrecentage(percentage);
		promo.setName(name);
		
		return promo;
		
	}
	
	public static String savePromo(Promotion promo) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO promo (ItemId, Percentage, Name) VALUES (?, ?, ?)");
			preparedStatement.setInt(1, promo.getItemId());
			preparedStatement.setDouble(2, promo.getDiscountPrecentage());
			preparedStatement.setString(3, promo.getName());
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Promotion Added Successfully";
			else
				status = "Failed to Add Item";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String validatePromo(Promotion promo) {
		
		if (!Pattern.matches("^[a-zA-Z\\s]+$", promo.getName()))
			return "Promotion Name should only contain letters";
		else if (!(promo.getDiscountPrecentage() <= 100 && promo.getDiscountPrecentage() >= 0))
			return "Discount Percentage should be between 0 and 100";
		else
			return "Promotion Validated";
		
	}
	
	public static ArrayList<Promotion> getAllPromos() {
		
		PreparedStatement preparedStatement;
		Connection connection;
		ResultSet resultSet;
		ArrayList<Promotion> promos = new ArrayList<>();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM promo");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Promotion pro = new Promotion();
				
				pro.setId(resultSet.getInt("Id"));
				pro.setItemId(resultSet.getInt("ItemId"));
				pro.setDiscountPrecentage(resultSet.getDouble("Percentage"));
				pro.setName(resultSet.getString("Name"));
				
				promos.add(pro);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return promos;
		
	}
	
	public static Promotion getPromoByItem(int itemId) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		ResultSet resultSet;
		Promotion pro = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM promo WHERE ItemId = ?");
			preparedStatement.setInt(1, itemId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				pro = new Promotion();
				
				pro.setId(resultSet.getInt("Id"));
				pro.setItemId(resultSet.getInt("ItemId"));
				pro.setDiscountPrecentage(resultSet.getDouble("Percentage"));
				pro.setName(resultSet.getString("Name"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return pro;
		
	}
	
	public static String deletePromo(int id) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM promo WHERE Id = ?");
			preparedStatement.setInt(1, id);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Promotion Deleted";
			else
				status = "Failed to Delete Promtion";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String validatePromoEdit(Promotion promo) {
		
		if (!Pattern.matches("^[a-zA-Z\\s]+$", promo.getName()) && !promo.getName().equals(""))
			return "Promotion Name should only contain letters";
		else if (!(promo.getDiscountPrecentage() <= 100 && promo.getDiscountPrecentage() >= 0) && promo.getDiscountPrecentage() != 0)
			return "Discount Percentage should be between 0 and 100";
		else
			return "Promotion Validated";
		
	}

	
	public static String updatePromo(int id, Promotion promo) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			
			if (!promo.getName().equals("")) {
				
				preparedStatement = connection.prepareStatement("UPDATE promo SET Name = ? WHERE Id = ?");
				preparedStatement.setString(1, promo.getName());
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
			
			}
			
			if (promo.getDiscountPrecentage() != 0) {
				
				preparedStatement = connection.prepareStatement("UPDATE promo SET Percentage = ? WHERE Id = ?");
				preparedStatement.setDouble(1, promo.getDiscountPrecentage());
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
				
			}

			status = "Promotion Updated";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}

}
