
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%! int test;
		float overall;
		int find;
	%>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
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
			.upper{
				margin:5em;
				padding:2em;
				background-color:white;
				display:grid;
				grid-template-columns: 16fr 5fr;
				grid-column-gap:3em;
			}
			.wrapper{
				margin:4em;
				padding:2em;
				margin-Right:0px;
				background-color:#dbdbea;
				display:grid;
				grid-template-columns:repeat(5,1fr);
				grid-template-rows:repeat(5,1fr);
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
			.wrapper h2{
				text-align-last: center;
				justify-content: center;
				
			}
			.side{
				margin:4em;
				padding:2em;
				margin-left:2px;
				background:#f9f5f5;
			}
			.pin{
				margin:.5em;
				display:grid;
				grid-template-columns:1fr 1fr 1fr;
				grid-template-rows:1fr 1fr 1fr 1fr;
				text-align: center;
				align-items:stretch;
			}
			.pin button{
				background-color:gray;
				height:30px;
				margin:3px;
				padding:2px;
				border-radius: 25px;
				
			}
			.pin input{
				grid-column:2/4;
				background-color:crimson;
				border-radius: 25px;
				
				
			}
			.screen{
				text-align: center;
				padding:1px;
			}
			select{
				width:50px
			}
			.slots{
				display: inline-block;
				align-items: center;
			}
			.slots .input{
				border-radius: 10px;
				margin:5px;
				display:block;
				background-color:black;
				width:100px;
				height:10px;
			}
			.notes .input{
				border-radius: 10px;
				margin:5px;
				display:block;
				background-color:black;
				width:230px;
				height:10px;
			}
			.buy{
				width:95%;
				margin:6%;
				margin-right:3%;
				background-color:green;
				border-radius: 10px;
				border: 3px solid #000000;
				height:50px;
				font-size:33px;
				
			}
			.card >div{
				border-radius: 10px;
				margin:5px;
				display:block;
				background-color:black;
				width:250px;
				height:10px;
			}
			.pin a{
				background-color:gray;
				margin:1px;
				border-radius: 10px;
				border: 2px solid #000000;
				height:25px;
			}
			.yo{
				color:green;
			}
	    </style>
	    <script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'>
	    $(document).ready(function() {
	        $('button').click(function() {
	            alert($(this).attr("value"));
	        });
	    });
		</script> 
	</head>
	<body>
		<h1><a href="/VendingMachine/add">🔒</a> click here to open</h1>
		<div class="upper">
			<div class="wrapper">
				<c:set var="count" value="0" scope="page" />
			    <c:forEach items="${itemss}" var="pos">
			    <c:if test="${pos.name!=null}">
			    	<div>
			    		<h3>${pos.name}</h3>
				    	
				    	<h3>${pos.cost}$</h3>
				    	<p>#${count+1} </p>
			    	</div>
			    	</c:if>
			    	<c:if test="${pos.name==null}">
			    	<h2>Empty</h2>
			    	</c:if>
				    <c:set var="count" value="${count + 1}" scope="page"/>
			    </c:forEach>
		    </div>
		    <div class="side">
		    	<div class="screen">
		    	<c:if test="${first== 0 }">
				<c:set var="past" scope="session" value="${first}"/>   
				</c:if>
		    	
		    	<c:if test="${first>-1}">
		    		<c:set var="total" value="${first}" />
				</c:if>
				<c:if test="${second>-1}">
		    		<c:set var="total" value="${first*10+second}" />
				</c:if>
					<c:if test="${target==null}">
		    		<textarea rows="1" cols="30">${total} Does not Exist!!</textarea><p>${button}</p>
					</c:if>
					<c:if test="${target!=null}">
					<textarea rows="1" cols="30">&nbsp ${target.name} &nbsp || &nbsp ${target.cost} $ &nbsp || ${target.quantity} left</textarea><p>${button}</p>
					</c:if>
		    	</div>
		    	<form:form action="/VendingMachine/home/${total-1}/buy" >
				
		    	<div class="pin">
		   			<%for ( test = 0; test < 10; test++){ %>
				         <a href="<%=test%>/"><%= test %></a>
				      <%}%>
				      <input type="submit" value="Submit" type="button"/>
		    	</div>
		    	</form:form>
		    	<div></div>
		    	<div class="slot">
		    		<h1>here</h1>
		    	</div>
		    	<form:form action="/buy/yo" method="POST" modelAttribute="item">
		    	<div class="slots" name="slots">
		    		<h3>Accept 10,50 Cent or 1$ </h3>
		    		<div class="input"></div>
		    		<select id="coins">
					  <option value=".1">10C</option>
					  <option value=".5">50C</option>
					  <option value="1">1$</option>
					</select>
					
					
		    	</div>
			    	<div class="notes" name="notes">
			    		<h3> Accept 20$ or 50$ only</h3>
			    		<div class="input"></div>
			    		<select id="coins">
						  <option value="20">20$</option>
						  <option value="50">50$</option>
						</select>
			    	</div>
		    		<input class="buy"type="submit" value="|  |  buy  |  |"/>
				</form:form>
				<div class="card">
			    	<h2>Enter your card</h2>
			    	<div></div>
		    	</div>
		    	<h1 class="yo">${good}</h1>
		    </div>
	    </div>
    </body>
</html>