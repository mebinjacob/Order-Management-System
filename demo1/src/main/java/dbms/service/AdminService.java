package dbms.service;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void test(){
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
	}
	
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
}
