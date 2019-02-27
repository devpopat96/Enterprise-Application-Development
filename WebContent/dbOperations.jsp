 <jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:t="http://myfaces.apache.org.tomahawk" version="2.0">
	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:t="http://myfaces.apache.org/tomahawk">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>databaseAccess</title>
</head>
<body>
<div align="center" style="border:2px solid DodgerBlue; background-color:LightGray">
	<f:view>
		<center>
			<h2>Database Access Menu</h2>
			<hr />
			<div class="operationsMain">
				<a href="dbOptions.jsp">Main Menu</a> &#160;&#160; 
				<a href="confirm.jsp">Logout</a><br />
				<hr />
				<h:form>
					<br />
					<br />
					<div class="left-div">
						<h:panelGrid columns="5">
							<h:commandButton value="TableList" action="#{databaseOperations.fetchTables}" styleClass="button" />
							<h:commandButton value="ColumnList" action="#{databaseOperations.fetchColumnNames}" disabled="#{databaseOperations.disableColumnList}" styleClass="button" />
							<h:commandButton value="DisplayTable" action="#{databaseOperations.fetchTableData}" disabled="#{databaseOperations.disableDisplayTable})" styleClass="button" />
							<h:commandButton value="DisplaySelectedColumns" action="#{databaseOperations.fetchColumnData}" disabled="#{databaseOperations.disableColumnData}" styleClass="button" />
							<h:commandButton value="ProcessSQLquery" action="#{databaseOperations.queryProcessing}" styleClass="button" />
						</h:panelGrid>
					</div>
					<div class="right-div">
						<pre>
							<h:outputText value="#{databaseOperations.message}" rendered="#{databaseOperations.renderMessage}" style="color:red" />
						</pre>
						<panelGrid columns="4"> 
						<h:selectOneListbox
							id="selectOneCb" style="width:125px; height:75px"
							value="#{databaseOperations.tableSelected}"
							rendered="#{databaseOperations.renderTablename}" size="4">
							<f:selectItems value="#{databaseOperations.tableList}" />
						</h:selectOneListbox> 
						<h:outputText value=" " /> 
						<h:outputText value=" " /> 
						<h:outputText value=" " /> 
						<h:selectManyListbox id="selectcolumns"
							style="width:125px; height:75px"
							value="#{databaseOperations.columnSelected}"
							rendered="#{databaseOperations.columnRender}" size="5">
						<f:selectItems value="#{databaseOperations.columnsList}" />
						</h:selectManyListbox> 
						<h:outputText value=" " /> 
						<h:outputText value=" " /> 
						<h:outputText value=" " /> 
						<h:inputTextarea rows="6" cols="40" style="height:75px" value="#{databaseOperations.userQuery}" /> 
						</panelGrid>
						<br/><br/>	
						<hr/>
					</div>
					<div class="bottom">
						<div style="background-attachment: scroll; overflow: auto; height: 325px; background-repeat: repeat" align="center">
							<t:dataTable value="#{databaseOperations.result}" var="row" rendered="#{databaseOperations.renderTabledata}" border="2" width="900px">
								<t:columns var="col" value="#{databaseOperations.columnSelected}">
									<f:facet name="header">
										<t:outputText styleClass="outputHeader" value="#{col}" />
									</f:facet>
									<t:outputText styleClass="outputText" value="#{row[col]}" />
								</t:columns>
							</t:dataTable>
						</div>
					</div>
					<hr />
				</h:form>
			</div>
		</center>
	</f:view>
	</div>
</body>
</html>
</jsp:root>