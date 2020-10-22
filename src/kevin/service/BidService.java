/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * BidService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import kevin.model.Bid;
import kevin.util.DBConnect;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BidService {

	
	public static Bid setBid(int itemId, String userId, double amount, String bidDate, String bidExpireDate, long creditCard) {
		
		Bid bid = new Bid();
		
		bid.setItemId(itemId);
		bid.setBid(amount);
		bid.setUserId(userId);
		bid.setBidDate(bidDate);
		bid.setBidExpireDate(bidExpireDate);
		bid.setCreditCard(creditCard);
		
		return bid;
		
	}
	
	public static Bid getBid(double bid, int itemId) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		ResultSet resultSet;
		Bid b = new Bid();

		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM bid WHERE Bid = ? AND ItemId = ?");
			preparedStatement.setDouble(1, bid);
			preparedStatement.setInt(2, itemId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				b.setId(resultSet.getInt("Id"));
				b.setBid(resultSet.getDouble("Bid"));
				b.setItemId(resultSet.getInt("ItemId"));
				b.setUserId(resultSet.getString("UserId"));
				b.setBidDate(resultSet.getString("BidDate"));
				b.setBidExpireDate(resultSet.getString("BidExpireDate"));
				b.setCreditCard(resultSet.getLong("CreditCard"));
				
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return b;
		
	}
	
	public static ArrayList<Bid> getBids(int itemId) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		ResultSet resultSet;
		ArrayList<Bid> bids = new ArrayList<>();

		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM bid WHERE ItemId = ?");
			preparedStatement.setInt(1, itemId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Bid b = new Bid();
				
				b.setId(resultSet.getInt("Id"));
				b.setBid(resultSet.getDouble("Bid"));
				b.setItemId(resultSet.getInt("ItemId"));
				b.setUserId(resultSet.getString("UserId"));
				b.setBidDate(resultSet.getString("BidDate"));
				b.setBidExpireDate(resultSet.getString("BidExpireDate"));
				b.setCreditCard(resultSet.getLong("CreditCard"));
				
				bids.add(b);
				
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return bids;
		
	}
	
	public static String saveBid(Bid bid) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO bid (ItemId, UserId, Bid, BidDate, BidExpireDate, CreditCard) VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, bid.getItemId());
			preparedStatement.setString(2, bid.getUserId());
			preparedStatement.setDouble(3, bid.getBid());
			preparedStatement.setString(4, bid.getBidDate());
			preparedStatement.setString(5, bid.getBidExpireDate());
			preparedStatement.setLong(6, bid.getCreditCard());
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Bid Added";
			else
				status = "Failed to Add Bid";
			
			if (AuctionService.getAuctionItem(bid.getItemId()).getHighestBid() < bid.getBid()) {
				
				preparedStatement = connection.prepareStatement("UPDATE auction_item SET HighestBid = ? WHERE Id = ?");
				preparedStatement.setDouble(1, bid.getBid());
				preparedStatement.setInt(2, bid.getItemId());
				preparedStatement.execute();
				
			}
			
			preparedStatement = connection.prepareStatement("UPDATE auction_item SET BidCount = ? WHERE Id = ?");
			preparedStatement.setInt(1, AuctionService.getAuctionItem(bid.getItemId()).getBidCount() + 1);
			preparedStatement.setInt(2, bid.getItemId());
			preparedStatement.execute();
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static void setExpireTimer(Calendar cal, Bid bid) {
		
		long delay = cal.getTimeInMillis() - System.currentTimeMillis();
		
		ScheduledExecutorService expire = Executors.newSingleThreadScheduledExecutor();
		expire.schedule(new Runnable(){
			
		    @Override
		    public void run() {
		        
		    	Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				String date = sdf.format(calendar.getTime());
				PaymentService.savePayment(PaymentService.setPayment(bid.getUserId(), bid.getCreditCard(), bid.getBid(), date));
		    	BidService.setSold(bid.getItemId(), bid.getUserId());
		    	
		    }
		    
		}, delay, TimeUnit.MILLISECONDS);
		
	}
	
	private static void setSold(int id, String userId) {
		
		PreparedStatement preparedStatement;
		Connection connection;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE auction_item SET Sold = ?, Buyer = ? WHERE Id = ?");
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, userId);
			preparedStatement.setInt(3, id);
			preparedStatement.execute();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
