package edu.uic.ids.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

//import edu.uic.ids.model.AccessLogBean;
import edu.uic.ids.model.DatabaseBean;
import edu.uic.ids.model.MathBean;
import edu.uic.ids.model.StatsBean;
import edu.uic.ids.model.StatsManagedBean;

@ManagedBean(name="methods")
@SessionScoped
public class Methods {
private DatabaseAccess dbAccess;
private DatabaseBean dbBean; 
private DatabaseOperations databaseOperations;
private StatsBean statsBean;
private StatsManagedBean statsManagedBean;
private Reports reports;
private Upload upload;
private MathBean mathManagedBean;
private Export export;
private String message;
private List<String> schemaList;

public Methods()
{
	schemaList=new ArrayList<String>();
}
public String login()
{
	String status = dbAccess.dbConnect();
	databaseOperations = new DatabaseOperations();
	upload = new Upload();
	statsBean = new StatsBean();
	statsManagedBean = new StatsManagedBean();
	reports = new Reports();
	mathManagedBean = new MathBean();
	export = new Export();
	
	databaseOperations.setDbAccess(dbAccess);
	databaseOperations.setDbBean(dbBean);
	upload.setDbBean(dbBean);
	upload.setDbAccess(dbAccess);
	
	
	statsBean.setDbBean(dbBean);
	statsBean.setDbAccess(dbAccess);
	
	statsManagedBean.setDbBean(dbBean);
	statsManagedBean.setDbAccess(dbAccess);
	
	reports.setDbBean(dbBean);
	reports.setDbAccess(dbAccess);
	
	mathManagedBean.setDbBean(dbBean);
	mathManagedBean.setDbAccess(dbAccess);
	
	export.setDbBean(dbBean);
	export.setDbAccess(dbAccess);
	
	if(status.equals("SUCCESS"))
	{
		HttpSession session = Util.getSession();
		 session.setAttribute("username", dbBean.getUserName());
		dbAccess.setMessage("");
		dbAccess.setRenderMessage(false);
		return "SUCCESS";
	}
	else {
		FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Login not valid",
                "Please try again"));
		return "FAIL";
	}
}

public String logout() 
{
	try {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		return "LOGOUT";
	} catch (Exception e) {
		message = e.getMessage();
		databaseOperations.setMessage(message);
		databaseOperations.setRenderMessage(true);
		databaseOperations.setMessage(message);
		return "FAIL";
	}
}

public DatabaseAccess getDbAccess() {
	return dbAccess;
}

public void setDbAccess(DatabaseAccess dbAccess) {
	this.dbAccess = dbAccess;
}


public DatabaseOperations getDatabaseOperations() {
	return databaseOperations;
}


public String getMessage() {
	return message;
}


public void setDatabaseOperations(DatabaseOperations databaseOperations) {
	this.databaseOperations = databaseOperations;
}


public void setMessage(String message) {
	this.message = message;
}

public List<String> getSchemaList() {
	return schemaList;
}


public void setSchemaList(List<String> schemaList) {
	this.schemaList = schemaList;
}
public DatabaseBean getDbBean() {
	return dbBean;
}
public void setDbBean(DatabaseBean dbBean) {
	this.dbBean = dbBean;
}
public StatsBean getStatsBean() {
	return statsBean;
}
public void setStatsBean(StatsBean statsBean) {
	this.statsBean = statsBean;
}

public StatsManagedBean getStatsManagedBean() {
	return statsManagedBean;
}
public void setStatsManagedBean(StatsManagedBean statsManagedBean) {
	this.statsManagedBean = statsManagedBean;
}
public Reports getReports() {
	return reports;
}
public void setReports(Reports reports) {
	this.reports = reports;
}
public Upload getUpload() {
	return upload;
}
public void setUpload(Upload upload) {
	this.upload = upload;
}
public MathBean getMathManagedBean() {
	return mathManagedBean;
}
public void setMathManagedBean(MathBean mathManagedBean) {
	this.mathManagedBean = mathManagedBean;
}

}
