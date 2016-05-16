define(function() {

    var GetUsernameService = function () {};

    GetUsernameService.prototype.execute = function (functions) {
        $.get("http://192.168.20.133:8080/Conting/profile", function(data, status) {
            if(status == "success"){
                if(data.code == 200)
                    functions.after(data.name);
                else if(data.code == 401)
                    functions.error(data.code, data.msg);
            }else{
                functions.error(1000, "통신중 에러가 발생했습니다.");
            }
        });
    };

    return GetUsernameService;
});