package edu.uic.ids.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//Imports
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

//TEst
//import edu.uic.ids.model.AccessLogBean;
//Import DatabaseAccessInfoBean
import edu.uic.ids.model.DatabaseBean;

//Managed bean
@ManagedBean(name="export")
@SessionScoped
public class Export 
{

	private DatabaseBean dbBean;
	private String fileName;
	private String message;
	private boolean renderMessage;
	private DatabaseAccess dbAccess;
	private String selectedTable;
	private ResultSet resultSet;
	private boolean renderTableName;
	private Result result;
    private List<String> listTable;

	public Export()
	{
		
		renderTableName = false;
		renderMessage = false;
		listTable = new ArrayList<String>();
	}
	
	public String getTables()
	{
		
		try 
		{
			resetMessage();
			listTable.clear();
			String tablenames;
			ResultSet[] rs =  dbAccess.fetchTables();
			
			if(rs!=null)
			{
			
				while(rs[0].next())
				{
					tablenames = rs[0].getString("TABLE_NAME");
					listTable.add(tablenames);
				}
				while(rs[1].next())
				{
					tablenames = rs[1].getString("TABLE_NAME");
					listTable.add(tablenames);
				}
				renderListTable();
				return "SUCCESS";
			}
		
			else
			{
				message = dbAccess.getMessage();
				renderMessage = true;
				return "FAIL";
			}
		} 
	
		catch (Exception error) 
		{
			message = error.getMessage();
			renderMessage = true;
			return "FAIL";
		}
	}
	public void renderListTable()
	{
		renderMessage=false;
	
		if(listTable.isEmpty())
		{
			message = "There are no tables in this schema.";
			renderMessage = true;
			renderTableName = false;
		}
	
		else
			renderTableName = true;
	}

	public String exportCSV()
	{
		try 
		{
			resetMessage();
		
			if(listTable.isEmpty())
			{
				message = "No tables in this ScheMA";
				renderMessage = true;
				return "FAIL";
			}
		
			if(selectedTable.isEmpty())
			{
				message = "Please select table to export data.";
				renderMessage=true;
				return "FAIL";
			}
		
			else
			{
				FacesContext facesCont = FacesContext.getCurrentInstance();
				ExternalContext externalCont = facesCont.getExternalContext();
				FileOutputStream fileOutputStream = null;
				String path = facesCont.getExternalContext().getRealPath("/temp");
				File dir = new File(path);
				
				if(!dir.exists())
					new File(path).mkdirs();
				externalCont.setResponseCharacterEncoding("UTF-8");
				ResultSet rs[]=dbAccess.fetchTables();
				String fileNameBase = "Statistical Summary of"+rs+ ".csv";
				String fileName = path + "/" + "_" + fileNameBase;
				File f = new File(fileName);
				resultSet = null;
				
				String sqlQuery = "select * from " + selectedTable ;
				resultSet=dbAccess.selectQueryProcessing(sqlQuery);
			
				if(resultSet!=null)
				{
					result = ResultSupport.toResult(resultSet);
					Object [][] sData = result.getRowsByIndex();
					String columnNames [] = result.getColumnNames();
					StringBuffer stringBuff = new StringBuffer();
				
					try 
					{
						fileOutputStream = new FileOutputStream(fileName);
						for(int i=0; i<columnNames.length; i++) 
						{
							stringBuff.append(columnNames[i].toString() + ",");
						}
						stringBuff.append("\n");
						fileOutputStream.write(stringBuff.toString().getBytes());
					
						for(int i = 0; i < sData.length; i++) 
						{
							stringBuff = new StringBuffer();
						
							for(int j=0; j<sData[0].length; j++)
							{
								
								if(sData[i][j]==null)
								{
									String secondValue="0";
									secondValue=secondValue.replaceAll("[^A-Za-z0-9.]", " . ");
									if(secondValue.isEmpty())
									{
										secondValue="0";
									}
									stringBuff.append(secondValue + ",");
								}
						
								else
								{
									String value =sData[i][j].toString();
									if(value.contains(","))
									{
										int index=value.indexOf(",");
										String newValue=value.substring(0, index-1);
										value=newValue+value.substring(index+1,value.length());
									}
									value=value.replaceAll("[^A-Za-z0-9,.]", " ");
									if(value.isEmpty())
									{
										value="0";
									}
									stringBuff.append(value + ",");
								}
							}
							stringBuff.append("\n");
							fileOutputStream.write(stringBuff.toString().getBytes());
						}
						fileOutputStream.flush();
						fileOutputStream.close();
					} 
					
					catch (FileNotFoundException error) 
					{
						message = error.getMessage();
						renderMessage = true; 
					} 
					
					catch (IOException io) 
					{
						message = io.getMessage();
						renderMessage = true;
					}
					String mimeType = externalCont.getMimeType(fileName);
					FileInputStream input = null;
					byte b;
					externalCont.responseReset();
					externalCont.setResponseContentType(mimeType);
					externalCont.setResponseContentLength((int) f.length());
					externalCont.setResponseHeader("Content-Disposition",
							"attachment; filename=\"" + fileNameBase + "\"");
					
					try 
					{
						input = new FileInputStream(f);
						OutputStream output = externalCont.getResponseOutputStream();
						
						while(true) 
						{
							b = (byte) input.read();
							if(b < 0)
								break;
							output.write(b);

						}
					} 
					
					catch (IOException error) 
					{
						message=error.getMessage();
						renderMessage=true;
					}
					
					finally
					{
					
					try 
						{ 
							input.close(); 
						} 
						
						catch (IOException error) 
						{
							message=error.getMessage();
							renderMessage=true;
						}
					}
					facesCont.responseComplete();
				} 
				
				else
				{
					message=dbAccess.getMessage();
					renderMessage=true;
				}

			}

			return "SUCCESS";
		} 
		
		catch (Exception error) 
		{
			message = error.getMessage();
			renderMessage = true;
			return "FAIL";
		}
	}
	public void importExportSchemaChange()
    {
		renderMessage=false;
		renderTableName=false;
		getTables();
    }
	
	public void resetMessage()
	{
		renderMessage=false;
	}
	
	public String getSelectedTable() {
		return selectedTable;
	}

	public void setSelectedTable(String selectedTable) {
		this.selectedTable = selectedTable;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public boolean isRenderTableName() {
		return renderTableName;
	}

	public void setRenderTableName(boolean renderTableName) {
		this.renderTableName = renderTableName;
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

	public List<String> getListTable() {
		return listTable;
	}

	public void setListTable(List<String> listTable) {
		this.listTable = listTable;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRenderMessage() {
		return renderMessage;
	}

	public void setRenderMessage(boolean renderMessage) {
		this.renderMessage = renderMessage;
	}

	public DatabaseAccess getDbAccess() {
		return dbAccess;
	}

	public void setDbAccess(DatabaseAccess dbAccess) {
		this.dbAccess = dbAccess;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
}
