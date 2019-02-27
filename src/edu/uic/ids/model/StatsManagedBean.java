package edu.uic.ids.model;

import java.io.Serializable;

//Import DbAccess class
import edu.uic.ids.action.DatabaseAccess;

public class StatsManagedBean implements Serializable,Cloneable 
{
	private static final long serialVersionUID = 1L;
	private double quartileOne ;
	private double quartileThree ;
	private double interquartileRange;
	private double range;
	private String columnSelected;
	private double minimumValue;
	private double maximumValue;
	private double mean;
	private double variance;
	private double standardDeviation ;
	private double median;
	private DatabaseBean dbBean;
	private DatabaseAccess dbAccess;
	public StatsManagedBean()
	{
		//Default Constructor
	}
	public StatsManagedBean(double quartileOne, double quartileThree, double interquartileRange, double range, String columnSelected, double minimumValue, double maximumValue, double mean, double variance, double standardDeviation,
			double median)
	{
		this.quartileOne = quartileOne;
		this.quartileThree = quartileThree;
		this.interquartileRange = interquartileRange;
		this.range = range;
		this.columnSelected = columnSelected;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.mean = mean;
		this.variance = variance;
		this.standardDeviation = standardDeviation;
		this.median = median;
		
	}
	public void setVariables(double quartileOne,double quartileThree,double median)
	{
		this.quartileOne = quartileOne;
		this.quartileThree = quartileThree;
		this.median = median;
		
	}
	
	public double getQuartileOne() {
		return quartileOne;
	}

	public void setQuartileOne(double quartileOne) {
		this.quartileOne = quartileOne;
	}

	public double getQuartileThree() {
		return quartileThree;
	}

	public void setQuartileThree(double quartileThree) {
		this.quartileThree = quartileThree;
	}

	public double getInterquartileRange() {
		return interquartileRange;
	}

	public void setInterquartileRange(double interquartileRange) {
		this.interquartileRange = interquartileRange;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}
	public String getColumnSelected() {
		return columnSelected;
	}

	public void setColumnSelected(String columnSelected) {
		this.columnSelected = columnSelected;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getMinimumValue() {
		return minimumValue;
	}

	public void setMinimumValue(double minimumValue) {
		this.minimumValue = minimumValue;
	}

	public double getMaximumValue() {
		return maximumValue;
	}

	public void setMaximumValue(double maximumValue) {
		this.maximumValue = maximumValue;
	}

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getVariance() {
		return variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public DatabaseBean getDbBean() {
		return dbBean;
	}

	public void setDbBean(DatabaseBean dbBean) {
		this.dbBean = dbBean;
	}

	public DatabaseAccess getDbAccess() {
		return dbAccess;
	}

	public void setDbAccess(DatabaseAccess dbAccess) {
		this.dbAccess = dbAccess;
	}

	
}
