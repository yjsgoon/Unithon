define(function() {

    var EnterRoomService = function () {};

    EnterRoomService.prototype.execute = function (visit, functions) {
        $.post('http://192.168.20.133:8080/Conting/roomvisit', { visit : visit },
            function(data, status){
                console.log(data);
                if(status == "success"){
                    if(data.code == 206){
                        functions.after(data.roomvisitor);
                    }else{
                        functions.error(data.code, data.msg); // 방이 없는 경우
                    }
                }else{
                    functions.error(1000, "통신중 에러가 발생했습니다.");
                }
            });
    };

    return EnterRoomService;
});