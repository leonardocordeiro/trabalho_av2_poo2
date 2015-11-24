<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
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
		<form class="form-horizontal" role="form"
			action="<c:url value="/membro" />" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Nome:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="nome"
						value="${membro.nome}" placeholder="Seu nome">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">E-mail:</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" name="email"
						value="${membro.email}" placeholder="Seu email">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<c:choose>
						<c:when test="${empty membro.id}">
							<button type="submit" class="btn btn-primary">Cadastrar</button>
						</c:when>
						<c:when test="${not empty membro.id}">
							<button type="submit" class="btn btn-primary">Atualizar</button>
						</c:when>
					</c:choose>
				</div>
			</div>
			<input type="hidden" name="id" value="${membro.id}">
		</form>
	</div>
</body>
</html>