<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:t="http://myfaces.apache.org.tomahawk" version="2.0">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml"
		xmlns:f="http://java.sun.com/jsf/core"
		xmlns:h="http://java.sun.com/jsf/html"
		xmlns:t="http://myfaces.apache.org/tomahawk">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>dataAnalysis</title>
</head>
<body>
<div align="center" style="border:2px solid DodgerBlue; background-color:LightGray">
	<f:view>
		<center>
			<h3 align="center">IDS 517 Data Analysis Menu</h3>
			<hr/>
			<div class="operationsMain">
				<a href="dbOptions.jsp">Main Menu</a> &#160;&#160; 
				<a href="confirm.jsp">Logout</a><br />
				<hr />
				<h:form>
					<div class="left-div">
						<h:panelGrid columns="8">
							<h:commandButton value="TableList" action="#{statsBean.getTables}" styleClass="button" />
							<h:commandButton value="ColumnList" action="#{statsBean.getColumnNames}" styleClass="button" disabled="#{statsBean.renderColumnListbutton}" />
							<h:commandButton value="Statistics" action="#{statsBean.generateReport}" styleClass="button" disabled="#{statsBean.renderReport}" />
							<h:commandButton value="Regression" action="#{statsBean.displayColumnsforRegression}" styleClass="button" />
							<h:commandButton value="Export" action="#{statsBean.export}"  disabled="#{statsBean.disableTabledata}" styleClass="button" />					
							<h:commandButton value="Regression Results" action="#{statsBean.generateRegressionReport}" styleClass="button" disabled="#{statsBean.renderRegressionButton}" />
							<h:commandButton value="Reset" action="#{statsBean.resetButton}" styleClass="button" />
							<h:commandButton value="Plot" action="#{statsBean.generateChart}"  disabled="#{statsBean.disableRegressionResult}"  styleClass="button" />
					</h:panelGrid>
					</div>
						<div class="right-div">
						<h:outputText value="#{statsBean.message}"
								rendered="#{statsBean.renderMessage}" style="color:red" />
					
						<panelGrid columns="4">
							<h:selectOneListbox id="selectOneCb"
								style="width:150px; height:100px"
								value="#{statsBean.tableSelected}"
								rendered="#{statsBean.renderTablename}" size="5">
								<f:selectItems value="#{statsBean.tableList}" />
							</h:selectOneListbox>
							<h:selectManyListbox id="selectcolumns"
								style="width:150px; height:100px"
								value="#{statsBean.columnSelected}"
								rendered="#{statsBean.columnRender}" size="5">
								<f:selectItems value="#{statsBean.columnsList}" />
							</h:selectManyListbox>
							<h:selectOneListbox id="predictor"
								value="#{statsBean.predictorValue}"
								rendered="#{statsBean.renderRegressionColumn}" size="5">
								<f:selectItem itemValue="0" itemLabel="Select Predictor Value" />
								<f:selectItems value="#{statsBean.numericData}" />
							</h:selectOneListbox>
							<h:selectOneListbox id="response"
								value="#{statsBean.responseValue}"
								rendered="#{statsBean.renderRegressionColumn}" size="5">
								<f:selectItem itemValue="0" itemLabel="Select Response Value" />
								<f:selectItems value="#{statsBean.numericData}" />
							</h:selectOneListbox>

						</panelGrid>
					</div>
					<br/>
					<div class="bottom">
						<div
							style="background-attachment: scroll; overflow: auto; background-repeat: repeat; background-color: MediumSeaGreen"
							align="center">
							<t:dataTable value="#{statsBean.statisticList}"
								var="rowNumber" rendered="#{statsBean.renderTabledata}"
								border="1" cellspacing="0" cellpadding="1"
								headerClass="headerWidth">
								<h:column>
									<f:facet name="header">
										<h:outputText value="Column Name" />
									</f:facet>
									<h:outputText value="#{rowNumber.columnSelected}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Min" />
									</f:facet>
									<h:outputText value="#{rowNumber.minimumValue}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Max" />
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
										<h:outputText value="First Quartile" />
									</f:facet>
									<h:outputText value="#{rowNumber.quartileOne}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Third Quartile" />
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
										<h:outputText value="IQR" />
									</f:facet>
									<h:outputText value="#{rowNumber.interquartileRange}" />
								</h:column>
							</t:dataTable>
						</div>
						<br />
					</div>
					<div class="bottom">
						<h:outputText value=" Regression Equation: "
							rendered="#{statsBean.renderRegressionResult}">
						</h:outputText>
						&#160;&#160;&#160;
						<h:outputText
							value="#{mathManagedBean.regEquation}"
							rendered="#{statsBean.renderRegressionResult}">
						</h:outputText>
						<hr/>
						<h:outputText value="Regression Model"
							rendered="#{statsBean.renderRegressionResult}"></h:outputText>
						<h:panelGrid columns="5"
							rendered="#{statsBean.renderRegressionResult}" border="2">
							<h:outputText value="Predictor" />
							<h:outputText value="Co-Efficentss" />
							<h:outputText value="Standardized Co-efficients" />
							<h:outputText value="T-Value" />
							<h:outputText value="P-Value" />
							<h:outputText value="Constant" />
							<h:outputText value="#{mathManagedBean.intercept}" />
							<h:outputText value="#{mathManagedBean.interceptStdErr}" />
							<h:outputText value="#{mathManagedBean.tStatistic }" />
							<h:outputText value="#{mathManagedBean.interceptPValue }" />
							<h:outputText value="#{statsBean.predictorValue}" />
							<h:outputText value="#{mathManagedBean.slope}" />
							<h:outputText value="#{mathManagedBean.slopeStdErr}" />
							<h:outputText value="#{mathManagedBean.tStatPred }" />
							<h:outputText value="#{mathManagedBean.pValuePred }" />
						</h:panelGrid>
						<hr/>
						<h:panelGrid columns="2"
							rendered="#{statsBean.renderRegressionResult}" border="2">
							<h:outputText value="Standard Error" />
							<h:outputText
								value="#{mathManagedBean.stdErrorM}" />
							<h:outputText value="R-Squared" />
							<h:outputText value="#{mathManagedBean.rSquare}" />
							<h:outputText
								value="Adjusted R-Squared" />
							<h:outputText
								value="#{mathManagedBean.rSquareAdj}" />
						</h:panelGrid>
						<br /> <br />
						<h:outputText value="ANOVA"
							rendered="#{statsBean.renderRegressionResult}" />
						<br />
						<h:panelGrid columns="6"
							rendered="#{statsBean.renderRegressionResult}" border="2">
							<h:outputText value="Source" />
							<h:outputText value="DF" />
							<h:outputText value="Adj SS" />
							<h:outputText value="Adj MS" />
							<h:outputText value="F-Value" />
							<h:outputText value="P-Value" />
							<h:outputText value="Regression" />
							<h:outputText
								value="#{mathManagedBean.predictorDegreesFreedom}" />
							<h:outputText
								value="#{mathManagedBean.regSumSquares}" />
							<h:outputText
								value="#{mathManagedBean.meanSquare }" />
							<h:outputText value="#{mathManagedBean.fValue }" />
							<h:outputText value="#{mathManagedBean.pValue}" />
							<h:outputText value="Error" />
							<h:outputText
								value="#{mathManagedBean.residualErrorDegreesFreedom}" />
							<h:outputText
								value="#{mathManagedBean.sumSquaredErr }" />
							<h:outputText
								value="#{mathManagedBean.meanSquareErr }" />
							<h:outputText value="" />
							<h:outputText value="" />
							<h:outputText value="Total" />
							<h:outputText value="#{mathManagedBean.totalDegreesFreedom}" />
							<h:outputText value="#{mathManagedBean.totalSumSquares}" />
							
						</h:panelGrid>
						
					</div>
					<div class="right-div2">
						
						<h:graphicImage value="#{statsBean.seriesPath}" width="500"
							height="400" rendered="#{statsBean.seriesChart}" />
						
						<br />
					</div>
				</h:form>
			</div>
		</center>
	</f:view>
	</div>
</body>
	</html>
</jsp:root>