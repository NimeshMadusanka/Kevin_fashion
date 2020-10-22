/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.model
 * Admin.java
 */
package kevin.model;

public class Admin {

	private static Admin ADMIN;
	private String password = "1234";
	
	private Admin() {}
	
	public static Admin getAdmin() {
		
		if (ADMIN == null) {
			
			synchronized (Admin.class) {
				
				if (ADMIN == null) {
					
					ADMIN = new Admin();
					
				}
				
			}
			
		}
		
		return ADMIN;
		
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		ADMIN.password = password;
	}
	
}
