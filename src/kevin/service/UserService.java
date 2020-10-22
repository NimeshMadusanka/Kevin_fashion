/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.service
 * UserService.java
 */
package kevin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;

import kevin.model.User;
import kevin.util.DBConnect;

public class UserService {
	
	public static User setUser(String firstName, String lastName, String email, String telephone, String password, String confirmPassword, String postalNumber, String street, String city, String district) {
		
		User user = new User();
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setPassword(password);
		user.setConfirmPassword(confirmPassword);
		user.setPostalNumber(postalNumber);
		user.setStreet(street);
		user.setCity(city);
		user.setDistrict(district);
		
		return user;
		
	}
	
	public static User getUser(String email) {
		
		User usr = new User();
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE Email = ?");
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				usr.setEmail(resultSet.getString("Email"));
				usr.setFirstName(resultSet.getString("FirstName"));
				usr.setLastName(resultSet.getString("LastName"));
				usr.setTelephone(resultSet.getString("Telephone"));
				usr.setPassword(resultSet.getString("Password"));
				usr.setConfirmPassword(resultSet.getString("Password"));
				usr.setPostalNumber(resultSet.getString("PostalNumber"));
				usr.setStreet(resultSet.getString("Street"));
				usr.setCity(resultSet.getString("City"));
				usr.setDistrict(resultSet.getString("District"));
				usr.setPackageId(resultSet.getInt("Package"));
				
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return usr;
		
	}
	
	public static ArrayList<User> getUsers() {
		
		ArrayList<User> users = new ArrayList<User>();
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM user");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				User usr = new User();
				
				usr.setEmail(resultSet.getString("Email"));
				usr.setFirstName(resultSet.getString("FirstName"));
				usr.setLastName(resultSet.getString("LastName"));
				usr.setTelephone(resultSet.getString("Telephone"));
				usr.setPassword(resultSet.getString("Password"));
				usr.setConfirmPassword(resultSet.getString("Password"));
				usr.setPostalNumber(resultSet.getString("PostalNumber"));
				usr.setStreet(resultSet.getString("Street"));
				usr.setCity(resultSet.getString("City"));
				usr.setDistrict(resultSet.getString("District"));
				usr.setPackageId(resultSet.getInt("Package"));
				
				users.add(usr);
				
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		return users;
		
	}
	
	public static String validateUser(User user) {
		
		if (!Pattern.matches("^[a-zA-Z]+$", user.getFirstName()))
			return "First Name should only contain letters";
		else if (!Pattern.matches("^[a-zA-Z]+$", user.getLastName()))
			return "Last Name should only contain letters";
		else if (!Pattern.matches("^[a-z0-9._]+@[a-z]+\\.[a-z]+$", user.getEmail()))
			return "Please enter a vali e-mail address";
		else if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", user.getPassword()))
			return "Please enter a valid password";
		else if (!user.getConfirmPassword().equals(user.getPassword()))
			return "Confirm Password should match Password";
		else if (!Pattern.matches("^[0-9]{10}$", user.getTelephone()))
			return "Please enter a valid telephone number";
		else if (!Pattern.matches("^.+$", user.getPostalNumber()))
			return "Please enter a valid Postal Number";
		else if (!Pattern.matches("^.+$", user.getStreet()))
			return "Please enter a valid Street";
		else if (!Pattern.matches("^.+$", user.getCity()))
			return "Please enter a valid City";
		else if (!Pattern.matches("^.+$", user.getDistrict()))
			return "Please enter a valid District";
		else
			return "User Validated";
		
	}
	
