/**
假客户端界面js主入口
**/
layui.use(['layer', 'form', 'element', 'layedit'], function () {
	layer = layui.layer;
	var form = layui.form();
	element = layui.element();
	layedit = layui.layedit;

	function setTips(chooser, text) {
		let index = 999;
		$(chooser).mouseenter(function () {
			index = layer.tips(text, this, {
				time: 100000
			});
		});
		$(chooser).mouseleave(function () {
			layer.close(index);
		});
	}

	setTips('#navtab-chat', '客服会话中心');
	setTips('#navtab-dashboard', '统计');
	setTips('#navtab-knowledge', '知识库与常用语');
	setTips('#navtab-client', '客户资料库');
	setTips('#navtab-setting', '设置');

	editorIndex = layedit.build('LAY_editor', {
		height: 140,
		tool: [
			'strong',
			'italic',
			'underline',
			'del',
			'link',
			'face',
			'image',
		]
	});
});

//WS 成功后回调
function establishedConnectionCallback() {
	function getCookie(name) {
		var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
		if (arr = document.cookie.match(reg))
			return decodeURIComponent(arr[2]);
		else
			return null;
	}
	MControl.send(new ClientLogin(1,getCookie('account')));
}

//WS 消息控制开始

let webSocket = new WebSocket("ws://192.168.1.109:8080/ClientWS");
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
		let obj = JSON.parse(msg)
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



class CommonLanguage {
	constructor(id, sentence) {
		this.id = id;
		this.sentence = sentence;
	}
}
let commonLangManager = {
	commonLangList: [],
	send: function (commonLanguage) {
		// TODO 告知服务器加commonLanguage.id频率
		//快速上位
		layedit.setContent(editorIndex, commonLanguage.sentence, true);
	}
}

function clickKnowledge(knowledge) {
	layedit.setContent(editorIndex, knowledge, true);
}




let nickNameMap = {
	1: 'Lee',
	2: 'Sun',

}

function getUserBriefInfo(userId, userType) {
	if (!nickNameMap[userId]) {
		return "未名";
	} else {
		return nickNameMap[userId];
	}
}

class Conversation {
	constructor(conversationId, userId, msgList) {
		this.conversationId = conversationId;
		this.userId = userId;
		this.avatar = 'img/tx.png',
			this.userNickName = getUserBriefInfo(userId);
		this.hasNewMsg = true;
		this.msgList = msgList;

		this.knowledgeList = []; //该会话的知识库列表
	}
}

class Knowledge {
	constructor(keyWord, content) {
		this.keyWord = keyWord;
		this.content = content;
	}
}

class VueChatMessage {
	constructor(senderId, isMine, time, contentType, content) {
		this.senderId = senderId;
		this.userNickName = getUserBriefInfo(senderId);
		this.isMine = isMine;
		this.time = time;
		this.contentType = contentType;
		this.content = content;
	}
}

let conversationList = [];
conversationList.push(new Conversation(1, 1, [
	new VueChatMessage(1, false, new Date(), 0, 'hello'),
	new VueChatMessage(1, false, new Date(), 0, '在吗？'),
	new VueChatMessage(4, true, new Date(), 0, '在的，我是客服人员，请问您有什么问题需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？'),
	new VueChatMessage(1, false, new Date(), 0, '在吗？'),
	new VueChatMessage(4, true, new Date(), 0, '<p>haha</p><p>lala</p>'),
]));
conversationList.push(new Conversation(2, 2, []));

let chatApp = new Vue({
	el: '#chatApp',
	data: {
		conversationList: conversationList,
		currentConversation: -1,
		commonLangManager: commonLangManager,
	},
	methods: {
		chooseConversation: function (conversation) {
			this.currentConversation = conversation;
			conversation.hasNewMsg = false;
		}
	}
});






// TEST
function fxx(str){
	MControl.send(new ClientChat(conversationId,clientId,0,str));
}