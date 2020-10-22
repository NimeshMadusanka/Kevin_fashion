/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * ItemService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import kevin.model.Item;
import kevin.model.User;
import kevin.util.DBConnect;

public class ItemService {
	
	public static Item setItem(int id, String name, double price, String category, String color, String size, int quantity, String description, String imagePath) {
		
		Item item = new Item();
		
		if (id != 0)
			item.setId(id);
		
		item.setName(name);
		item.setPrice(price);
		item.setDescription(description);
		item.setImagePath(imagePath);
		item.setCategory(category);
		item.setColor(color);
		item.setSize(size);
		item.setQuantity(quantity);
		
		return item;
		
	}
	
	public static String deleteItem(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM item WHERE Id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

			status = "Item Deleted";
	
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static Item getItem(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		Item itm = new Item();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				itm.setId(resultSet.getInt("Id"));
				itm.setName(resultSet.getString("Name"));
				itm.setPrice(resultSet.getDouble("Price"));
				itm.setDescription(resultSet.getString("Description"));
				itm.setImagePath(resultSet.getString("ImagePath"));
				itm.setCategory(resultSet.getString("Category"));
				itm.setColor(resultSet.getString("Color"));
				itm.setSize(resultSet.getString("Size"));
				itm.setQuantity(resultSet.getInt("Quantity"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return itm;
		
	}
	
	public static ArrayList<Item> getItems(String Category) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE Category = ?");
			preparedStatement.setString(1, Category);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Item itm = new Item();
				
				itm.setId(resultSet.getInt("Id"));
				itm.setName(resultSet.getString("Name"));
				itm.setPrice(resultSet.getDouble("Price"));
				itm.setDescription(resultSet.getString("Description"));
				itm.setImagePath(resultSet.getString("ImagePath"));
				itm.setCategory(resultSet.getString("Category"));
				itm.setColor(resultSet.getString("Color"));
				itm.setSize(resultSet.getString("Size"));
				itm.setQuantity(resultSet.getInt("Quantity"));
				
				items.add(itm);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return items;
		
	}
	
	public static ArrayList<Item> getAllItems() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM item");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Item itm = new Item();
				
				itm.setId(resultSet.getInt("Id"));
				itm.setName(resultSet.getString("Name"));
				itm.setPrice(resultSet.getDouble("Price"));
				itm.setDescription(resultSet.getString("Description"));
				itm.setImagePath(resultSet.getString("ImagePath"));
				itm.setCategory(resultSet.getString("Category"));
				itm.setColor(resultSet.getString("Color"));
				itm.setSize(resultSet.getString("Size"));
				itm.setQuantity(resultSet.getInt("Quantity"));
				
				items.add(itm);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return items;
		
	}
	
	public static String saveItem(Item item) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO item (Name, Price, Category, Color, Size, Quantity, Description, ImagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, item.getName());
			preparedStatement.setDouble(2, item.getPrice());
			preparedStatement.setString(3, item.getCategory());
			preparedStatement.setString(4, ItemService.getColorId(item.getColor()));
			preparedStatement.setString(5, item.getSize());
			preparedStatement.setInt(6, item.getQuantity());
			preparedStatement.setString(7, item.getDescription());
			preparedStatement.setString(8, item.getImagePath());
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Item Added";
			else
				status = "Failed to Add Item";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String updateItem(Item item) {

		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;

		try {

			connection = DBConnect.getDBConnection();

			int count = 0;
			
			if (!item.getName().equals("")) {

				preparedStatement = connection.prepareStatement("UPDATE item SET Name = ? WHERE id = ? ");
				preparedStatement.setString(1, item.getName());
				preparedStatement.setInt(2, item.getId());
				preparedStatement.execute();
				count++;

			}
			
			if (item.getPrice() != -1) {

				preparedStatement = connection.prepareStatement("UPDATE item SET Price = ? WHERE id = ? ");
				preparedStatement.setDouble(1, item.getPrice());
				preparedStatement.setInt(2, item.getId());
				preparedStatement.execute();
				count++;

			}
			
			if (!item.getDescription().equals("")) {

				preparedStatement = connection.prepareStatement("UPDATE item SET Description = ? WHERE id = ? ");
				preparedStatement.setString(1, item.getDescription());
				preparedStatement.setInt(2, item.getId());
				preparedStatement.execute();
				count++;
				
			}
			
			if (item.getColor() != null) {

				preparedStatement = connection.prepareStatement("UPDATE item SET Color = ? WHERE id = ? ");
				preparedStatement.setString(1, ItemService.getColorId(item.getColor()));
				preparedStatement.setInt(2, item.getId());
				preparedStatement.execute();
				count++;

			}
			
			if (item.getCategory() != null) {

				preparedStatement = connection.prepareStatement("UPDATE item SET Category = ? WHERE id = ? ");
				preparedStatement.setString(1, item.getCategory());
				preparedStatement.setInt(2, item.getId());
				preparedStatement.execute();
				count++;

			}
			
			if (item.getSize() != null) {

				preparedStatement = connection.prepareStatement("UPDATE item SET Size = ? WHERE id = ? ");
				preparedStatement.setString(1, item.getSize());
				preparedStatement.setInt(2, item.getId());
				preparedStatement.execute();
				count++;

			}
			
			if (item.getQuantity() != -1) {

				preparedStatement = connection.prepareStatement("UPDATE item SET Quantity = ? WHERE id = ? ");
				preparedStatement.setInt(1, item.getQuantity());
				preparedStatement.setInt(2, item.getId());
				preparedStatement.execute();
				count++;

			}
			
			if (!item.getImagePath().equals("")) {

				preparedStatement = connection.prepareStatement("UPDATE item SET ImagePath = ? WHERE id = ? ");
				preparedStatement.setString(1, item.getImagePath());
				preparedStatement.setInt(2, item.getId());
				preparedStatement.execute();
				count++;

			}
			if (count != 0)
				status = "Item Details Updated";
			else
				status = "Change Something to Update Item Details";

		} catch (Exception e) {

			status = e.getMessage();
			e.printStackTrace();

		}

		return status;

	}
	
	public static String addCategory(String category) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO category VALUES (?)");
			preparedStatement.setString(1, category);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Category Added Successfully";
			else
				status = "Failed to Add Category";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String addSize(String size) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO size VALUES (?)");
			preparedStatement.setString(1, size);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Size Added Successfully";
			else
				status = "Failed to Add Size";

		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String addColor(String value, String name) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO color VALUES (?, ?)");
			preparedStatement.setString(1, value);
			preparedStatement.setString(2, name);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Color Added Successfully";
			else
				status = "Failed to Add Color";

		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static ArrayList<String> getCategories() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ArrayList<String> categories = new ArrayList<String>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM category");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				String category = new String();
				category = resultSet.getString("Category");
				categories.add(category);
				
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return categories; 
		
	}
	
