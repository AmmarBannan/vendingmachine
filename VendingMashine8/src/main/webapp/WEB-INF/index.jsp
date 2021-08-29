
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome Page</title>
    <style>
	    body{
			background-color:red;
		}
		a{
				text-decoration:none;
			}
			h1 {
			        color: white;
			        
			}
		a{
				text-decoration:none;
			}
		.wrapper{
			margin:10em;
			padding:2em;
			background-color:#dbdbea;
			display:grid;
			grid-template-columns:repeat(5,1fr);
			grid-column-gap:1em;
			grid-row-gap:1em;
		}
		.wrapper >div{
		
		text-align: center;
		background:#f9f5f5;
		margin:1em;
		padding:2em;
		}
		.wrapper >div:nth-child(odd){
		background:#eee;
		}
		a button{
			
			color:red;
		}
    </style>
</head>
<meta charset="ISO-8859-1">
<title>Vendor</title>
</head>
<body>
	<h1><a href="/">🔓</a>&nbsp &nbsp You are in!!</h1>
    <div class="wrapper">
    <c:set var="count" value="0" scope="page" />
    <c:forEach items="${itemss }" var="pos">
   
    	<div>
    	<p>${count+1}</p>
    		<c:if test="${pos.name!=null}">
    		
			    <form:form action="/edit/${count}" method="POST" modelAttribute="item">
				    <p>
				        <form:label path="name">name</form:label>
				        <form:errors path="name"/>
				        <form:input path="name" value="${pos.name}" class="input"/>
				    </p>
				    <p>
				        <form:label path="cost">Price in $</form:label>
				        <form:errors path="cost"/>
				        <form:input path="cost" value="${pos.cost}"  /> 
				    </p>
				    <p>
				        <form:label path="quantity">quantity</form:label>
				        <form:errors path="quantity"/>
				        <form:input path="quantity" value="${pos.quantity}" type="number"/>
				    </p>
					 <input type="hidden" name="position" value=${pos }>
				    
				    <input type="submit" value="Change"/>   
				</form:form>
				<form:form action="/delete/${pos.id}">
					<input type="submit" value="Remove"/>  
				</form:form>
			</c:if>
			
			<c:if test="${pos==null}">
			    <form:form action="/adding/${count}" method="POST" modelAttribute="item">
				    <p>
				        <form:errors path="name"/>
				        <form:errors path="name"/>
				        <form:input path="name" class="input"/>
				    </p>
				    <p>
				        <form:label path="cost">Price in $</form:label>
				        <form:errors path="cost"/>
				        <form:input path="cost"/>
				    </p>
				    <p>
				        <form:label path="quantity">quantity</form:label>
				        <form:errors path="quantity"/>
				        <form:input path="quantity" type="number"/>
				    </p>
					 <input type="hidden" name="position" value=${pos}>
				    <input type="submit" value="add"/>
				</form:form>
			</c:if>
		</div>
		<c:set var="count" value="${count + 1}" scope="page"/>
	</c:forEach>
	
	</div>
</body>
</html>