package edu.uic.ids.action;

import java.sql.ResultSet;

import javax.servlet.jsp.jstl.sql.Result;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.MonthConstants;

import java.sql.ResultSetMetaData;

import edu.uic.ids.model.DatabaseBean;
import edu.uic.ids.model.StatsManagedBean;


public class Reports {

	private DatabaseBean dbBean;
   private JFreeChart pieChart;
   private Result result;
   private DatabaseAccess dbAccess;
   private ResultSet resultSet;
   private DefaultPieDataset pieModel;
   private ResultSetMetaData resultSetMetaData;
   private String errorMessage;
   private boolean renderErrorMessage;
   private boolean renderPieChart;
   private XYDataset data ;
   private DefaultCategoryDataset dataset ;
   private StatsManagedBean statsManagedBean;
 
   public Reports()
   {
	   pieModel=new DefaultPieDataset();
	   dataset = new DefaultCategoryDataset();
   }

   public boolean generateChart(String pieChartColumnSelected,String tableSelected) 
   {
		try
		{
			pieModel.clear();
			dataset.clear();
			double quartileOne =statsManagedBean.getQuartileOne();
			double quartileThree = statsManagedBean.getQuartileThree();
			double median = statsManagedBean.getMedian();
			int countMedian = 0;
			int countQuartileOne =0;
			int countQuartileThree	=0;
			int greaterThanQuartileThree = 0;
			String sqlQuery="Select " + pieChartColumnSelected + " from" +tableSelected;
			resultSet=dbAccess.selectQueryProcessing(sqlQuery);
			resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
			String datatype =resultSetMetaData.getColumnTypeName(1);
			int value=0;
			float floatColumn=0;
			double doubleColumn=0;
			int smallIntColumn=0;
			long longColumn=0;
			if(resultSet!=null)
			{
				while(resultSet.next())
				{
					switch(datatype.toLowerCase())
					{
						case "int":
							value=resultSet.getInt(1);
							if(value <= quartileOne)
							{
								countQuartileOne++;
							}
							if(value> quartileOne && value<=median)
								countMedian++;
							if(value> median && value<=quartileThree)
								countQuartileThree++;
							if(value> quartileThree)
								greaterThanQuartileThree++;
							break;
						case "smallint":
							smallIntColumn=resultSet.getInt(1);
							if(smallIntColumn <= quartileOne)
								countQuartileOne++;
							if(smallIntColumn> quartileOne && smallIntColumn<=median)
								countMedian++;
							if(smallIntColumn> median && smallIntColumn<=quartileThree)
								countQuartileThree++;
							if(smallIntColumn> quartileThree)
								greaterThanQuartileThree++;
							break;
						case "float":
							floatColumn=resultSet.getFloat(1);
							if(floatColumn <= quartileOne)
								countQuartileOne++;
							if(floatColumn> quartileOne && floatColumn<=median)
								countMedian++;
							if(floatColumn> median && floatColumn<=quartileThree)
								countQuartileThree++;
							if(floatColumn> quartileThree)
								greaterThanQuartileThree++;
							break;
						case "double":
							doubleColumn=resultSet.getDouble(1);
							if(doubleColumn <= quartileOne)
								countQuartileOne++;
							if(doubleColumn> quartileOne && doubleColumn<=median)
								countMedian++;
							if(doubleColumn> median && doubleColumn<=quartileThree)
								countQuartileThree++;
							if(doubleColumn> quartileThree)
								greaterThanQuartileThree++;
							break;
						case "long":
							longColumn=resultSet.getLong(1);
							if(longColumn <= quartileOne)
								countQuartileOne++;
							if(longColumn> quartileOne && longColumn<=median)
								countMedian++;
							if(longColumn> median && longColumn<=quartileThree)
								countQuartileThree++;
							if(longColumn> quartileThree)
								greaterThanQuartileThree++;
							break;
					}
				}
				pieModel.setValue("Quartile quartileOne", countQuartileOne);
				pieModel.setValue("Lies between Quartile quartileOne and Median", countMedian);
				pieModel.setValue("Lies between Median and Quartile quartileThree", countQuartileThree);
				pieModel.setValue("Greater than quartileThree", greaterThanQuartileThree);
				dataset.addValue(countQuartileOne," Quartile quartileOne ", "Category 1");
				dataset.addValue(countMedian," Lies between Quartile quartileOne and Median ", "Category 2");
				dataset.addValue(countQuartileThree,"Lies between Median and Quartile quartileThree","Category 3");
				dataset.addValue(greaterThanQuartileThree," Greater than quartileThree ", "Category 4");
				return true;
			}
			else
			{
				errorMessage=dbAccess.getMessage();
				return false;
			}
		} catch(Exception e) {
			errorMessage=e.getMessage();
			return false;
		}
	}