	public static ArrayList<String> getSizes() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ArrayList<String> sizes = new ArrayList<String>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM size");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				String size = new String();
				size = resultSet.getString("Size");
				sizes.add(size);
				
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return sizes; 
		
	}
	
	public static HashMap<String, String> getColors() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		HashMap<String, String> colors = new HashMap<String, String>();
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM color");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				String value = new String();
				String name = new String();
				value = resultSet.getString("Value");
				name = resultSet.getString("Name");
				colors.put(value, name);
				
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return colors; 
		
	}
	
	public static String getColorId(String colorName) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String colorId = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT Value FROM color WHERE Name = ?");
			preparedStatement.setString(1, colorName);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				colorId = resultSet.getString("Value");	
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return colorId;
		
	}
	
	public static String getColor(String colorValue) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String colorName = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT Name FROM color WHERE Value = ?");
			preparedStatement.setString(1, colorValue);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				colorName = resultSet.getString("Name");	
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return colorName;
		
	}
	
	public static String deleteCategory(String category) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM category WHERE Category = ?");
			preparedStatement.setString(1, category);
			preparedStatement.execute();

			status = "Category Deleted";
	
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String deleteSize(String size) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM size WHERE Size = ?");
			preparedStatement.setString(1, size);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Size Deleted";
			else
				status = "Failed to Delete Size";
	
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String deleteColor(String color) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM color WHERE Name = ?");
			preparedStatement.setString(1, color);
			preparedStatement.execute();

			status = "Color Deleted";
	
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String editColor(String value, String newName) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE color SET Name = ? WHERE Value = ?");
			preparedStatement.setString(1, newName);
			preparedStatement.setString(2, value);
			preparedStatement.execute();

			status = "Color Updated";
	
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}
