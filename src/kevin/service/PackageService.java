/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * PackageService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;

import kevin.model.Package;
import kevin.model.Promotion;
import kevin.util.DBConnect;

public class PackageService {
	
	public static Package setPackage(String name, double discountPercentage, double price) {
		
		Package pack = new Package(); 
		
		pack.setName(name);
		pack.setDiscountPercentage(discountPercentage);
		pack.setPrice(price);
		
		return pack;
		
	}
	
	public static String validatePackage(Package pack) {
		
		if (!Pattern.matches("^[a-zA-Z\\s]+$", pack.getName()))
			return "Package Name should only contain letters";
		else if (!(pack.getDiscountPercentage() <= 100 && pack.getDiscountPercentage() >= 0))
			return "Discount Percentage should be between 0 and 100";
		else if (!(pack.getPrice() >= 0))
			return "Price should be greater than or equal to 0";
		else
			return "Package Validated";
		
	}
	
	public static String validateEditPackage(Package pack) {
		
		if (!Pattern.matches("^[a-zA-Z\\s]+$", pack.getName()) && !pack.getName().equals(""))
			return "Package Name should only contain letters";
		else if (!(pack.getDiscountPercentage() <= 100 && pack.getDiscountPercentage() >= 0) && pack.getDiscountPercentage() != -1)
			return "Discount Percentage should be between 0 and 100";
		else if (!(pack.getPrice() >= 0) && pack.getPrice() != -1)
			return "Price should be greater than or equal to 0";
		else
			return "Package Validated";
		
	}
	
	public static String savePackage(Package pack) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO package (Name, DiscountPercentage, Price) VALUES (?, ?, ?)");
			preparedStatement.setString(1, pack.getName());
			preparedStatement.setDouble(2, pack.getDiscountPercentage());
			preparedStatement.setDouble(3, pack.getPrice());
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Package Added";
			else
				status = "Failed to Add Package";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static ArrayList<Package> getAllPackages() {
		
		PreparedStatement preparedStatement;
		Connection connection;
		ResultSet resultSet;
		ArrayList<Package> packs = new ArrayList<>();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM package");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Package pac = new Package();
				
				pac.setId(resultSet.getInt("Id"));
				pac.setName(resultSet.getString("Name"));
				pac.setDiscountPercentage(resultSet.getDouble("DiscountPercentage"));
				pac.setPrice(resultSet.getDouble("Price"));
				
				packs.add(pac);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return packs;
		
	}
	
	public static Package getPackage(int id) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		ResultSet resultSet;
		Package pac = new Package();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM package WHERE Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				pac.setId(resultSet.getInt("Id"));
				pac.setName(resultSet.getString("Name"));
				pac.setDiscountPercentage(resultSet.getDouble("DiscountPercentage"));
				pac.setPrice(resultSet.getDouble("Price"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return pac;
		
	}
	
	public static String deletePackage(int id) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM package WHERE Id = ?");
			preparedStatement.setInt(1, id);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Package Deleted";
			else
				status = "Failed to Delete Package";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String updatePackage(int id, Package pack) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			int count = 0;
			
			if (!pack.getName().equals("")) {
				
				preparedStatement = connection.prepareStatement("UPDATE package SET Name = ? WHERE Id = ?");
				preparedStatement.setString(1, pack.getName());
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
				count++;
			
			}
			
			if (pack.getDiscountPercentage() != -1) {
				
				preparedStatement = connection.prepareStatement("UPDATE package SET DiscountPercentage = ? WHERE Id = ?");
				preparedStatement.setDouble(1, pack.getDiscountPercentage());
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
				count++;
				
			}
			
			if (pack.getPrice() != -1) {
				
				preparedStatement = connection.prepareStatement("UPDATE package SET Price = ? WHERE Id = ?");
				preparedStatement.setDouble(1, pack.getPrice());
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
				count++;
				
			}
			
			if (count > 0)
				status = "Package Updated";
			else
				status = "Change Something to Update Package";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String subscribe(String userId, long creditCard, int pack) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE user SET Package = ?, CreditCard = ? WHERE Email = ?");
			preparedStatement.setInt(1, pack);
			preparedStatement.setLong(2, creditCard);
			preparedStatement.setString(3, userId);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Subscription Successful";
			else
				status = "Failed to subscribe";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}
