/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * CreditCardService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import kevin.model.CreditCard;
import kevin.util.DBConnect;

public class CreditCardService {

	public static CreditCard setCreditCard(long cardNo, String userId, String name, String address, String network,
			String country, double creditLimit, String expireDate, int cvv) {

		CreditCard creditCard = new CreditCard();
		
		creditCard.setCardNo(cardNo);
		creditCard.setUserId(userId);
		creditCard.setName(name);
		creditCard.setAddress(address);
		creditCard.setCountry(country);
		creditCard.setCreditLimit(creditLimit);
		creditCard.setExpireDate(expireDate);
		creditCard.setCvv(cvv);
		creditCard.setNetwork(network);

		return creditCard;
		
	}

	public static String validateCreditCard(CreditCard creditCard) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		sdf.setLenient(false);
		Date exp = null;

		try {

			exp = sdf.parse(creditCard.getExpireDate());

		} catch (ParseException e) {

			e.printStackTrace();
			return e.getMessage();

		}

		boolean expired = exp.before(new Date());

		if (!Pattern.matches("^[a-zA-Z\\s]+$", creditCard.getName()))
			return "Name should only contain letters";
		else if (!Pattern.matches("^[a-zA-Z\\s]+$", creditCard.getCountry()))
			return "Country should only contain letters";
		else if (!Pattern.matches("^[0-9]{15,16}$", Long.toString(creditCard.getCardNo())))
			return "Please enter a valid Creid Card number";
		else if (!Pattern.matches("^[0-9]{3}$", Integer.toString(creditCard.getCvv())))
			return "Please enter a valid CVV";
		else if (!Pattern.matches("^[0-9]{3}$", Integer.toString(creditCard.getCvv())))
			return "Please enter a valid CVV";
		else if (expired == true)
			return "Credit Card is expired";
		else
			return "Credit Card Validation Successful";

	}

	public static boolean isCreditCardExpired(CreditCard card) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		sdf.setLenient(false);
		Date exp = null;
		
		try {
			
			exp = sdf.parse(card.getExpireDate());
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		
		return exp.before(new Date());

	}
	
	public static boolean isCreditLimitExeeded(double amount, CreditCard card) {
		
		return card.getCreditLimit() < amount;
		
	}
	
	public static ArrayList<CreditCard> getCreditCards(String userId) {

		Connection connection;
		PreparedStatement preparedStatement;
		ArrayList<CreditCard> cards = new ArrayList<CreditCard>();

		try {

			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM credit_card WHERE UserId = ?");
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				CreditCard card = new CreditCard();

				card.setName(resultSet.getString("Name"));
				card.setAddress(resultSet.getString("Address"));
				card.setCountry(resultSet.getString("Country"));
				card.setNetwork(resultSet.getString("Network"));
				card.setExpireDate(resultSet.getString("ExpireDate"));
				card.setCardNo(resultSet.getLong("CardNo"));
				card.setCvv(resultSet.getInt("Cvv"));
				card.setCreditLimit(resultSet.getDouble("CreditLimit"));
				card.setUserId(resultSet.getString("UserId"));

				cards.add(card);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return cards;

	}
	
	public static CreditCard getCreditCard(long creditCardNo) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		CreditCard card = new CreditCard();

		try {

			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM credit_card WHERE CardNo = ?");
			preparedStatement.setLong(1, creditCardNo);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				card.setName(resultSet.getString("Name"));
				card.setAddress(resultSet.getString("Address"));
				card.setCountry(resultSet.getString("Country"));
				card.setNetwork(resultSet.getString("Network"));
				card.setExpireDate(resultSet.getString("ExpireDate"));
				card.setCardNo(resultSet.getLong("CardNo"));
				card.setCvv(resultSet.getInt("Cvv"));
				card.setCreditLimit(resultSet.getDouble("CreditLimit"));
				card.setUserId(resultSet.getString("UserId"));
				
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return card;
		
	}
	
	public static String deleteCreditCard(long creditCardNo) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM credit_card WHERE CardNo = ?");
			preparedStatement.setLong(1, creditCardNo);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Credit Card Successfully Deleted";
			else
				status = "Failed to Delete Credit Card";
			
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.getStackTrace();
			
		}
		
		return status;
		
	}
	
	public static String updateCreditCard(long creditCardNo, double creditLimit) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE credit_card SET CreditLimit = ? WHERE CardNo = ?");
			preparedStatement.setDouble(1, creditLimit);
			preparedStatement.setLong(2, creditCardNo);
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Credit Card Successfully Updated";
			else
				status = "Failed to Update Credit Card";
			
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.getStackTrace();
			
		}
		
		return status;
		
	}

	public static String saveCreditCard(CreditCard creditCard) {

		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;

		try {

			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO credit_card VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setLong(1, creditCard.getCardNo());
			preparedStatement.setString(2, creditCard.getNetwork());
			preparedStatement.setInt(3, creditCard.getCvv());
			preparedStatement.setString(4, creditCard.getExpireDate());
			preparedStatement.setDouble(5, creditCard.getCreditLimit());
			preparedStatement.setString(6, creditCard.getName());
			preparedStatement.setString(7, creditCard.getAddress());
			preparedStatement.setString(8, creditCard.getCountry());
			preparedStatement.setString(9, creditCard.getUserId());

			int rows = preparedStatement.executeUpdate();

			if (rows > 0)
				status = "Credit Card Added Successfully";
			else
				status = "Failed to add Credit Card";

		} catch (Exception e) {

			status = e.getMessage();
			e.getStackTrace();

		}

		return status;

	}

}
