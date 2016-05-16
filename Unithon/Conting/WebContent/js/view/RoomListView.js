define(function() {

    var RoomListView = function () {};
    
    RoomListView.prototype.setPresentator = function (presentator) { this.presentator = presentator };

    RoomListView.prototype.render = function (data) {
    	var presentator = this.presentator;
    	var source   = $("#card-template").html();
        var template = Handlebars.compile(source);;
        var html    = template(data);
        $("#roomList").html("");
        $("#roomList").html(html);
        
        $("#createRoomCard").click(function(){
            $("#createRoomModal").modal();
            $('.begin-clockpicker').clockpicker({autoclose: true});
            $('.end-clockpicker').clockpicker({autoclose: true});
        });

        $("#createRoomBtn").click(function(event){
            event.preventDefault();

            var roomTitle = $("#roomTitle").val();
            var purpose = $('input:radio[name="purpose1"]:checked').data('purpose');
            console.log("checked').val(); "+purpose);
            var beginTime = $("#beginTime").val();
            var endTime = $("#endTime").val();
            var capacity = $('input:radio[name="capacity"]:checked').val();

            presentator.createRoom(roomTitle, capacity, purpose, beginTime, endTime);
        });

        $(".enter").click(function(event){
        	window.location.href = "file:///E:/Study/Project/Eclipse/Conting/WebContent/chat.html";
        });
    };

    return RoomListView;
});