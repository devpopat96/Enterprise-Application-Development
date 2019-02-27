<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" version="2.0">
	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>logout</title>
		<link rel="stylesheet" href="CSS/style.css" />
		</head>
		<body>
		<div  style="border:2px solid DodgerBlue; background-color:LightGray">
			<f:view>
				<center>
					<h1>Project and Database Logout</h1>
					<hr/>
					<div class="operationsMain">
						<h:form>
							<h:commandButton value="Logout" action="#{methods.logout}" styleClass="button" />
							<br/>
							<a href="dbOptions.jsp">Cick here to return back to application</a>
							<br />
						</h:form>
					</div>
				</center>
			</f:view>
			</div>
		</body>
	</html>
</jsp:root>