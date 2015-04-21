package dbms.service;

/**
 *@author Mebin Jacob
 **/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import dbms.dao.Item;
import dbms.dao.regionSale;
import dbms.dto.IvmgmtPieChartDTO;

@Service
public class InventoryManagerService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${invmgmtProducts.sql}")
	String invmgmtProducts;
	
	@Value("${invmgmtProductsByCategroy.sql}")
	String inmgmtProductsByCategorySQl;

	@Value("${invmgmtRatingGreaterThan4.sql}")
	String productsBasedOnRating;
	
	@Value("${invmonthlysalesreport.sql}")
	String invmonthlySalesReportSQL;
	
	@Value("${invweeklyreport.sql}")
	String invweeklySalesReportSQL;
	
	@Value("${inventory_regionWisesale.sql}")
	String inventory_regionWisesale;	

	@Value("${storeIdSQL_InvMgr.sql}")
	String storeIdSQL_InvMgr;	
	
	
	public boolean saveOrder(String itemid, String quantity, String comment, String price){
		
		return false;
	}

	public List<regionSale> getRegionWiseProducts(int pageNo) {
		List<regionSale> results = jdbcTemplate.query(
				inventory_regionWisesale, new Object[]{pageNo * 500},
				new RowMapper<regionSale>() {
					@Override
					public regionSale mapRow(ResultSet rs, int rowNum) throws SQLException {

						regionSale item = new regionSale();
						item.setSales(Double.parseDouble((String)rs.getObject(1)));
						item.setRegion((String)rs.getObject(2));
						return item;
					}
				});
		return results;
	}
	
	public List<List<String>> getInventoryRequest_Store()
	{

		List<List<String>> queriedData;
		queriedData = new ArrayList<List<String>>();
		SqlRowSet data = jdbcTemplate.queryForRowSet(storeIdSQL_InvMgr);
		SqlRowSetMetaData mData = data.getMetaData();
		while(data.next())
		{
			List<String> colData = new ArrayList<String>();
			for(int i = 1; i <= mData.getColumnCount(); i++)
				colData.add(data.getString(i));
			queriedData.add(colData);
		}
		return queriedData;
	}
	
	public List<Item> getAllProducts(int pageNo) {
		List<Item> results = jdbcTemplate.query(
				invmgmtProducts, new Object[]{pageNo * 500},
				new RowMapper<Item>() {
					@Override
					public Item mapRow(ResultSet rs, int rowNum) throws SQLException {

						Item item = new Item();
//						item.setItemId(Integer.parseInt((String)rs.getObject(1)));
						item.setActive((String)rs.getObject(2));
						item.setSellingPrice(rs.getObject(3).toString());
						item.setName((String)rs.getObject(4));
						
						item.setDiscountId(rs.getObject(6).toString());
						item.setOrderId((String)rs.getObject(7));
						return item;
					}
				});
		return results;
	}
	
	
	public List<Item> getAllProductsBasedOnReview(int pageNo) {
		List<Item> results = jdbcTemplate.query(
				productsBasedOnRating, new Object[]{pageNo * 500},
				new RowMapper<Item>() {
					@Override
					public Item mapRow(ResultSet rs, int rowNum) throws SQLException {

						Item item = new Item();
//						item.setItemId(Integer.parseInt((String)rs.getObject(1)));
						item.setActive((String)rs.getObject(2));
						item.setSellingPrice(rs.getObject(3).toString());
						item.setName((String)rs.getObject(4));
						
						item.setDiscountId(rs.getObject(6).toString());
						item.setOrderId((String)rs.getObject(7));
						return item;
					}
				});
		return results;
	}
	
	public List<IvmgmtPieChartDTO> getProductsByCategory(int pageNo) {
		List<IvmgmtPieChartDTO> results = jdbcTemplate.query(
				inmgmtProductsByCategorySQl, new Object[]{pageNo * 500},
				new RowMapper<IvmgmtPieChartDTO>() {
					@Override
					public IvmgmtPieChartDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

						IvmgmtPieChartDTO ivmgmtPieChartDTO = new IvmgmtPieChartDTO();
						ivmgmtPieChartDTO.setCategory(rs.getObject(2).toString());
						ivmgmtPieChartDTO.setCount(Integer.parseInt(rs.getObject(1).toString()));
						return ivmgmtPieChartDTO;
					}
				});
		return results;
	}
	
	
	public List<List<Object>> getMonthlySalesReport(String month, String year){
		
		List<List<Object>> results = jdbcTemplate.query(
				invmonthlySalesReportSQL, new Object[]{year, month},
				new RowMapper<List<Object>>() {
					@Override
					public List<Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						List<Object> storeSalesReportList = new ArrayList<Object>();
						storeSalesReportList.add(rs.getString(1).toString());
						if(null != rs.getString(2)){
							
							storeSalesReportList.add(Integer.parseInt(rs.getString(2)));
						}
						else{
							storeSalesReportList.add(0);
						}
						
						return storeSalesReportList;
					}
				});
		return results;
	}
	
	
public List<List<Object>> getWeeklySalesReport(String year, String month, String week){
		
		List<List<Object>> results = jdbcTemplate.query(
				invweeklySalesReportSQL, new Object[]{year, month, week},
				new RowMapper<List<Object>>() {
					@Override
					public List<Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						List<Object> storeSalesReportList = new ArrayList<Object>();
						storeSalesReportList.add(rs.getString(1).toString());
						if(null != rs.getString(2)){
							
							storeSalesReportList.add(Integer.parseInt(rs.getString(2)));
						}
						else{
							storeSalesReportList.add(0);
						}
						
						return storeSalesReportList;
					}
				});
		return results;
	}
	
}
