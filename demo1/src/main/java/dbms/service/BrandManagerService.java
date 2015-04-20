package dbms.service;

/**
 *@author Piyush Johar
 **/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dbms.dao.*;
import dbms.dto.IvmgmtPieChartDTO;

@Service
public class BrandManagerService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${brndMgrProducts.sql}")
	String brndMgrProducts;
	
	@Value("${invmgmtProductsByCategroy.sql}")
	String inmgmtProductsByCategorySQl;

	@Value("${invmgmtRatingGreaterThan4.sql}")
	String productsBasedOnRating;
	
	@Value("${brndMgrAddCategory.sql}")
	String addCategory;
	
	@Value("${brndmonthlysalesreport.sql}")
	String brndmonthlySalesReportSQL;
	
	@Value("${brndweeklyreport.sql}")
	String brndweeklySalesReportSQL;
	
	@Value("${brndMgrAddSubCategory.sql}")
	String brndMgrAddSubCategorySQL;
	
	public void addCategory(String category, String subCategory){
		Object[] params = new Object[] {category};
		int[] types = new int[] {Types.VARCHAR};
		jdbcTemplate.update(addCategory, category);
		//jdbcTemplate.update(addCategory, params, types);
		System.out.println("row inserted.");
		/*if(subCategory != null)
		{			
			Object[] params1 = new Object[] { subCategory };
			int[] types1 = new int[] {  Types.VARCHAR};			
			jdbcTemplate.update(brndMgrAddSubCategorySQL, params1, Types.VARCHAR);			
		}*/
	}
	public List<Item> getAllProducts(int pageNo) {
		List<Item> results = jdbcTemplate.query(
				brndMgrProducts, new Object[]{pageNo * 500},
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
				brndmonthlySalesReportSQL, new Object[]{year, month},
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
				brndweeklySalesReportSQL, new Object[]{year, month, week},
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
