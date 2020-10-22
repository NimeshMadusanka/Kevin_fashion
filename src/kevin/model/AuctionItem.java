/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.model
 * AuctionItem.java
 */
package kevin.model;

public class AuctionItem {

	private int id;
	private String name;
	private double startPrice;
	private String category;
	private String size;
	private int quantity;
	private String color;
	private String description;
	private String imagePath;
	private int durationDays;
	private int durationHours;
	private int durationMinutes;
	private double highestBid;
	private int bidCount;
	private boolean sold;
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
	/**
	 * @return the startPrice
	 */
	public double getStartPrice() {
		return startPrice;
	}
	/**
	 * @param startPrice the startPrice to set
	 */
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
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
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
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
	 * @return the durationDays
	 */
	public int getDurationDays() {
		return durationDays;
	}
	/**
	 * @param durationDays the durationDays to set
	 */
	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}
	/**
	 * @return the durationHours
	 */
	public int getDurationHours() {
		return durationHours;
	}
	/**
	 * @param durationHours the durationHours to set
	 */
	public void setDurationHours(int durationHours) {
		this.durationHours = durationHours;
	}
	/**
	 * @return the durationMinutes
	 */
	public int getDurationMinutes() {
		return durationMinutes;
	}
	/**
	 * @param durationMinutes the durationMinutes to set
	 */
	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	/**
	 * @return the highestBid
	 */
	public double getHighestBid() {
		return highestBid;
	}
	/**
	 * @param highestBid the highestBid to set
	 */
	public void setHighestBid(double highestBid) {
		this.highestBid = highestBid;
	}
	/**
	 * @return the bidCount
	 */
	public int getBidCount() {
		return bidCount;
	}
	/**
	 * @param bidCount the bidCount to set
	 */
	public void setBidCount(int bidCount) {
		this.bidCount = bidCount;
	}
	/**
	 * @return the sold
	 */
	public boolean isSold() {
		return sold;
	}
	/**
	 * @param sold the sold to set
	 */
	public void setSold(boolean sold) {
		this.sold = sold;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuctionItem [id=" + id + ", name=" + name + ", startPrice=" + startPrice + ", category=" + category
				+ ", size=" + size + ", quantity=" + quantity + ", color=" + color + ", description=" + description
				+ ", imagePath=" + imagePath + ", durationDays=" + durationDays + ", durationHours=" + durationHours
				+ ", durationMinutes=" + durationMinutes + ", highestBid=" + highestBid + ", bidCount=" + bidCount
				+ "]";
	}

}
