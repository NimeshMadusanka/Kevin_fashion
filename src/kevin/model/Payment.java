/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.model
 * Payment.java
 */
package kevin.model;

public class Payment {

	private int id;
	private double price;
	private String userId;
	private long creditCardNo;
	private String date;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the creditCardNo
	 */
	public long getCreditCardNo() {
		return creditCardNo;
	}
	/**
	 * @param creditCardNo the creditCardNo to set
	 */
	public void setCreditCardNo(long creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Payment [id=" + id + ", price=" + price + ", userId=" + userId + ", creditCardNo=" + creditCardNo
				+ ", date=" + date + "]";
	}
	
	
	
}
