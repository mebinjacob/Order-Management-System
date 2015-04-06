package dbms.service;

/**
 *@author Piyush Johar
 **/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
	String addCateory;
	
	public void addCategory(String category, String subCategory){
		Object[] params = new Object[] { category, subCategory };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR};
		jdbcTemplate.update(addCateory, params, types);
		System.out.println("row inserted.");
		
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

}
