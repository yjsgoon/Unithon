define(function() {

    var UsernameView = function () { this.data = undefined };
    UsernameView.prototype.getData = function(){
        return this.data;
    };
    UsernameView.prototype.setData = function(){
        this.data = data;
    };

    return UsernameView;
});