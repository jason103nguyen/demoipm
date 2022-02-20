<%@tag pageEncoding="UTF-8" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="content" fragment="true" %>
<%@attribute name="customScript" fragment="true" %>
<%@attribute name="customCss" fragment="true" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

	<title><jsp:invoke fragment="title"/></title>

	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/jq-3.6.0/dt-1.11.3/datatables.min.css"/>

	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/v/bs5/jq-3.6.0/dt-1.11.3/datatables.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.2.0/dist/select2-bootstrap-5-theme.min.css" />

	<!-- https://cdnjs.com/libraries/sockjs-client -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
	<!-- https://cdnjs.com/libraries/stomp.js/ -->
	<script  src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

	<jsp:invoke fragment="customCss"/>
</head>

<body class="bg-light">
<div class="d-flex" id="wrapper">
	<!-- Sidebar-->
	<div style="height: 100vh; width: 20%;" class="border-end bg-white" id="sidebar-wrapper">
		<div class="sidebar-heading border-bottom bg-light">
			<jsp:include page="/WEB-INF/jsp/logoFpt.jsp"/>
		</div>
		<jsp:include page="/WEB-INF/jsp/menu.jsp"/>
	</div>
	<!-- Page content wrapper-->
	<div style="width: 80%;" id="page-content-wrapper">
		<!-- Top header-->
		<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
			<div class="container-fluid d-flex flex-column align-items-center">
				<jsp:invoke fragment="header"/>
			</div>
		</nav>
		<!-- Page content-->
		<div class="container py-3">
			<jsp:invoke fragment="content"/>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/chatbox/chatbox.jsp"/>
<jsp:invoke fragment="customScript"/>
</body>

</html>