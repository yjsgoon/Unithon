define(function() {

    var MainPresentator = function (getUsernameService, updateUsernameService, createRoomService, getRoomListService, enterRoomService, exitRoomService,
        sendChatService, updateChatService, roomListView) {
        this.username = undefined;

        this.getUsernameService = getUsernameService;
        this.updateUsernameService = updateUsernameService;
        this.createRoomService = createRoomService;
        this.getRoomListService = getRoomListService;
        this.enterRoomService = enterRoomService;
        this.exitRoomService = exitRoomService;
        this.sendChatService = sendChatService;
        this.updateChatService = updateChatService;
        this.roomListView = roomListView;
    };

    MainPresentator.prototype.setUsernameData = function (username){
        this.username = username;
    };

    MainPresentator.prototype.getUsernameData = function (){
        return this.username;
    };


    // input void
    // return callback
    MainPresentator.prototype.getUsername = function () {
        this.getUsernameService.execute({
            after: function (name) {
                console.log(name);
            },
            error: function (code, msg) {
                switch (code) {
                    case 401:
                        $("#usernameFormModal").modal();
                        break;
                    default:
                        alert(msg);
                        break;
                }
            }
        });
    };

    // input name
    // return void
    MainPresentator.prototype.updateUsername = function (name) {
        var mainPresentator = this;
        this.updateUsernameService.execute(name, {
            after: function () {
                mainPresentator.setUsernameData(name);
                $("#usernameFormModal").modal('hide');
            },
            error: function (code, msg) {
                alert(msg);
            }
        });
    };

    MainPresentator.prototype.createRoom = function (title, capacity, purpose, beginTime, endTime) {
    	var mainPresentator = this;
        console.log(this.getUsernameData());
        console.log(title);
        console.log(purpose);
        console.log(capacity);
        console.log(beginTime);
        console.log(endTime);
        console.log(endTime);

        this.createRoomService.execute(this.getUsernameData(), title, capacity, purpose, moment(beginTime, "HH:mm").unix(), moment(endTime, "HH:mm").unix(), {
            after: function () {
                console.log("성공");
                mainPresentator.getRoomList();
            },
            error: function (code, msg) {
                alert(msg);
            }
        });
    };

    MainPresentator.prototype.getRoomList = function () {
    	var mainPresentator = this;
        this.getRoomListService.execute({
            after: function (roomList) {
            	function toImgSrc(purpose){
            		console.log(purpose);
            		switch (purpose){
            		case 0:	return "dinner.png";
            		case 1: return "beer.png";
            		case 2: return "coffee.png";
            		}
            	}
                console.log(roomList);

                for(var i=0; i<roomList.length; i++){
                	roomList[i].num = i+1;
                    roomList[i].beginTime = moment(roomList[i].beginTime, "x").format('HH:mm');
                    roomList[i].endTime = moment(roomList[i].endTime, "x").format('HH:mm');
                    roomList[i].purpose = toImgSrc(roomList[i].purpose);
                }

                mainPresentator.roomListView.render({roomList : roomList});
            },
            error: function (code, msg) {
                switch (code) {
                    case 404:
                        // 방이 없습니다
                        break;
                    default:
                        alert(msg);
                        break;
                }
            }
        });
    };

    MainPresentator.prototype.enterRoom = function () {
        this.enterRoomService.execute({
            after: function (visitors) {
                console.log(visitors);
            },
            error: function (code, msg) {
                switch (code) {
                    case 406:
                        //  "not exist".
                        break;
                    default:
                        alert(msg);
                        break;
                }
            }
        });
    };

    MainPresentator.prototype.exitRoom = function () {
        this.exitRoomService.execute({});
    };

    MainPresentator.prototype.sendChat = function () {
        this.sendChatService.execute({
            after: function (visitors) {
                console.log(visitors);
            },
            error: function (code, msg) {
                switch (code) {
                    case 406:
                        //  "not exist".
                        break;
                    default:
                        alert(msg);
                        break;
                }
            }
        });
    };


    MainPresentator.prototype.updateChat = function () {
        this.updateChatService.execute({
            after: function (visitors) {
                console.log(visitors);
            },
            error: function (code, msg) {
                switch (code) {
                    case 406:
                        //  "not exist".
                        break;
                    default:
                        alert(msg);
                        break;
                }
            }
        });
    };

    return MainPresentator;
});