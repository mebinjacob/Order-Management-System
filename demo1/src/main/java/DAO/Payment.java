package DAO;

import java.util.Date;

public class Payment {
	private int billId;
	private Date billDate;
	private int total;
	private String billStatus;
	private int quantity;
	private int brandmanagerId;
	private int inventorymanagerId;
	
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getBrandmanagerId() {
		return brandmanagerId;
	}
	public void setBrandmanagerId(int brandmanagerId) {
		this.brandmanagerId = brandmanagerId;
	}
	public int getInventorymanagerId() {
		return inventorymanagerId;
	}
	public void setInventorymanagerId(int inventorymanagerId) {
		this.inventorymanagerId = inventorymanagerId;
	}
	
}
