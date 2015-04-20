package dbms.dao;

import java.math.BigDecimal;

public class regionSale {
	private String region;
	private double sales;
	
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}	
}
