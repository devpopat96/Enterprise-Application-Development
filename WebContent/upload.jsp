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
<title>dataImport</title>
</head>
<body>
<div align="center" style="border:2px solid DodgerBlue; background-color:LightGray">
	<f:view>
		<center>
			<h3>Data Upload/Import Menu</h3>
			<hr />
			<div class="operationsMain">
				<a href="dbOptions.jsp">Main Menu</a> &#160;&#160; 
				<a href="confirm.jsp">Logout</a><br />
				<hr/>
				<h:form enctype="multipart/form-data">
					<pre>
						<h:outputText value="#{dbAccess.message}" rendered="#{dbAccess.renderMessage}" style="color:red;" />
					</pre>
					<h:messages style="position:relative;align:center;color:red;word-wrap:break-word;"></h:messages>
					<br/>
					<h:panelGrid columns="2" columnClasses="firstColumn,secondColumn">
						<h:outputLabel value="*File :"/>
						<t:inputFileUpload id="fileUpload" label="File to upload" storage="default" value="#{upload.uploadedFile}" size="60" required="true" requiredMessage="File cannot be empty" />
						<br/>
						<h:outputLabel value=" " />
					</h:panelGrid>
					<h:commandButton styleClass="button" id="upload"
						type="submit" value="Upload File" action="#{upload.processFile}"></h:commandButton>
					</h:form>
				<h:form>
					<h:panelGrid columns="2" rendered="#{upload.fileImport}">
						<h:outputLabel value="Number of records:"
							style="font-weight: bold;" />
						<h:outputText value="#{upload.totalNumberOfRows }" />
						<h:outputLabel value="fileLabel:" style="font-weight: bold;" />
						<h:outputText value="#{upload.fileLabel }" />
						<h:outputLabel value="fileName:" style="font-weight: bold;" />
						<h:outputText value="#{upload.fileName }" />
						<h:outputLabel value="fileSize:" style="font-weight: bold;" />
						<h:outputText value="#{upload.fileSize }" />
						<h:outputLabel value="fileContentType:" style="font-weight: bold;" />
						<h:outputText value="#{upload.fileContentType }" />
						<h:outputLabel value="tempFilePath:" style="font-weight: bold;" />
						<h:outputText value="#{upload.filePath }" />
						<h:outputLabel value="tempFileName:" style="font-weight: bold;" />
						<h:outputText value="#{upload.tempFileName }" />
						<h:outputLabel value="facesContext:" style="font-weight: bold;" />
						<h:outputText value="#{upload.facesContext }" />
					</h:panelGrid>
					<br />
					<h:outputText rendered="#{upload.fileImportError }"
						value="#{messageBean.errorMessage }" />
						
						<h:panelGrid  rendered="#{statsBean.renderTablename}">
							<t:dataTable value="#{statsBean.tableList}" var="row" border="1" rendered="#{statsBean.renderTablename}">
							<h:column>
									<f:facet name="header">
										<h:outputText value="Tables" />
									</f:facet>
									<h:outputText value="#{row}" />
								</h:column>
							</t:dataTable>
							<t:dataTable value="#{statsBean.columnsList}" var="row" border="1">
							<h:column>
									<f:facet name="header">
										<h:outputText value="Columns" />
									</f:facet>
									<h:outputText value="#{row}" />
								</h:column>
							</t:dataTable>
							<t:dataTable value="#{statsBean.statisticList}"
								var="rowNumber" rendered="#{statsBean.renderTabledata}"
								border="1" cellspacing="0" cellpadding="1"
								headerClass="headerWidth">
								<h:column>
									<f:facet name="header">
										<h:outputText value="Column Selected" />
									</f:facet>
									<h:outputText value="#{rowNumber.columnSelected}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Minimum Value" />
									</f:facet>
									<h:outputText value="#{rowNumber.minimumValue}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Maximum Value" />
									</f:facet>
									<h:outputText value="#{rowNumber.maximumValue}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Mean" />
									</f:facet>
									<h:outputText value="#{rowNumber.mean}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Variance" />
									</f:facet>
									<h:outputText value="#{rowNumber.variance}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Standard Deviation" />
									</f:facet>
									<h:outputText value="#{rowNumber.standardDeviation}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="quartileOne" />
									</f:facet>
									<h:outputText value="#{rowNumber.quartileOne}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="quartileThree" />
									</f:facet>
									<h:outputText value="#{rowNumber.quartileThree}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Range" />
									</f:facet>
									<h:outputText value="#{rowNumber.range}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="interquartileRange" />
									</f:facet>
									<h:outputText value="#{rowNumber.interquartileRange}" />
								</h:column>
							</t:dataTable>
							<h:graphicImage value="#{statsBean.seriesPath}" width="500"
							height="400" rendered="#{statsBean.seriesChart}" />
							</h:panelGrid>
							
				</h:form>
			</div>
		</center>
	</f:view>
	</div>
</body>
	</html>
</jsp:root>
