package dbms.service;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

//import scala.Array;
import dbms.dao.Item;

@Service
public class AdminService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Value("${adminProducts.sql}")
	String adminProducts;
	
	@Value("${brndMgrAddCategory.sql}")
	String addCategory;
	
	@Value("${adminAddUser.sql}")
	String addUser;
	
	@Value("${adminAddItem.sql}")
	String addItem;
	
	@Value("${brndMgrAddSubCategory.sql}")
	String brndMgrAddSubCategorySQL;
	
	/*public void test(){
        jdbcTemplate.execute("create table customers(" +
                " first_name varchar(255), last_name varchar(255))");

        String[] fullNames = new String[]{"John Woo", "Jeff Dean", "Josh Bloch", "Josh Long"};
        for (String fullname : fullNames) {
            String[] name = fullname.split(" ");
            System.out.printf("Inserting customer record for %s %s\n", name[0], name[1]);
            jdbcTemplate.update(
                    "INSERT INTO customers(first_name,last_name) values(?,?)",
                    name[0], name[1]);
        }    
	}*/

	/*public void executeQuery() throws IOException
	{
		String sql = "SELECT COUNTRY , COUNT(*)FROM GEO_LAKE GROUP BY COUNTRY";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		createPieCharts(rs, "Barchart", "D:/Piecharts.png");
	}*/
	
	public void createBarChart(SqlRowSet rowSet, String charTitle, String fileLocation) throws IOException
	{
		SqlRowSetMetaData mData = rowSet.getMetaData();
		String col1 = mData.getColumnName(1);
		String col2 = mData.getColumnName(2);
		int image_width = 1024;
		int image_height = 1024;
		DefaultCategoryDataset charDataset = new DefaultCategoryDataset();
		while(rowSet.next())
		{
			String value1 = rowSet.getString(col1);
			int value2 = rowSet.getInt(col2);
			charDataset.addValue(value2, col2, value1);
		}
		JFreeChart BarChartObject=ChartFactory.createBarChart(charTitle,col1,col2,
				charDataset,PlotOrientation.VERTICAL,true,true,false); 
		 File BarChart=new File(fileLocation);   
		 ChartUtilities.saveChartAsPNG(BarChart,BarChartObject,image_width,image_height); 
	}
	
	/*
	 * *
	 */
	public void createPieCharts(SqlRowSet rowSet, String chartTitle, String fileLocation) throws IOException
	{
		SqlRowSetMetaData mData = rowSet.getMetaData();
		String col1 = mData.getColumnName(1);
		String col2 = mData.getColumnName(2);
		DefaultPieDataset chartDataset = new DefaultPieDataset();
		int image_height = 1024;
		int image_width = 1024;
		float quality = 1;
		while(rowSet.next())
		{
			String value1 = rowSet.getString(col1);
			int value2 = rowSet.getInt(col2);
			chartDataset.setValue(value1, value2);
		}
		JFreeChart PieChartObject=ChartFactory.createPieChart(chartTitle, chartDataset,true,true,false);
		File BarChart=new File(fileLocation);              
        ChartUtilities.saveChartAsJPEG(BarChart, quality, PieChartObject,image_width,image_height); 
		
	}
	
	public List<Item> getAllProducts(int pageNo) {
		List<Item> results = jdbcTemplate.query(
				adminProducts, new Object[]{pageNo * 500},
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
	
	public List<List<String>> getTupuleCount() throws SQLException{
		String sql = "SELECT COUNT(*) FROM STORE_ITEM_DETAILS";
		int count = jdbcTemplate.queryForInt(sql);
		List<String> rs = new ArrayList<String>();
		rs.add("Number of Rows in STORE ITEM DETAILS");
		rs.add(Integer.toString(count));
		List<List<String>> queriedData = new ArrayList<List<String>>();
		queriedData.add(rs);
		return queriedData;
	}
	
	public void addCategory(String category, String subCategory){
		Object[] params = new Object[] {category};
		int[] types = new int[] {Types.VARCHAR};
		jdbcTemplate.update(addCategory, category);
		//jdbcTemplate.update(addCategory, params, types);
		System.out.println("row inserted.");
		System.out.println();
		if(subCategory != null)
		{			
			Object[] params1 = new Object[] { subCategory };
//			int[] types1 = new int[] {  Types.VARCHAR};			
			jdbcTemplate.update(brndMgrAddSubCategorySQL, params1);			
		}
	}
	
	public void addUser(			String firstName, String middleName, String lastName, Integer contactNumber, String password, String role, String street, String city, String state, Integer zipCode, String email){
		Object[] params = new Object[] {firstName, middleName, lastName, contactNumber, password, Integer.parseInt(role), street, city, state, zipCode, email};
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};
		jdbcTemplate.update(addUser, params, types);
		System.out.println("row inserted.");		
	}
	
	public void addItem(String isActive, Double sellingPrice, String itemName, String description, Integer discount_id, Integer order_id){
		Object[] params = new Object[] {isActive, sellingPrice, itemName, description, discount_id, order_id};
		int[] types = new int[] { Types.VARCHAR, Types.DOUBLE, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
		jdbcTemplate.update(addItem, params, types);
		System.out.println("row inserted.");		
	}
}
