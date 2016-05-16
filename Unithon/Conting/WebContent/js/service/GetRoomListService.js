define(function() {

    var GetRoomListService = function () {};

    GetRoomListService.prototype.execute = function (functions) {
        $.get('http://192.168.20.133:8080/Conting/roomlist',
            function(data, status){
                console.log(data);
                if(status == "success"){
                    if(data.code == 204){
                        functions.after(data.roomList);
                    }else{
                        functions.error(data.code, data.msg); // 방이 없는 경우
                    }
                }else{
                    functions.error(1000, "통신중 에러가 발생했습니다.");
                }
            });
    };

    return GetRoomListService;
});