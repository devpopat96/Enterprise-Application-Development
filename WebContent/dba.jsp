<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" version="2.0">
	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:t="http://myfaces.apache.org/tomahawk">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login</title>
</head>
<body>
	<div align="center" style="border:2px solid DodgerBlue; background-color:LightGray">
		<h2 align="Center" style="border:2px solid Orange; background-color:LightGray">
		IDS 517 Database Login
		<br/>
		Fall 2018 g213
		<br/>
		Member 1 - Devansh Popat 
		<br/> 
		&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		Member 2 - Nishannthi Srinivasan
		</h2>
		
			<f:view>
				<h:form>
					<h:panelGrid columns="3">
						<h:outputText value="Username:" />
						<h:inputText id="username" value="#{dbBean.userName}" required="true" requiredMessage="Please enter the username" />
						<h:message for="username" style="color:red;" />
							
						<h:outputText value="Password:" />
						<h:inputSecret id="password" value="#{dbBean.password}" required="true" requiredMessage="Please enter the password" />
						<h:message for="password" style="color:red;"/>
							
						<h:outputText value="Port:" />
						<h:inputText id="port" value="#{dbBean.port}" required="true" requiredMessage="Please enter the port number" />
						<h:message for="port" style="color:red;" />
									
						<h:outputText value="Schema:" />
						<h:inputText id="schema" value="#{dbBean.schema}" required="true" requiredMessage="Please enter the schema" />
						<h:message for="schema" style="color:red;" />	
							
						<h:outputText value="Host:" />
						<h:selectOneMenu id="host" value="#{dbBean.host}" required="true" requiredMessage="Please choose host">
							<f:selectItem itemValue="131.193.209.68" itemLabel="server 68" />
							<f:selectItem itemValue="131.193.209.69" itemLabel="server 69" />
							<f:selectItem itemValue="localhost(127.0.0.1)" itemLabel="localhost" />
						</h:selectOneMenu>
						<h:message for="host" style="color:red;" />
							
						<h:outputText value="Database:" /> 						
						<h:selectOneMenu id="database" value="#{dbBean.dbms}" required="true" requiredMessage="Please choose database">
							<f:selectItem itemValue="mysql" itemLabel="My SQL" />
							<f:selectItem itemValue="db2" itemLabel="DB2" />
							<f:selectItem itemValue="oracle" itemLabel="Oracle" />
						</h:selectOneMenu>		
						<h:message for="database" style="color:red;" /> 
							
						<h:commandButton value="Login" action="#{methods.login}" styleClass="button" style="width:80px;height:40px" />
						<a href="http://131.193.209.68:8080/ids517f18/faces/index.jsp">help</a>
					</h:panelGrid>
					<br />
					<pre>
						<h:outputText value="#{dbAccess.message}" rendered="#{dbAccess.renderMessage}" style="color:red;" />
					</pre>
				</h:form>
			</f:view>
		</div>
</body>
</html>
</jsp:root>