	public boolean generateTimeSeriesPlot(String schema,String tableSelected,String columnSelected)
	{
		try
		{
			String sqlQuery="Select  "  + columnSelected +" " +  "from" + " "+ schema +"."+
			tableSelected;
			resultSet=dbAccess.selectQueryProcessing(sqlQuery);
		    resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
			String datatype=resultSetMetaData.getColumnTypeName(1);
			TimeSeries series = new TimeSeries("Random Data");
			Day current = new Day(19, MonthConstants.APRIL, 2017);
			if(resultSet!=null)
			{
				while(resultSet.next())
				{
					switch(datatype.toLowerCase())
					{
						case "int":
							series.add(current, resultSet.getInt(columnSelected));
							break;
						case "smallint":
							series.add(current, resultSet.getInt(1));
							break;
						case "float":
							series.add(current, resultSet.getFloat(1));
							break;
						case "double":
							series.add(current, resultSet.getDouble(1));
							break;
						case "long":
							series.add(current, resultSet.getLong(1));
							break;
					}
					current = (Day) current.next();
				}
				data = new TimeSeriesCollection(series);
				return true;
			}
			else
			{
				errorMessage=dbAccess.getMessage();
				return false;
			}
		} catch(Exception e) 
		{
			errorMessage = e.getMessage();
			return false;
		}
	}

     JFreeChart chart = ChartFactory.createTimeSeriesChart(
         "Time Series Chart", "Date", "Rate",
         data, true, true, false);

	public DatabaseAccess getc() {
		return dbAccess;
	}

	public void setDbAccess(DatabaseAccess dbAccess) {
		this.dbAccess = dbAccess;
	}

	public DefaultPieDataset getPieModel() {
		return pieModel;
	}

	public void setPieModel(DefaultPieDataset pieModel) {
		this.pieModel = pieModel;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	public JFreeChart getPieChart() {
		return pieChart;
	}

	public void setPieChart(JFreeChart pieChart) {
		this.pieChart = pieChart;
	}
	
	public ResultSetMetaData getResultSetMetaData() {
		return resultSetMetaData;
	}

	public void setResultSetMetaData(ResultSetMetaData resultSetMetaData) {
		this.resultSetMetaData = resultSetMetaData;
	}

	public boolean isRenderErrorMessage() {
		return renderErrorMessage;
	}

	public void setRenderErrorMessage(boolean renderErrorMessage) {
		this.renderErrorMessage = renderErrorMessage;
	}

	public boolean isRenderPieChart() {
		return renderPieChart;
	}

	public void setRenderPieChart(boolean renderPieChart) {
		this.renderPieChart = renderPieChart;
	}
	
	public XYDataset getData() {
		return data;
	}

	public void setData(XYDataset data) {
		this.data = data;
	}
	 
	public DefaultCategoryDataset getDataset() {
			return dataset;
	}

	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataset = dataset;
	}

	public StatsManagedBean getStatsManagedBean() {
		return statsManagedBean;
	}

	public void setStatsManagedBean(StatsManagedBean statsManagedBean) {
		this.statsManagedBean = statsManagedBean;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public DatabaseBean getDbBean() {
		return dbBean;
	}

	public void setDbBean(DatabaseBean dbBean) {
		this.dbBean = dbBean;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public DatabaseAccess getDbAccess() {
		return dbAccess;
	}
}