	public static String saveUser(User user) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String status = null;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO user (Email, FirstName, LastName, Telephone, Password, PostalNumber, Street, City, District) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getTelephone());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getPostalNumber());
			preparedStatement.setString(7, user.getStreet());
			preparedStatement.setString(8, user.getCity());
			preparedStatement.setString(9, user.getDistrict());
			
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0)
				status = "Registration Successful";
			else
				status = "Registration Failed";
			
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			status = e.getMessage();
			e.printStackTrace();
			
		}
		
		return status;
		
	}
	
	public static boolean validateLogin(String email, String password) {
		
		if (password.equals(UserService.getUser(email).getPassword()))
			return true;
		else
			return false;
		
	}
	
	public static String validateEdit(User user) {

		if (!Pattern.matches("^[a-zA-Z]+$", user.getFirstName()) && !user.getFirstName().equals(""))
			return "First Name should only contain letters";
		else if (!Pattern.matches("^[a-zA-Z]+$", user.getLastName()) && !user.getLastName().equals(""))
			return "Last Name should only contain letters";
		else if (!Pattern.matches("^[a-z0-9._]+@[a-z]+\\.[a-z]+$", user.getEmail()) && !user.getEmail().equals(""))
			return "Please enter a vali e-mail address";
		else if (!Pattern.matches("^[0-9]{10}$", user.getTelephone()) && !user.getTelephone().equals(""))
			return "Please enter a valid telephone number";
		else if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", user.getPassword()) && !user.getPassword().equals(""))
			return "Please enter a valid password";
		else if (!user.getConfirmPassword().equals(user.getPassword()))
			return "Confirm Password should match Password";
		else if (!Pattern.matches("^.+$", user.getPostalNumber()) && !user.getPostalNumber().equals(""))
			return "Please enter a valid Postal Number";
		else if (!Pattern.matches("^.+$", user.getStreet()) && !user.getStreet().equals(""))
			return "Please enter a valid Street";
		else if (!Pattern.matches("^.+$", user.getCity()) && !user.getCity().equals(""))
			return "Please enter a valid City";
		else if (!Pattern.matches("^.+$", user.getDistrict()) && !user.getDistrict().equals(""))
			return "Please enter a valid District";
		else
			return "Profile Updated";

	}
	
	public static User updateUser(User currentUser, User user) {

		Connection connection;
		PreparedStatement preparedStatement;

		try {

			connection = DBConnect.getDBConnection();

			if (!user.getFirstName().equals("")) {

				preparedStatement = connection.prepareStatement("UPDATE user SET FirstName = ? WHERE Email = ? ");
				preparedStatement.setString(1, user.getFirstName());
				preparedStatement.setString(2, currentUser.getEmail());
				preparedStatement.execute();

				currentUser.setFirstName(user.getFirstName());

			}

			if (!user.getLastName().equals("")) {

				preparedStatement = connection.prepareStatement("UPDATE user SET LastName = ? WHERE Email = ? ");
				preparedStatement.setString(1, user.getLastName());
				preparedStatement.setString(2, currentUser.getEmail());
				preparedStatement.execute();

				currentUser.setLastName(user.getLastName());

			}

			if (!user.getTelephone().equals("")) {

				preparedStatement = connection.prepareStatement("UPDATE user SET Telephone = ? WHERE Email = ? ");
				preparedStatement.setString(1, user.getTelephone());
				preparedStatement.setString(2, currentUser.getEmail());
				preparedStatement.execute();

				currentUser.setTelephone(user.getTelephone());

			}

			if (!user.getPostalNumber().equals("")) {

				preparedStatement = connection.prepareStatement("UPDATE user SET PostalNumber = ? WHERE Email = ? ");
				preparedStatement.setString(1, user.getPostalNumber());
				preparedStatement.setString(2, currentUser.getEmail());
				preparedStatement.execute();

				currentUser.setPostalNumber(user.getPostalNumber());

			}

			if (!user.getStreet().equals("")) {

				preparedStatement = connection.prepareStatement("UPDATE user SET Street = ? WHERE Email = ? ");
				preparedStatement.setString(1, user.getStreet());
				preparedStatement.setString(2, currentUser.getEmail());
				preparedStatement.execute();

				currentUser.setStreet(user.getStreet());

			}

			if (!user.getCity().equals("")) {

				preparedStatement = connection.prepareStatement("UPDATE user SET City = ? WHERE Email = ? ");
				preparedStatement.setString(1, user.getCity());
				preparedStatement.setString(2, currentUser.getEmail());
				preparedStatement.execute();

				currentUser.setCity(user.getCity());

			}

			if (!user.getDistrict().equals("")) {

				preparedStatement = connection.prepareStatement("UPDATE user SET District = ? WHERE Email = ? ");
				preparedStatement.setString(1, user.getDistrict());
				preparedStatement.setString(2, currentUser.getEmail());
				preparedStatement.execute();

				currentUser.setDistrict(user.getDistrict());

			}
			
			if (!user.getPassword().equals("") && !user.getConfirmPassword().equals("") && user.getPassword().equals(user.getConfirmPassword()) && user.getPassword().equals(currentUser.getPassword())) {
				
				preparedStatement = connection.prepareStatement("UPDATE user SET Password = ? WHERE Email = ? ");
				preparedStatement.setString(1, user.getPassword());
				preparedStatement.setString(2, currentUser.getEmail());
				preparedStatement.execute();

				currentUser.setDistrict(user.getPassword());
				
			}

			connection.close();

			return currentUser;

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		return currentUser;

	}
	
	public static boolean removeUser(String email) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		boolean result = false;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM user WHERE Email = ?");
			preparedStatement.setString(1, email);
			result = preparedStatement.execute();
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
}