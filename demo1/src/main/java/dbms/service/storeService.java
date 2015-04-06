package dbms.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

@Service
public class storeService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Value("${storeList.sql}")
	String storeList_sql;
	
	public List<List<String>> selectItem_FromStore(int StoreId)
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
	
	public List<List<String>> selectInventoryRequest_Store(int storeId)
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
}
