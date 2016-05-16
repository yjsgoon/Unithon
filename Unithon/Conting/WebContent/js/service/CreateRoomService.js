define(function() {

    var CreateRoomService = function () {};

    CreateRoomService.prototype.execute = function (username, title, capacity, purpose, beginTime, endTime, functions) {
    	console.log("purpose :"+purpose);
        $.post('http://192.168.20.133:8080/Conting/roomcreate',
            { profileName : username,
            title : title,
            capacity : capacity,
            purpose : purpose,
            beginTime : beginTime,
            endTime : endTime },

            function(data, status){
                if(status == "success"){
                    functions.after();
                }
                console.log(data);
            });
    };

    return CreateRoomService;
});