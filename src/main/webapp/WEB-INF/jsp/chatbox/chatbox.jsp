<div class="fixed-top" style="width: fit-content">
    <div class="toast-container position-relative" style="top: 1vh; right: -77vw;">
    </div>
</div>
<div class="fixed-top" style="width: fit-content">
    <div class="position-relative" style="bottom: -90vh; left: 30px;">
        <div class="d-flex rounded-3">
            <div>
                <button id="chat-icon" type="button" class="btn btn-sm btn-primary position-relative">
                    <i class="bi bi-chat-left-text-fill"></i>
                    <span id="message-alert" class="position-absolute top-0 start-0 translate-middle p-2 bg-danger border border-light rounded-circle"></span>
                </button>
            </div>
            <div id="chat-section" class="bg-light position-relative border rounded-3"
                 style="width: 300px; height: 400px; top: -410px;">
                <div class="d-flex flex-column justify-content-between h-100">
                    <div>
                        <div id="connecting" class="h6 bg-primary text-light m-0 p-2 rounded-top w-100">Chat</div>
                        <select id="to-username" class="form-select"></select>
                    </div>
                    <div id="chat-box" class="flex-fill w-100" style="width: 100%; overflow-y: auto"></div>
                    <div class="w-100 bg-primary p-2 rounded-bottom">
                        <input id="chat-text" type="text"
                               class="form-control form-control-sm bg-white rounded-pill w-100 py-0 px-2"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    /** Initial variable **/
    let unreadMessage = null;
    let inputChat = $('#chat-text');
    let messageArea = $('#chat-box');
    let connectingElement = $('#connecting');
    let stompClient = null;
    let username = null;

    /** Initial event **/
    $('#message-alert').hide();
    $('#chat-section').hide();
    connect();

    /** Set up connection to WebSocket Server **/
    function connect() {
        username = $('#username').text().trim();

        let socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }

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

    /** Set up logic render message, chat UI **/
    $('#chat-icon').click(function () {
        console.log("Unread message: " + JSON.stringify(unreadMessage));
        if (unreadMessage != null) {
            $('#to-username').html(`<option value="` + unreadMessage.sender + `">` + unreadMessage.sender + `</option>`);
            $('#to-username').trigger({
                type: 'select2:select',
                params: {
                    data: {id: unreadMessage.sender, text: unreadMessage.sender, selected: true}
                }
            });
            $('#chat-section').show();
            $('#message-alert').hide();
            unreadMessage = null;
        } else {
            $('#chat-section').toggle();
        }
    })

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
        console.log(event.params);
        let toUsername = event.params.data.text;
        event.preventDefault();
        $.ajax({
            url: "${pageContext.request.contextPath}/get-chat-history?fromUsername=" + username + "&toUsername=" + toUsername,
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

    function showIncomingUnreadMessage(message) {
        let newToast = $(`
			<div class="toast" style="width: 20vw;">
				<div class="p-2 d-flex justify-content-between list-group-item-info">
					<strong class="me-auto">` + message.sender + `</strong>
					<button type="button" class="btn-close" data-bs-dismiss="toast"></button>
				</div>
				<div class="p-2">
					<p class="m-0">` + message.content + `</p>
				</div>
			</div>
			`);
        $('.toast-container').prepend(newToast);
        newToast.toast('show');
    }

    function onMessageReceived(payload) {
        let message = JSON.parse(payload.body);
        if ($('#chat-section').is(":hidden") ||
            (message.sender !== $('#to-username :selected').text() &&
                message.receiver !== $('#to-username :selected').text())) {
            showIncomingUnreadMessage(message);
            $('#message-alert').show();
            unreadMessage = message;
            return;
        }
        renderMessage(message);
    }

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

        let totalHeight = 0;

        messageArea.children().each(function(){
            totalHeight = totalHeight + $(this).outerHeight(true);
        });
        messageArea.scrollTop(totalHeight);
    }
</script>