<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<meta charset="UTF-8"></meta>
	</head>
	<body>
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value='/membros' />">Membros</a>
				<a class="navbar-brand" href="<c:url value='/competencias' />">Competências</a>
			</div>
		</nav>
		<div class="container">
			<h4><a href="<c:url value="competencia/form" />">Cadastrar</a></h4>
			<table class="table table-bordered">
			    <thead>
			      	<tr>
				        <th>Nome</th>
						<th></th>
			      	</tr>
		    	</thead>
		    	<tbody>
		    		<c:forEach items="${competencias}" var="competencia">
				      	<tr>
					        <td>${competencia.nome}</td>
					        <td>
					        	<a href="<c:url value="/competencia/editar?id=${competencia.id}" />" class="btn btn-primary">Editar</a>
					        	<a href="<c:url value="/competencia/deletar?id=${competencia.id}" />" class="btn btn-danger">Deletar</a>
					        </td>
				     	</tr>
			     	</c:forEach>
			      	
			    </tbody>
		 	</table>
		</div>
	</body>
</html>