package edu.uic.ids.action;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import java.sql.DatabaseMetaData;

import edu.uic.ids.model.DatabaseBean;
import edu.uic.ids.model.StatsBean;
 
@ManagedBean(name = "databaseOperations")
@SessionScoped
public class DatabaseOperations {
	private StatsBean statsBean;
	private boolean columnRender;
	private boolean columnDataRender;
	private ResultSet resultSet;
	private boolean renderTablename;
	private Result result;
	private boolean renderButton;
	private boolean renderTabledata;
	private String userQuery;
	private int rowsAffected;
	private int columnCount;
	private boolean renderSchema;
	private String message;
	private String tableSelected;
	private boolean renderMessage;
	private DatabaseAccess dbAccess;
	private DatabaseMetaData metaData;
	private ResultSetMetaData resultSetMetaData;
	private DatabaseBean dbBean;
	private List<String> schemaList;
	private List<String> tableList;
	private List<String> columnsList;
	private List<String> columnSelected;
	private List<String> columns = new ArrayList<String>();
	public  List<String> worldTables = new ArrayList<String>();
	private boolean disableTableList = false;
	private boolean disableColumnList;
	private boolean disableDisplayTable;
	private boolean disableColumnData;

	public DatabaseOperations() {
		columnRender = false;
		rowsAffected = 0;
		columnDataRender = false;
		renderTablename = false;
		renderTabledata = false;
		renderMessage = false;
		renderSchema = false;
		tableList = new ArrayList<String>();
		columnsList = new ArrayList<String>();
		columnSelected = new ArrayList<String>();
		schemaList = new ArrayList<String>();

		disableColumnList = false;
		disableDisplayTable = false;
		disableColumnData = false;

	}

	private String sourceColumn;

	public String getSourceColumn() {
		return sourceColumn;
	}

	public void setSourceColumn(String sourceColumn) {
		this.sourceColumn = sourceColumn;
	}

	public String getDestinationColumn() {
		return destinationColumn;
	}

	public void setDestinationColumn(String destinationColumn) {
		this.destinationColumn = destinationColumn;
	}

	private String destinationColumn;


	public void renderTableList() {
		renderMessage = false;
		if (tableList.isEmpty()) {
			message = "The selected schema has no tables to be dispayed";
			renderMessage = true;
			renderTabledata = false;
			renderMessage = true;
			renderTablename = false;
			userQuery = "";
		} else
			renderTablename = true;
		disableDisplayTable = false;
		disableColumnList = false;
	}

	public String fetchTables() {
		try {
			clear();
			tableList.clear();
			String tableNames;
			ResultSet[] rs  = dbAccess.fetchTables();
			if (rs != null) {
				while (rs[0].next()) {
					tableNames = rs[0].getString("TABLE_NAME");
					tableList.add(tableNames);
				}
				renderTableList();
				return "SUCCESS";
			} else {
				message = dbAccess.getMessage();
				renderMessage = true;
				return "FAIL";
			}
		} catch (Exception err) {
			err.printStackTrace();
			message = "An exception has occured. The details of the error are given below." + "\n" + err.getMessage();
			renderMessage = true;
			return "FAIL";
		}
	}

	public String fetchTableData() {
		try {
			clear();
			if (tableList.isEmpty()) {
				message = "No tables are available to display at the moment";
				renderMessage = true;
				return "FAIL";
			}
			if (tableSelected.isEmpty()) {
				message = "Please select a table and try again";
				renderMessage = true;
				return "FAIL";
			} else {
				String sqlQuery ="";
				if(worldTables.contains(tableSelected))
					sqlQuery = "select * from "+dbBean.getSchema()+"." + tableSelected;
				else
					sqlQuery = "select * from "+dbBean.getSchema()+"."+ tableSelected;
				resultSet = dbAccess.selectQueryProcessing(sqlQuery);
				userQuery = sqlQuery;
				if (resultSet != null) {
					buildMetaData();
					renderTabledata = true;
					return "SUCCESS";
				} else {
					message = dbAccess.getMessage();
					renderMessage = true;
					return "FAIL";
				}
			}
		} catch (Exception err) {
			message = err.getMessage();
			renderMessage = true;
			return "FAIL";
		}
	}

	private void buildMetaData() {
		try {
			if (resultSet != null) {
				columnSelected = new ArrayList<String>();
				resultSetMetaData = resultSet.getMetaData();
				result = ResultSupport.toResult(resultSet);
				columnCount = resultSetMetaData.getColumnCount();
				rowsAffected = result.getRowCount();
				String columnNameList[] = result.getColumnNames();
				for (int i = 0; i < columnCount; i++) {
					columnSelected.add(columnNameList[i]);
				}
				renderTabledata = true;
			} else {
				message = dbAccess.getMessage();
				renderMessage = true;
			}
		} catch (Exception err) {
			err.printStackTrace();
			message = err.getMessage();
			renderMessage = true;
		}
	}

