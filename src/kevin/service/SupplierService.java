/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * SupplierService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;

import kevin.model.Supplier;
import kevin.util.DBConnect;

public class SupplierService {
	
	public static Supplier addSupplier(String name, String telephone, String address) {
		
		Supplier supplier = new Supplier();
		
		supplier.setName(name);
		supplier.setTelephone(telephone);
		supplier.setAddress(address);
		
		return supplier;
		
	}
	
	public static String validateSupplier(Supplier supplier) {
		
		if (!Pattern.matches("^[a-zA-Z]+$", supplier.getName()))
			return "First Name should only contain letters";
		else if (!Pattern.matches("^[0-9]{10}$", supplier.getTelephone()))
			return "Please enter a valid telephone number";
		else
			return "Supplier Validated";
		
	}
	
	public static String validateSupplierEdit(Supplier supplier) {
		
		if (!Pattern.matches("^[a-zA-Z]+$", supplier.getName()) && !supplier.getName().equals(""))
			return "First Name should only contain letters";
		else if (!Pattern.matches("^[0-9]{10}$", supplier.getTelephone()) && !supplier.getTelephone().equals(""))
			return "Please enter a valid telephone number";
		else
			return "Supplier Validated";
		
	}
	
	public static String updateSupplier(int id, Supplier supplier) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			
			if (!supplier.getName().equals("")) {
			
				preparedStatement = connection.prepareStatement("UPDATE supplier SET Name = ? WHERE Id = ?");
				preparedStatement.setString(1, supplier.getName());
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
			
			}
			
			if (!supplier.getAddress().equals("")) {
				
				preparedStatement = connection.prepareStatement("UPDATE supplier SET Address = ? WHERE Id = ?");
				preparedStatement.setString(1, supplier.getAddress());
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
				
			}
			
			if (!supplier.getTelephone().equals("")) {
				
				preparedStatement = connection.prepareStatement("UPDATE supplier SET Telephone = ? WHERE Id = ?");
				preparedStatement.setString(1, supplier.getTelephone());
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
				
			}
			
			status = "Supplier Updated";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String deleteSupplier(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM supplier WHERE Id = ?");
			preparedStatement.setInt(1, id);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Supplier Deleted";
			else
				status = "Failed to Delete Supplier";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static Supplier getSupplier(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		Supplier sup = new Supplier();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM supplier WHERE Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				sup.setId(resultSet.getInt("Id"));
				sup.setName(resultSet.getString("Name"));
				sup.setTelephone(resultSet.getString("Telephone"));
				sup.setAddress(resultSet.getString("Address"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return sup;
		
	}
	
	public static ArrayList<Supplier> getAllSupliers() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM supplier");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Supplier sup = new Supplier();
				
				sup.setId(resultSet.getInt("Id"));
				sup.setName(resultSet.getString("Name"));
				sup.setTelephone(resultSet.getString("Telephone"));
				sup.setAddress(resultSet.getString("Address"));
				
				suppliers.add(sup);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return suppliers;
		
	}
	
	public static String saveSupplier(Supplier supplier) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO supplier (Name, Telephone, Address) VALUES (? , ?, ?)");
			preparedStatement.setString(1, supplier.getName());
			preparedStatement.setString(2, supplier.getTelephone());
			preparedStatement.setString(3, supplier.getAddress());
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Supplier Added";
			else
				status = "Failed to Add Supplier";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}
