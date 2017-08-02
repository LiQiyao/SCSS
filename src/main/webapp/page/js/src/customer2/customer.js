class VueChatMessage {
    constructor(senderId, fromClient, time, contentType, content) {
        this.senderId = senderId;
        this.fromClient = fromClient;
        this.time = time;
        this.contentType = contentType;
        this.content = content;
    }
}

Date.prototype.Format = function (fmt) { //author: meizz 
	var o = {
		"M+": this.getMonth() + 1, //月份 
		"d+": this.getDate(), //日 
		"h+": this.getHours(), //小时 
		"m+": this.getMinutes(), //分 
		"s+": this.getSeconds(), //秒 
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		"S": this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

layui.use(['layer', 'form', 'element', 'layedit', 'util'], function () {
    layer = layui.layer;
    var form = layui.form();
    element = layui.element();
    layedit = layui.layedit;
    var util = layui.util;

    //使用内部工具组件
    util.fixbar({
        bar1: true,
        click: function (e) {
            if (e == 'bar1') {
                openKF();
            }
        }
    });
    editorIndex = layedit.build('LAY_editor', {
        height: 100,
        tool: [
            'strong',
            'link',
            'face',
            'image',
        ]
    });

});

function openKF() {
    layerIndex = layer.open({
        type: 1,
        content: $('#kfWindow'),
        title: '在线客服',
        area: '400px',
    });
}

let conversationId = null;
let clientId = null;
let FLAG_UPDATE_CHAT = false;

//WS 成功后回调
function establishedConnectionCallback() {
    function getCookie(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg))
            return decodeURIComponent(arr[2]);
        else
            return null;
    }
    MControl.send(new ClientLogin(1, getCookie('account')));
}
//WS 消息控制开始

let webSocket = new WebSocket("ws://localhost:8080/ClientWS");
let MControl = {
    ws: webSocket,
    send: function (content) {
        let className = content.__proto__.constructor.name;
        let type = className[0].toLowerCase() + className.substring(1);
        let message = new Message(type, content);
        this.ws.send(JSON.stringify(message));
    },

    handleSet: {},
	handle: function (msg) {
		let obj = JSON.parse(msg);
		obj.type = obj.type[0].toUpperCase() + obj.type.substring(1);
		if (!!this.handleSet[obj.type]) {
			return this.handleSet[obj.type](obj.content);
		} else {
			console.log('Error: Cannot find handle for type:"' + obj.type + '"');
		}
	},
    addHandle: function (name, fun) {
        this.handleSet[name] = fun;
    }
}
webSocket.onerror = function () {
    console.log("ERROR!");
};
webSocket.onopen = function () {
    console.log("Link established.");
    establishedConnectionCallback();
};
webSocket.onmessage = (event) => {
    console.log(event.data);
    MControl.handle(event.data);
};
webSocket.onclose = () => {
    console.log("Closed.");
};

//WS 消息控制结束



function fxx(str) {
    MControl.send(new ClientChat(conversationId, clientId, 0, str));
}
let chatApp = new Vue({
    el: '#chatMain',
    data: {
        nickNameManager: [{}, {}], //0-客服，1-客户
        msgList: [],
    },
    methods: {
        fastSend: fxx,
        sendChatMsg: function() {
            MControl.send(new ClientChat(conversationId, clientId, 0, layedit.getContent(editorIndex)));
            layedit.setContent(editorIndex, '', false);
        }
    },
    updated:function(){
		if(FLAG_UPDATE_CHAT){
			$('#chatMain-msgList')[0].scrollTop = $('#chatMain-msgList')[0].scrollHeight;
			FLAG_UPDATE_CHAT = false;
		}
	}
});

function getUserBriefInfo(userId, userType) { //userType 0-客户，1-客服
    let nickNameMap = chatApp.nickNameManager;
    if(userType != null){
        if (!nickNameMap[1-userType][userId]) {
            MControl.send(new UserInfoReq(userType, userId));
            return "未名";
        } else {
            return nickNameMap[1-userType][userId];
        }
    } else {
        MControl.send(new UserInfoReq(0, userId));
    }
}