package dbms.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import bsh.org.objectweb.asm.Type;

@Service
public class storeService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Value("${storeList.sql}")
	String storeList_sql;
	
	@Value("${store_InventoryCount}")
	String inventory_Count;
	
	@Value("${storeid.sql}")
	String storeIdSQL;
	
	@Value("${store_addInevtory}")
	String addInvsql;
	
	@Value("${monthlySalesReport.sql}")
	String monthlySalesReportSQL;
	
	@Value("${weeklysalesReport.sql}")
	String weeklysalesReportSQL;
	

	public  List<List<Object>> monthlySalesReport()
	{
		
		
		List<String> storeIdList = jdbcTemplate.query(
				storeIdSQL, new Object[]{LoginService.userID},
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getObject(1).toString();
					}
				});
		
		List<List<Object>> results = jdbcTemplate.query(
				monthlySalesReportSQL, new Object[]{storeIdList.get(0)},
				new RowMapper<List<Object>>() {
					@Override
					public List<Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						List<Object> storeSalesReportList = new ArrayList<Object>();
						storeSalesReportList.add(Integer.parseInt(rs.getString(1)));
						storeSalesReportList.add(Integer.parseInt(rs.getString(2)));
						return storeSalesReportList;
					}
				});
		return results;
	}
	
	
	
	public List<List<Object>> weeklySalesReport(String monthID)
	{
		
		
		List<String> storeIdList = jdbcTemplate.query(
				storeIdSQL, new Object[]{LoginService.userID},
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getObject(1).toString();
					}
				});
		
		List<List<Object>> results = jdbcTemplate.query(
				weeklysalesReportSQL, new Object[]{monthID, storeIdList.get(0)},
				new RowMapper<List<Object>>() {
					@Override
					public List<Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						List<Object> storeSalesReportList = new ArrayList<Object>();
						storeSalesReportList.add(Integer.parseInt(rs.getString(1)));
						storeSalesReportList.add(Integer.parseInt(rs.getString(2)));
						return storeSalesReportList;
					}
				});
		return results;
	}
	
	
	
	
	public List<List<String>> selectItem_FromStore(int StoreId)
	{
		List<List<String>> queriedData;
		queriedData = new ArrayList<List<String>>();
		SqlRowSet data = jdbcTemplate.queryForRowSet(storeList_sql, new Object[]{StoreId});
		//SqlRowSet data = jdbcTemplate.queryForRowSet(storeList_sql);
		SqlRowSetMetaData mData = data.getMetaData();
		List<String> colName = new ArrayList<String>();
		for(int i = 1; i <= mData.getColumnCount(); i++)
			colName.add(mData.getColumnName(i));
		queriedData.add(colName);
		while(data.next())
		{
			List<String> colData = new ArrayList<String>();
			for(int i = 1; i <= mData.getColumnCount(); i++)
				colData.add(data.getString(i));
			queriedData.add(colData);
		}
		return queriedData;
	}
	
	public List<List<String>> selectInventoryRequest_Store(int storeId)
	{
		List<List<String>> queriedData;
		queriedData = new ArrayList<List<String>>();
		SqlRowSet data = jdbcTemplate.queryForRowSet(storeList_sql, new Object[]{storeId});
		//SqlRowSet data = jdbcTemplate.queryForRowSet(storeList_sql);
		SqlRowSetMetaData mData = data.getMetaData();
		List<String> colName = new ArrayList<String>();
		for(int i = 1; i <= mData.getColumnCount(); i++)
			colName.add(mData.getColumnName(i));
		queriedData.add(colName);
		while(data.next())
		{
			List<String> colData = new ArrayList<String>();
			for(int i = 1; i <= mData.getColumnCount(); i++)
				colData.add(data.getString(i));
			queriedData.add(colData);
		}
		return queriedData;
	}
	
	public List<List<String>> selectDiscountAndOffers(int storeId)
	{
		List<List<String>> queriedData;
		queriedData = new ArrayList<List<String>>();
		//SqlRowSet data = jdbcTemplate.queryForRowSet(storeList_sql, new Object[]{StoreId});
		SqlRowSet data = jdbcTemplate.queryForRowSet(storeList_sql);
		SqlRowSetMetaData mData = data.getMetaData();
		List<String> colName = new ArrayList<String>();
		for(int i = 1; i <= mData.getColumnCount(); i++)
			colName.add(mData.getColumnName(i));
		queriedData.add(colName);
		while(data.next())
		{
			List<String> colData = new ArrayList<String>();
			for(int i = 1; i <= mData.getColumnCount(); i++)
				colData.add(data.getString(i));
			queriedData.add(colData);
		}
		return queriedData;
	}
	
	public void addInventoryRequst(String empId, String itemId, int qty, String Comments)
	{
		int count = jdbcTemplate.queryForInt(inventory_Count);
		int reqId = ++count;
		Object[] params = new Object[] { reqId, Integer.parseInt(empId), Integer.parseInt(itemId), qty, Comments, "Pending" };
		int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR};
		int nrows = jdbcTemplate.update(addInvsql, params, types);
		System.out.print("The number of rows Affecte" + nrows);
	}
}
