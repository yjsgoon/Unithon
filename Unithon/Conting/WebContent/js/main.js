$(function(){
    require(['js/presentator/MainPresentator',
             'js/service/GetUsernameService',
             'js/service/UpdateUsernameService',
             'js/service/CreateRoomService',
             'js/service/GetRoomListService',
             'js/service/EnterRoomService',
             'js/service/ExitRoomService',
             'js/service/SendChatService',
             'js/service/UpdateChatService',
             'js/view/RoomListView',
    ], function (MainPresentator, GetUsernameService, UpdateUsernameService, CreateRoomService, GetRoomListService, EnterRoomService, ExitRoomService, SendChatService, UpdateChatService, RoomListView) {

    	var roomListView = new RoomListView();
        var mainPresentator = new MainPresentator(new GetUsernameService(), new UpdateUsernameService(), new CreateRoomService(), new GetRoomListService(), new EnterRoomService(), new ExitRoomService(), new SendChatService(), new UpdateChatService(), roomListView);
        roomListView.setPresentator(mainPresentator);
        mainPresentator.getRoomList();
        mainPresentator.getUsername();

        $("#usernameBtn").click(function(event){
            var username = $("#username").val();
            mainPresentator.updateUsername(username);
        });
    });
});