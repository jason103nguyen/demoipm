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
<div class="fixed-top">
	<div class="position-relative" style="top: 90vh; left: 30px;">
		<div class="d-flex rounded-3">
			<h2 id="chat-icon" class="text-primary p-2"><i class="bi bi-chat-left-text-fill"></i></h2>
			<div id="chat-section" class="bg-light position-relative border rounded-3" style="width: 300px; height: 400px; top: -400px;">
				<div class="d-flex flex-column justify-content-between h-100">
					<div>
						<div id="connecting" class="h6 bg-primary text-white m-0 p-2 rounded-top w-100">Chat</div>
						<select id="to-username" class="form-select"></select>
					</div>
					<div id="chat-box" class="flex-fill w-100" style="width: 100%; overflow-y: auto"></div>
					<div class="w-100 bg-primary p-2 rounded-bottom">
						<input id="chat-text" type="text" class="form-control form-control-sm bg-white rounded-pill w-100 py-0 px-2"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('#chat-section').hide();

		$('#chat-icon').click(function () {
			$('#chat-section').toggle();
		})

		let inputChat = $('#chat-text');
		let messageArea = $('#chat-box');
		let connectingElement = $('#connecting');

		let stompClient = null;
		let username = null;


		function connect() {
			username = $('#username').text().trim();

			let socket = new SockJS('/ws');
			stompClient = Stomp.over(socket);

			stompClient.connect({}, onConnected, onError);
		}

		// Connect to WebSocket Server.
		connect();

		function onConnected() {
			// Subscribe to the Public Topic
			stompClient.subscribe('/user/topic/privateChatRoom', onMessageReceived);
			// Tell your username to the server
			stompClient.send("/app/chat.addUser",
					{},
					JSON.stringify({sender: username, type: 'JOIN'})
			)
			connectingElement.html(`<i class="text-success me-2 bi bi-circle-fill"></i>Active (` + username + `)`);
		}

		function onError(error) {
			connectingElement.html(`<i class="text-danger me-2 bi bi-circle-fill"></i>Not Connected`);
		}

		function sendPrivateMessage(event, toUsername) {
			let messageContent = inputChat.val().trim();
			if(messageContent && stompClient) {
				let chatMessage = {
					sender: username,
					receiver: toUsername,
					content: inputChat.val(),
					type: 'CHAT'
				};
				stompClient.send("/app/chat.sendPrivateMessage", {}, JSON.stringify(chatMessage));
				inputChat.val('');
			}
			event.preventDefault();
		}


		function onMessageReceived(payload) {
			let message = JSON.parse(payload.body);
			if (message.sender !== $('#to-username :selected').text() &&
					message.receiver !== $('#to-username :selected').text()) {
				return;
			}
			renderMessage(message);
		}

		$("#chat-text").on('keyup', function (e) {
			if (e.key === 'Enter' || e.keyCode === 13) {
				let toUsername = $('#to-username :selected').text();
				sendPrivateMessage(e, toUsername);
			}
		});

		$('#to-username').select2({
			theme: "bootstrap-5",
			placeholder: "To user",
			ajax: {
				url: "${pageContext.request.contextPath}/get-active-users?username=" + username,
				dataType: 'json',
				processResults: function (data) {
					let processData = data.map(item => ({id: item, text: item}));
					return {
						results: processData
					};
				}
			},
			minimumResultsForSearch: -1
		});

		// change chat content to selected user
		$('#to-username').on('select2:select', function (event) {
			event.preventDefault();
			$.ajax({
				url: "${pageContext.request.contextPath}/get-chat-history?fromUsername=" + username + "&toUsername=" + $('#to-username :selected').text(),
				data: {
					format: 'json'
				},
				error: function() {
				},
				success: function(chatMessages) {
					messageArea.empty();
					chatMessages.forEach(message => {
						renderMessage(message);
					});
				},
				type: 'GET'
			});
		});

		function renderMessage(message) {
			let messageElement = null;
			if (username === message.sender) {
				messageElement = $(`<div class="align-self-start px-3 py-1 m-1 text-success border rounded-pill" style="word-wrap: break-word; width: fit-content"></div>`);
			} else {
				messageElement = $(`<div class="align-self-end px-3 py-1 m-1 text-danger border rounded-pill" style="word-wrap: break-word; width: fit-content"></div>`)
			}

			if(message.type === 'JOIN') {
				// messageElement.classList.add('event-message');
				message.content = message.sender + ' joined!';
			} else if (message.type === 'LEAVE') {
				// messageElement.classList.add('event-message');
				message.content = message.sender + ' left!';
			} else {
				message.content = message.sender + ': ' + message.content;
			}

			messageElement.text(message.content);
			messageArea.append($(`<div class="d-flex flex-column"></div>`).html(messageElement));
			messageArea.scrollTop(messageArea.height());
		}
	</script>
</div>
<jsp:invoke fragment="customScript"/>
</body>

</html>