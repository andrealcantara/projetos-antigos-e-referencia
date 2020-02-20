<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/imgs/favicon.png">
<title>TweetMe</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/bootstrap-theme.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/documentation.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/sunburst.css">
<script src="${pageContext.request.contextPath}/scripts/bootstrap.js"></script>
<script
	src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/style.css" />
</head>

<body>
	<f:view>

		<!-- Page header -->
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"><h:outputText
							value="#{msg.tweetme}" /></a>
				</div>
				<div class="navbar-collapse collapse">
					<h:form styleClass="navbar-form navbar-right">
						<div class="form-group">
							<h:inputText value="#{loginBean.username}"
								styleClass="form-control" title="#{msg.username}">
								<f:attribute name="placeholder" value="#{msg.username}" />
							</h:inputText>
						</div>
						<div class="form-group">
							<h:inputSecret value="#{loginBean.senha}"
								styleClass="form-control" title="#{msg.password}">
								<f:attribute name="placeholder" value="#{msg.password}" />
							</h:inputSecret>
						</div>
						<h:commandButton styleClass="btn btn-default"
							action="#{loginBean.signin}" value="#{msg.sign_in}" />
					</h:form>
				</div>
				<!--/.navbar-collapse -->
			</div>
		</div>

		<!-- 		<div class="content"> -->
		<div id="adv" class="jumbotron height-adv width-adv pos-adv">
			<h1>
				<h:outputText value="Tweet me!" />
			</h1>
			<p>
				<h:outputText value="#{msg.description_tweetme}" />
			</p>
		</div>

		<div id="signup"
			class="jumbotron pos-signup width-signup height-signup">

			<h3 class="margin-t-0">
				<h:outputText value="#{msg.sign_up}" />
			</h3>
			<h:form styleClass="form-horizontal">
				<div class="form-group margin-t-b-0">
					<h4>
						<h:outputLabel styleClass="margin-t-b-0" value="#{msg.full_name}" />
					</h4>
					<h:inputText value="#{signupBean.name}"
						styleClass="form-control margin-t-b-0" id="inputName" />
				</div>
				<div class="form-group margin-t-b-0">
					<h4>
						<h:outputLabel styleClass="margin-t-b-0" value="#{msg.email}" />
					</h4>
					<h:inputText value="#{signupBean.email}"
						styleClass="form-control margin-t-b-0" id="inputEmail" />
				</div>
				<div class="form-group margin-t-b-0">
					<h4>
						<h:outputLabel styleClass="margin-t-b-0" value="#{msg.password}" />
					</h4>
					<h:inputSecret value="#{signupBean.password}"
						styleClass="form-control margin-t-b-0" id="inputPassword" />
				</div>
				<div class="form-group">
					<h:commandButton styleClass="btn btn-default"
						action="#{signupBean.emailValidate}" value="#{msg.sign_up}" />
				</div>
			</h:form>
		</div>

		<!-- 		</div> -->


		<div id="footer">
			<div class="container">
				<p class="muted credit">
					Web III Project - TweetMe courtesy <a
						href="https://github.com/fraudlucas">Marcus Lucas</a> and <a
						href="https://github.com/barbaracgoncalves">Barbara Gonçalves</a>.
				</p>
			</div>
		</div>


	</f:view>

	<!-- Bootstrap core JavaScript
    ================================================== 
	Placed at the end of the document so the pages load faster -->
	<script
		src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/bootstrap.js"></script>
</body>

</html>