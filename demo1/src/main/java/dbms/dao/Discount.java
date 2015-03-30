package dbms.dao;

import java.util.Date;

public class Discount {
	private int id;
	private Date startDate;
	private Date endDate;
	private String active;
	private int percent;
	private int storeManagerId;
	private int inventoryManagerId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public int getStoreManagerId() {
		return storeManagerId;
	}
	public void setStoreManagerId(int storeManagerId) {
		this.storeManagerId = storeManagerId;
	}
	public int getInventoryManagerId() {
		return inventoryManagerId;
	}
	public void setInventoryManagerId(int inventoryManagerId) {
		this.inventoryManagerId = inventoryManagerId;
	}
	
	
}
