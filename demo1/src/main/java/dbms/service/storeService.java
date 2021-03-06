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
	
	@Value("$(monthlystoreGrowth.sql)")
	String monthlyStore_Growth;
	
	@Value("${storeid.sql}")
	String storeIdSQL;
	
	@Value("${store_InventoryRequestLIst}")
	String invReqSql;
	
	@Value("${store_addInevtory}")
	String addInvsql;
	
	@Value("${monthlySalesReport.sql}")
	String monthlySalesReportSQL;
	
	@Value("${weeklysalesReport.sql}")
	String weeklysalesReportSQL;
	
	@Value("${store_StatsLeastpopular}")
	String minstore_sql; 

	@Value("${store_StatsMostPopular}")
	String maxstore_sql; 
	
	public  List<List<Object>> monthlySalesReport(String yearID)
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
				monthlySalesReportSQL, new Object[]{yearID, storeIdList.get(0)},
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
	
	public List<List<Object>> monthlyStoreGrowth(String yearID)
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
				monthlyStore_Growth, new Object[]{storeIdList.get(0), yearID,storeIdList.get(0), yearID},
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
	
	public List<List<Object>> weeklySalesReport(String monthID, String yearID)
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
				weeklysalesReportSQL, new Object[]{yearID, monthID, storeIdList.get(0)},
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
	
	
	
	
	public List<List<String>> selectItem_FromStore()
	{
		List<String> storeIdList = jdbcTemplate.query(
				storeIdSQL, new Object[]{LoginService.userID},
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getObject(1).toString();
					}
				});
		
		List<List<String>> queriedData;
		queriedData = new ArrayList<List<String>>();
		SqlRowSet data = jdbcTemplate.queryForRowSet(storeList_sql, new Object[]{storeIdList.get(0)});
		//SqlRowSet data = jdbcTemplate.queryForRowSet(storeList_sql);
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
	
	public List<List<String>> selectInventoryRequest_Store()
	{
		List<String> storeIdList = jdbcTemplate.query(
				storeIdSQL, new Object[]{LoginService.userID},
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getObject(1).toString();
					}
				});
		int storeID = Integer.parseInt(storeIdList.get(0));
		List<List<String>> queriedData;
		queriedData = new ArrayList<List<String>>();
		SqlRowSet data = jdbcTemplate.queryForRowSet(invReqSql, new Object[]{storeIdList.get(0)});
		//SqlRowSet data = jdbcTemplate.queryForRowSet(storeList_sql);
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
	
	public void addInventoryRequst(String itemId, int qty, String Comments)
	{
		int count = jdbcTemplate.queryForInt(inventory_Count);
		int reqId = ++count;
		List<String> storeIdList = jdbcTemplate.query(
				storeIdSQL, new Object[]{LoginService.userID},
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getObject(1).toString();
					}
				});
		Object[] params = new Object[] { reqId, Integer.parseInt(LoginService.userID), Integer.parseInt(itemId), qty, Comments, "Pending", /*Integer.parseInt(storeIdList.get(0))*/ 1};
		int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
		int nrows = jdbcTemplate.update(addInvsql, params, types);
		System.out.print("The number of rows Affecte" + nrows);
	}
	
	public List<List<String>> getminStoreData()
	{
		List<String> storeIdList = jdbcTemplate.query(
				storeIdSQL, new Object[]{LoginService.userID},
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getObject(1).toString();
					}
				});
		List<List<String>> queriedData;
		queriedData = new ArrayList<List<String>>();
		//int storeID = Integer.parseInt(storeIdList.get(0));
		SqlRowSet data = jdbcTemplate.queryForRowSet(minstore_sql, new Object[]{storeIdList.get(0)});
		//SqlRowSet data = jdbcTemplate.queryForRowSet(minstore_sql);
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
	
	public List<List<String>> getmaxStoreDetail()
	{
		List<String> storeIdList = jdbcTemplate.query(
				storeIdSQL, new Object[]{LoginService.userID},
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getObject(1).toString();
					}
				});
		
		List<List<String>> queriedData;
		queriedData = new ArrayList<List<String>>();
		SqlRowSet data = jdbcTemplate.queryForRowSet(maxstore_sql, new Object[]{storeIdList.get(0)});
		//SqlRowSet data = jdbcTemplate.queryForRowSet(maxstore_sql);
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
}
