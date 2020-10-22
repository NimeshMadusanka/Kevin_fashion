/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * AuctionService.java
 */
package kevin.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kevin.model.AuctionItem;
import kevin.model.Item;
import kevin.util.DBConnect;

public class AuctionService {
	
	public static AuctionItem addToAuction(Item item, double startPrice, int durationDays, int durationHours, int durationMinutes) {
		
		AuctionItem auctionItem = new AuctionItem();
		
		auctionItem.setId(item.getId());
		auctionItem.setName(item.getName());
		auctionItem.setCategory(item.getCategory());
		auctionItem.setColor(item.getColor());
		auctionItem.setSize(item.getSize());
		auctionItem.setQuantity(item.getQuantity());
		auctionItem.setImagePath(item.getImagePath());
		auctionItem.setDescription(item.getDescription());
		auctionItem.setStartPrice(startPrice);
		auctionItem.setBidCount(0);
		auctionItem.setDurationDays(durationDays);
		auctionItem.setDurationHours(durationHours);
		auctionItem.setDurationMinutes(durationMinutes);
		
		return auctionItem;
		
	}
	
	public static String saveAuctionItem(AuctionItem auctionItem) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO auction_item (Name, StartPrice, Category, Color, Size, Quantity, Description, ImagePath, DurationDays, DurationHours, DurationMinutes, BidCount, Sold) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, auctionItem.getName());
			preparedStatement.setDouble(2, auctionItem.getStartPrice());
			preparedStatement.setString(3, auctionItem.getCategory());
			preparedStatement.setString(4, auctionItem.getColor());
			preparedStatement.setString(5, auctionItem.getSize());
			preparedStatement.setInt(6, auctionItem.getQuantity());
			preparedStatement.setString(7, auctionItem.getDescription());
			preparedStatement.setString(8, auctionItem.getImagePath());
			preparedStatement.setInt(9, auctionItem.getDurationDays());
			preparedStatement.setInt(10, auctionItem.getDurationHours());
			preparedStatement.setInt(11, auctionItem.getDurationMinutes());
			preparedStatement.setInt(12, auctionItem.getBidCount());
			preparedStatement.setBoolean(13, false);
			int rows = preparedStatement.executeUpdate();
			 
			if (rows > 0)
				status = "Item Added to Auction";
			else
				status = "Failed to Add Item to Auction";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static AuctionItem getAuctionItem(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		AuctionItem itm = new AuctionItem();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM auction_item WHERE Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				itm.setId(resultSet.getInt("Id"));
				itm.setName(resultSet.getString("Name"));
				itm.setDescription(resultSet.getString("Description"));
				itm.setImagePath(resultSet.getString("ImagePath"));
				itm.setCategory(resultSet.getString("Category"));
				itm.setColor(resultSet.getString("Color"));
				itm.setSize(resultSet.getString("Size"));
				itm.setStartPrice(resultSet.getDouble("StartPrice"));
				itm.setQuantity(resultSet.getInt("Quantity"));
				itm.setBidCount(resultSet.getInt("BidCount"));
				itm.setDurationDays(resultSet.getInt("DurationDays"));
				itm.setDurationHours(resultSet.getInt("DurationHours"));
				itm.setDurationMinutes(resultSet.getInt("DurationMinutes"));
				itm.setHighestBid(resultSet.getDouble("HighestBid"));
				itm.setSold(resultSet.getBoolean("Sold"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return itm;
		
	}
	
	public static ArrayList<AuctionItem> getAllAuctionItems() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<AuctionItem> items = new ArrayList<AuctionItem>();
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM auction_item");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				AuctionItem itm = new AuctionItem();
				
				itm.setId(resultSet.getInt("Id"));
				itm.setName(resultSet.getString("Name"));
				itm.setDescription(resultSet.getString("Description"));
				itm.setImagePath(resultSet.getString("ImagePath"));
				itm.setCategory(resultSet.getString("Category"));
				itm.setColor(resultSet.getString("Color"));
				itm.setSize(resultSet.getString("Size"));
				itm.setStartPrice(resultSet.getDouble("StartPrice"));
				itm.setQuantity(resultSet.getInt("Quantity"));
				itm.setBidCount(resultSet.getInt("BidCount"));
				itm.setDurationDays(resultSet.getInt("DurationDays"));
				itm.setDurationHours(resultSet.getInt("DurationHours"));
				itm.setDurationMinutes(resultSet.getInt("DurationMinutes"));
				itm.setHighestBid(resultSet.getDouble("HighestBid"));
				itm.setSold(resultSet.getBoolean("Sold"));
				
				items.add(itm);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return items;
		
	}
	
	public static String deleteAuctionItem(int id) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM auction_item WHERE Id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

			status = "Auction Item Deleted";
	
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String EditAuction(int id, double startPrice, int days, int hours, int minutes) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			int count = 0;
			
			if (startPrice != -1) {
				
				preparedStatement = connection.prepareStatement("UPDATE auction_item SET StartPrice = ? WHERE Id = ?");
				preparedStatement.setDouble(1, startPrice);
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
				count++;
				
			}
			
			if (days != -1) {
				
				preparedStatement = connection.prepareStatement("UPDATE auction_item SET DurationDays = ? WHERE Id = ?");
				preparedStatement.setDouble(1, days);
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
				count++;
				
			}
			
			if (hours != -1) {
				
				preparedStatement = connection.prepareStatement("UPDATE auction_item SET DurationHours = ? WHERE Id = ?");
				preparedStatement.setDouble(1, hours);
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
				count++;
				
			}
			
			if (minutes != -1) {
				
				preparedStatement = connection.prepareStatement("UPDATE auction_item SET DurationMinutes = ? WHERE Id = ?");
				preparedStatement.setDouble(1, minutes);
				preparedStatement.setInt(2, id);
				preparedStatement.execute();
				count++;
				
			}
			
			if (count != 0)
				status = "Auction Updated";
			else
				status = "Change Something to Update Auction";
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
}
