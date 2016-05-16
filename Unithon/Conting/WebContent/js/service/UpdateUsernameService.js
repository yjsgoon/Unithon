define(function() {

    var UpdateUsernameService = function () {};

    UpdateUsernameService.prototype.execute = function (name, functions) {
        $.post("http://192.168.20.133:8080/Conting/profile", { profileName : name }, function(data, status) {
            if(status == "success"){
                if(data.code == 200)
                    functions.after();
                else if(data.code == 401)
                    functions.error(data.code, data.msg);
            }else{
                functions.error(1000, "통신중 에러가 발생했습니다.");
            }
        });
    };

    return UpdateUsernameService;
});