	public String fetchColumnNames() {
		try {
			clear();
			if (tableList.isEmpty()) {
				message = "No tables available to display at the moment";
				renderMessage = true;
				return "FAIL";
			}
			if (tableSelected.isEmpty()) {
				message = "Please select a table and try again";
				renderMessage = true;
			} else {
				String sqlQuery = "";
				if(worldTables.contains(tableSelected))
					 sqlQuery = "select * from "+dbBean.getSchema()+"." + tableSelected;
				else
				 sqlQuery = "select * from "+dbBean.getSchema()+"." + tableSelected;
				ResultSet resultSet = dbAccess.fetchColumnNames(sqlQuery);
				userQuery = "";
				if (resultSet != null) {
					columnsList.clear();
					ResultSetMetaData resultSetmd = (ResultSetMetaData) resultSet.getMetaData();
					int columnCount = resultSetmd.getColumnCount();
					for (int i = 1; i <= columnCount; i++) {
						String name = resultSetmd.getColumnName(i);
						String datatype = resultSetmd.getColumnTypeName(i);
						columns.add(name);
						columnsList.add(name);
					}
					columnRender = true;
					disableColumnData = false;
				} else {
					message = dbAccess.getMessage();
					renderMessage = true;
				}
			}
			return "SUCCESS";
		} catch (Exception err) {
			err.printStackTrace();
			message = err.getMessage();
			renderMessage = true;
			return "FAIL";
		}
	}

	public String fetchColumnData() {
		clear();
		System.out.println(columnSelected);
		if (tableList.isEmpty()) {
			message = "No tables available to display at the moment";
			renderMessage = true;
			return "FAIL";
		}
		if (tableSelected.isEmpty()) {
			message = "A table and a column in the table should be selected to proceed";
			renderMessage = true;
			return "FAIL";
		}
		if (columnSelected.isEmpty()) {
			System.out.println(columnSelected);
			message = "Please select a column and try again";
			renderMessage = true;
			return "FAIL";
		}
		splitColumns();
		return "SUCCESS";
		/*String data = columnSelected.get(0);
		int index = data.indexOf(" ");
		if (index < 0) {
			message = "Please select a column and try again";
			renderMessage = true;
			return "FAIL";
		} else {
			splitColumns();
			return "SUCCESS";
		}*/
	}

	public void splitColumns() {
		try {
			if (tableSelected != null && columnSelected != null) {
				List<String> columnSeperated = new ArrayList<String>();
				for (int i = 0; i < columnSelected.size(); i++) {
					String data = columnSelected.get(i);
					/*int index = data.indexOf(" ");
					data = data.substring(0, index);*/
					columnSeperated.add(data);
					System.out.println(columnSeperated);
				}
				columnSelected = new ArrayList<String>();
				columnSelected = columnSeperated;
				columnSeperated = null;
				StringBuilder rString = new StringBuilder();
				for (String each : columnSelected) {
					rString.append(",").append(each);
				}
				String sqlQuery = rString.toString();
				int index = sqlQuery.indexOf(",");
				sqlQuery = sqlQuery.substring(index + 1, sqlQuery.length());
				if(worldTables.contains(tableSelected))
				sqlQuery = "select " + sqlQuery + " from "+dbBean.getSchema()+"." + tableSelected;
				else sqlQuery ="select " + sqlQuery + " from "+dbBean.getSchema()+"." + tableSelected;

				resultSet = dbAccess.selectQueryProcessing(sqlQuery);
				userQuery = sqlQuery;
				if (resultSet != null) {
					buildMetaData();
				} else {
					message = dbAccess.getMessage();
					renderMessage = true;
				}
			} else {
				message = "A table and a column in the table should be selected to proceed further";
			}
		} catch (Exception err) {
			message = "An exception has occured. The details of the error are given below." + "\n" + err.getMessage();
			renderMessage = true;
		}
	}

