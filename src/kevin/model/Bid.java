/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.model
 * Bid.java
 */
package kevin.model;

/**
 * @author zenj8
 *
 */
public class Bid {

	private int id;
	private double bid;
	private int itemId;
	private String userId;
	private String bidDate;
	private String bidExpireDate;
	private long creditCard;
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
	 * @return the bid
	 */
	public double getBid() {
		return bid;
	}
	/**
	 * @param bid the bid to set
	 */
	public void setBid(double bid) {
		this.bid = bid;
	}
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
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
	 * @return the bidDate
	 */
	public String getBidDate() {
		return bidDate;
	}
	/**
	 * @param bidDate the bidDate to set
	 */
	public void setBidDate(String bidDate) {
		this.bidDate = bidDate;
	}
	/**
	 * @return the bidExpireDate
	 */
	public String getBidExpireDate() {
		return bidExpireDate;
	}
	/**
	 * @param bidExpireDate the bidExpireDate to set
	 */
	public void setBidExpireDate(String bidExpireDate) {
		this.bidExpireDate = bidExpireDate;
	}
	/**
	 * @return the creditCard
	 */
	public long getCreditCard() {
		return creditCard;
	}
	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(long creditCard) {
		this.creditCard = creditCard;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bid [id=" + id + ", bid=" + bid + ", itemId=" + itemId + ", userId=" + userId + ", bidDate=" + bidDate
				+ ", bidExpireDate=" + bidExpireDate + ", creditCard=" + creditCard + "]";
	}
	
}
