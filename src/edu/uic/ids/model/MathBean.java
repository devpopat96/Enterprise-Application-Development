package edu.uic.ids.model;

import edu.uic.ids.action.DatabaseAccess;

public class MathBean {
	private DatabaseBean dbBean;
	private DatabaseAccess dbAccess;
	private String message;
	private String regEquation;
	private double slopeStdErr;
	private double tStatPred;
	private double pValuePred;
	private double fValue;
	private double pValue;
	private double stdErrorM;
	private double rSquare;
	private double rSquareAdj;
	private double intercept;
	private double interceptStdErr;
	private double tStatistic;
	private double interceptPValue;
	private double slope;
	private double predictorDegreesFreedom;
	private double residualErrorDegreesFreedom;
	private double totalDegreesFreedom;
	private double regSumSquares;
	private double sumSquaredErr;
	private double totalSumSquares;
	private double meanSquare;
	private double meanSquareErr;
	
	public boolean setRegAnalysisVar(String regEquation, double intercept, double interceptStdErr, double tStatistic,
			double interceptPValue, double slope, double slopeStdErr, double tStatPred, double pValuePred, double stdErrorM,
			double rSquare, double rSquareAdj, double predictorDegreesFreedom,double residualErrorDegreesFreedom, double totalDegreesFreedom, double regSumSquares,
			double sumSquaredErr, double totalSumSquares, double meanSquare, double meanSquareErr,
			double fValue, double pValue)
	{
		try {
			this.slope = slope;
			this.slopeStdErr = slopeStdErr;
			this.tStatPred = tStatPred;
			this.pValuePred = pValuePred;
			this.fValue = fValue;
			this.pValue = pValue;
			this.stdErrorM = stdErrorM;
			this.setrSquare(rSquare);
			this.setrSquareAdj(rSquareAdj);
			this.predictorDegreesFreedom = predictorDegreesFreedom;
			this.residualErrorDegreesFreedom = residualErrorDegreesFreedom;
			this.totalDegreesFreedom = totalDegreesFreedom;
			this.regSumSquares = regSumSquares;
			this.sumSquaredErr = sumSquaredErr;
			this.totalSumSquares = totalSumSquares;
			this.meanSquare = meanSquare;
			this.meanSquareErr = meanSquareErr;
			this.regEquation = regEquation;
			this.intercept = intercept;
			this.interceptStdErr = interceptStdErr;
			this.tStatistic = tStatistic;
			this.interceptPValue = interceptPValue;
			return true;
		} 
		catch(Exception error) 
		{
			message = error.getMessage();
			return false;
		}
	}
	public double getInterceptStdErr() {
		return interceptStdErr;
	}

	public void setInterceptStdErr(double interceptStdErr) {
		this.interceptStdErr = interceptStdErr;
	}

	public double gettStatistic() {
		return tStatistic;
	}

	public void settStatistic(double tStatistic) {
		this.tStatistic = tStatistic;
	}

	public double getInterceptPValue() {
		return interceptPValue;
	}

	public void setInterceptPValue(double interceptPValue) {
		this.interceptPValue = interceptPValue;
	}

	public double getSlope() {
		return slope;
	}

	public void setSlope(double slope) {
		this.slope = slope;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRegEquation() {
		return regEquation;
	}

	public void setRegressionEquation(String regEquation) {
		this.regEquation = regEquation;
	}

	public double getIntercept() {
		return intercept;
	}

	public void setIntercept(double intercept) {
		this.intercept = intercept;
	}

	public double getPredictorDegreesFreedom() {
		return predictorDegreesFreedom;
	}

	public void setPredictorDegreesFreedom(double predictorDegreesFreedom) {
		this.predictorDegreesFreedom = predictorDegreesFreedom;
	}

	public double getResidualErrorDegreesFreedom() {
		return residualErrorDegreesFreedom;
	}

	public void setResidualErrorDegreesFreedom(double residualErrorDegreesFreedom) {
		this.residualErrorDegreesFreedom = residualErrorDegreesFreedom;
	}

	public double getTotalDegreesFreedom() {
		return totalDegreesFreedom;
	}

	public void setTotalDegreesFreedom(double totalDegreesFreedom) {
		this.totalDegreesFreedom = totalDegreesFreedom;
	}
	public double getpValuePred() {
		return pValuePred;
	}

	public void setpValuePred(double pValuePred) {
		this.pValuePred = pValuePred;
	}

	public double getStdErrorM() {
		return stdErrorM;
	}

	public void setStdErrorM(double stdErrorM) {
		this.stdErrorM = stdErrorM;
	}


	public double getRegSumSquares() {
		return regSumSquares;
	}

	public void setRegSumSquares(double regSumSquares) {
		this.regSumSquares = regSumSquares;
	}

	public double getSumSquaredErr() {
		return sumSquaredErr;
	}

	public void setSumSquaredErr(double sumSquaredErr) {
		this.sumSquaredErr = sumSquaredErr;
	}

	public double getTotalSumSquares() {
		return totalSumSquares;
	}

	public void setTotalSumSquares(double totalSumSquares) {
		this.totalSumSquares = totalSumSquares;
	}

	public double getMeanSquare() {
		return meanSquare;
	}

	public void setMeanSquare(double meanSquare) {
		this.meanSquare = meanSquare;
	}

	public double getMeanSquareErr() {
		return meanSquareErr;
	}

	public void setMeanSquareErr(double meanSquareErr) {
		this.meanSquareErr = meanSquareErr;
	}

	public double getfValue() {
		return fValue;
	}

	public void setfValue(double fValue) {
		this.fValue = fValue;
	}

	public double getpValue() {
		return pValue;
	}

	public void setpValue(double pValue) {
		this.pValue = pValue;
	}

	public double getSlopeStdErr() {
		return slopeStdErr;
	}

	public void setSlopeStdErr(double slopeStdErr) {
		this.slopeStdErr = slopeStdErr;
	}

	public double gettStatPred() {
		return tStatPred;
	}

	public void settStatPred(double tStatPred) {
		this.tStatPred = tStatPred;
	}

	public double getrSquare() {
		return rSquare;
	}

	public void setrSquare(double rSquare) {
		this.rSquare = rSquare;
	}

	public double getrSquareAdj() {
		return rSquareAdj;
	}

	public void setrSquareAdj(double rSquareAdj) {
		this.rSquareAdj = rSquareAdj;
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

	public void setRegEquation(String regEquation) {
		this.regEquation = regEquation;
	}

}