	public String queryProcessing() {
		try {
			clear();
			if (userQuery.isEmpty()) {
				message = "Please enter a query to proceed further";
				renderMessage = true;
				return "FAIL";
			} else {
				userQuery = userQuery.toLowerCase();
				int index = userQuery.indexOf(" ");
				if (index < 0) {
					message = "Please enter a valid query to proceed further";
					renderMessage = true;
					return "FAIL";
				}
				String subString = userQuery.substring(0, index);
				switch (subString) {
				case "select":
					clear();
					resultSet = dbAccess.selectQueryProcessing(userQuery);
					if (resultSet != null) {
						buildMetaData();
					} else {
						message = dbAccess.getMessage();
						renderMessage = true;
					}
					break;
				case "update":
					clear();
					rowsAffected = dbAccess.crudQueryProcessing(userQuery);
					if (rowsAffected < 0) {
						message = dbAccess.getMessage();
						renderMessage = true;
					} else {
						message = "Updated" + rowsAffected + " rows successfully";
						renderMessage = true;
					}
					break;
				case "drop":
					clear();
					rowsAffected = dbAccess.crudQueryProcessing(userQuery);
					if (rowsAffected < 0) {
						message = dbAccess.getMessage();
						renderMessage = true;
					} else {
						message = "Dropped the table successfully";
						renderMessage = true;
						fetchTables();
					}
					break;
				case "create":
					clear();
					rowsAffected = dbAccess.crudQueryProcessing(userQuery);
					if (rowsAffected < 0) {
						message = dbAccess.getMessage();
						renderMessage = true;
					} else {
						message = "The table was created successfully";
						renderMessage = true;
					}
					break;
				case "delete":
					clear();
					rowsAffected = dbAccess.crudQueryProcessing(userQuery);
					if (rowsAffected < 0) {
						message = dbAccess.getMessage();
						renderMessage = true;
					} else {
						message = "Deleted" + rowsAffected + " rows successfully";
						renderMessage = true;
					}
					break;
				case "insert":
					clear();
					rowsAffected = dbAccess.crudQueryProcessing(userQuery);
					if (rowsAffected < 0) {
						message = dbAccess.getMessage();
						renderMessage = true;
					} else {
						message = "Inserted" + rowsAffected + " rows successfully";
						renderMessage = true;
					}
					break;
				default:
					message = "Please enter a valid query to proceed further";
					renderMessage = true;
					break;
				}
			}
		} catch (Exception err) {
			message = "An exception has occured. The details of the error are given below." + "\n" + err.getMessage();
			renderMessage = true;
		}
		return "SUCCESS";
	}
	public void clear() {
		message = "";
		renderMessage = false;
		renderTabledata = false;
		rowsAffected = 0;
		columnCount = 0;
	}
	public List<String> getTableList() {
		return tableList;
	}
	public void setTableList(List<String> tableList) {
		this.tableList = tableList;
	}

	public String getTableSelected() {
		return tableSelected;
	}

	public void setTableSelected(String tableSelected) {
		this.tableSelected = tableSelected;
	}

	public List<String> getColumnSelected() {
		return columnSelected;
	}

	public void setColumnSelected(List<String> columnSelected) {
		this.columnSelected = columnSelected;
	}

	public boolean isColumnRender() {
		return columnRender;
	}

	public void setColumnRender(boolean columnRender) {
		this.columnRender = columnRender;
	}

	public boolean isColumnDataRender() {
		return columnDataRender;
	}

	public void setColumnDataRender(boolean columnDataRender) {
		this.columnDataRender = columnDataRender;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public boolean isRenderTablename() {
		return renderTablename;
	}

	public void setRenderTablename(boolean renderTablename) {
		this.renderTablename = renderTablename;
	}

	public ResultSetMetaData getResultSetMetaData() {
		return resultSetMetaData;
	}

	public void setResultSetMetaData(ResultSetMetaData resultSetMetaData) {
		this.resultSetMetaData = resultSetMetaData;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public boolean isRenderTable() {
		return renderTabledata;
	}

	public void setRenderTable(boolean renderTable) {
		this.renderTabledata = renderTable;
	}

	public List<String> getColumnsList() {
		return columnsList;
	}

	public void setColumnsList(List<String> columnsList) {
		this.columnsList = columnsList;
	}

	public String getUserQuery() {
		return userQuery;
	}

	public void setUserQuery(String userQuery) {
		this.userQuery = userQuery;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}

	public void setRowsAffected(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public DatabaseAccess getDbAccess() {
		return dbAccess;
	}

	public void setDbAccess(DatabaseAccess dbAccess) {
		this.dbAccess = dbAccess;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public List<String> getSchemaList() {
		return schemaList;
	}

	public void setSchemaList(List<String> schemaList) {
		this.schemaList = schemaList;
	}

	public DatabaseMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(DatabaseMetaData metaData) {
		this.metaData = metaData;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public boolean isRenderSchema() {
		return renderSchema;
	}

	public void setRenderSchema(boolean renderSchema) {
		this.renderSchema = renderSchema;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRenderTabledata() {
		return renderTabledata;
	}

	public void setRenderTabledata(boolean renderTabledata) {
		this.renderTabledata = renderTabledata;
	}

	public boolean isRenderMessage() {
		return renderMessage;
	}

	public void setRenderMessage(boolean renderMessage) {
		this.renderMessage = renderMessage;
	}

	public boolean isRenderButton() {
		return renderButton;
	}

	public void setRenderButton(boolean renderButton) {
		this.renderButton = renderButton;
	}

	public boolean isDisableTableList() {
		return disableTableList;
	}

	public void setDisableTableList(boolean disableTableList) {
		this.disableTableList = disableTableList;
	}

	public boolean isDisableColumnList() {
		return disableColumnList;
	}

	public void setDisableColumnList(boolean disableColumnList) {
		this.disableColumnList = disableColumnList;
	}

	public boolean isDisableDisplayTable() {
		return disableDisplayTable;
	}

	public void setDisableDisplayTable(boolean disableDisplayTable) {
		this.disableDisplayTable = disableDisplayTable;
	}

	public boolean isDisableColumnData() {
		return disableColumnData;
	}

	public void setDisableColumnData(boolean disableColumnData) {
		this.disableColumnData = disableColumnData;
	}

	public DatabaseBean getDbBean() {
		return dbBean;
	}

	public void setDbBean(DatabaseBean dbBean) {
		this.dbBean = dbBean;
	}

	

}