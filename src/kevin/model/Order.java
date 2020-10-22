/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.model
 * Order.java
 */
package kevin.model;

public class Order {

	private int id;
	private String userId;
	private String description;
	private int quantity;
	private String imagePath;
	private boolean accepted;
	private double cost;
	private boolean payed;
	private Long creditCardNo;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * @return the accepted
	 */
	public boolean isAccepted() {
		return accepted;
	}
	/**
	 * @param accepted the accepted to set
	 */
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * @return the payed
	 */
	public boolean isPayed() {
		return payed;
	}
	/**
	 * @param payed the payed to set
	 */
	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	/**
	 * @return the creditCardNo
	 */
	public Long getCreditCardNo() {
		return creditCardNo;
	}
	/**
	 * @param creditCardNo the creditCardNo to set
	 */
	public void setCreditCardNo(Long creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", description=" + description + ", quantity=" + quantity
				+ ", imagePath=" + imagePath + ", accepted=" + accepted + ", cost=" + cost + ", payed=" + payed
				+ ", creditCardNo=" + creditCardNo + "]";
	}
	
}
