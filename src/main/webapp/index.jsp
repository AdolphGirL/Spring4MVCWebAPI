<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<title>IFI Web API Index</title>

	<link rel="stylesheet" type="text/css" href="<c:url value='/webjars/bootstrap/4.3.1/css/bootstrap.min.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/cover.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css' />">
	<style>
		.bd-placeholder-img {
			font-size: 1.125rem;
			text-anchor: middle;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
		}

		@media ( min-width : 768px) {
			.bd-placeholder-img-lg {
				font-size: 3.5rem;
			}
		}
	</style>
	
	<script type="text/javascript" src="<c:url value='/webjars/bootstrap/4.3.1/js/bootstrap.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/webjars/jquery/3.0.0/jquery.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/main.js' />"></script>
	<script type="text/javascript">
	</script>
</head>
<body class="text-center">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<header class="masthead mb-auto">
			<div class="inner">
				<h3 class="masthead-brand">Cover</h3>
				<nav class="nav nav-masthead justify-content-center">
					<a class="nav-link active" href="#">Home</a> 
					<a class="nav-link" href="#">Features</a> 
					<a class="nav-link" href="#">Contact</a>
				</nav>
			</div>
		</header>
		
		<div role="main" class="inner cover">
			<h1 class="cover-heading">Cover your page.</h1>
			<p class="lead">Cover is a one-page template for building simple
				and beautiful home pages. Download, edit the text, and add your own
				fullscreen background photo to make it your own.</p>
			<p class="lead">
				<a href="#" class="btn btn-lg btn-secondary">Learn more</a>
			</p>
		</div>

		<footer class="mastfoot mt-auto">
			<div class="inner">
				<p>
					Cover template for <a href="https://getbootstrap.com/">Bootstrap</a>,
					by <a href="https://twitter.com/mdo">@mdo</a>.
				</p>
			</div>
		</footer>
	</div>
</body>
</html>