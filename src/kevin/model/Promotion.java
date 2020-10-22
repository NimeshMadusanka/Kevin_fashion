/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.model
 * Promotion.java
 */
package kevin.model;

/**
 * @author zenj8
 *
 */
public class Promotion {

	private int id;
	private int itemId;
	private double discountPrecentage;
	String name;
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
	 * @return the discountPrecentage
	 */
	public double getDiscountPrecentage() {
		return discountPrecentage;
	}
	/**
	 * @param discountPrecentage the discountPrecentage to set
	 */
	public void setDiscountPrecentage(double discountPrecentage) {
		this.discountPrecentage = discountPrecentage;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Promotion [id=" + id + ", itemId=" + itemId + ", discountPrecentage=" + discountPrecentage + ", name="
				+ name + "]";
	}
	
	
}
