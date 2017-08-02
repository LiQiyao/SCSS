/**
客服界面js主入口
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
	MControl.send(new ServiceLogin(getCookie('token')));
}

//WS 消息控制开始

let webSocket = new WebSocket("ws://localhost:8080/ServiceWS");
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

// 本客服人员的信息
let serviceInfo = {
	serviceId: null,
	serviceName: null,
	nickName: null,
	groupName: null,
	employeeId: null,
	autoMessage: null,
	conversationCount: null,
	queuePeopleCount: null,
};


let commonLangManager = {
	commonLangList: [],
	send: function (commonLanguage) {
		//告知服务器加频率
		MControl.send(new CommonLanguageClick(commonLanguage.commonLanguageId));

		//快速上位
		layedit.setContent(editorIndex, commonLanguage.content, true);
	}
}

let notificationManager = {
	notificationList: [],
	add: function (notification) {
		this.notificationList.push(notification);
		showNotification();
		$('#notificationNumTag').html("(" + this.notificationList.length + ")");
	}
}

function newTalk() {
	MControl.send(new PullReq(serviceInfo.serviceId));
}

function clickKnowledge(knowledge) {
	layedit.setContent(editorIndex, knowledge, true);
}

function sendChatMsg(conversation) {
	MControl.send(new ServiceChat(conversation.conversationId, conversation.userId, 0, layedit.getContent(editorIndex)));
	layedit.setContent(editorIndex, '', false);
}

function knowledgeFastSearch() {
	MControl.send(new FastSearchKnowledges(chatApp.currentConversation.conversationId, $('#knowledgeFastSearchInput').val()));
}

function searchClientByName() {
	let keyWord = $('#clientSearchKeyword').val();
	MControl.send(new SearchClientByNameReq(keyWord));
}

let nickNameMap = {};

function getUserBriefInfo(userId, userType) {
	if (!nickNameMap[userId]) {
		if (userType != null) {
			MControl.send(new UserInfoReq(userType, userId));
		} else {
			MControl.send(new UserInfoReq(0, userId));
		}
		return "未名";
	} else {
		return nickNameMap[userId];
	}
}

// 一个会话
class Conversation {
	constructor(conversationId, userId, msgList) {
		this.conversationId = conversationId;
		this.userId = userId;
		this.avatar = 'img/tx.png';
			//this.userNickName = getUserBriefInfo(userId);
		this.hasNewMsg = true;

		let end = 0;
		for (let i = 0; i < msgList.length; i++) {
			if (msgList[i].conversationId == conversationId) {
				end = i;
			}
		}
		this.historyList = msgList.slice(0, end);
		this.msgList = msgList.slice(end, msgList.length);
		this.knowledgeList = []; //该会话的知识库列表
		this.colorCount = 0;
	}
}

// 一条知识(这里不再被需要了)
// class Knowledge {
// 	constructor(keyWord, content) {
// 		this.keyWord = keyWord;
// 		this.content = content;
// 	}
// }

class VueChatMessage {
	constructor(senderId, fromClient, time, contentType, content) {
		this.senderId = senderId;
		//this.userNickName = getUserBriefInfo(senderId);
		this.fromClient = fromClient;
		this.time = time;
		this.contentType = contentType;
		this.content = content;
	}
}

let clientDetailManager = {};

let conversationList = [];
/*conversationList.push(new Conversation(1, 1, [
	new VueChatMessage(1, 1, new Date(), 0, 'hello'),
	new VueChatMessage(1, 1, new Date(), 0, '在吗？'),
	new VueChatMessage(4, 0, new Date(), 0, '在的，我是客服人员，请问您有什么<span class="x1">问题需要</span>咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？需要咨询的吗？'),
	new VueChatMessage(1, 1, new Date(), 0, '在吗？'),
	new VueChatMessage(4, 0, new Date(), 0, '<p>haha</p><p>lala</p>'),
]));
conversationList.push(new Conversation(2, 2, []));*/
let FLAG_UPDATE_KNOWLEDGE = false;
let FLAG_UPDATE_CHAT = false;
let chatApp = new Vue({
	el: '#chatApp',
	data: {
		conversationList: conversationList,
		currentConversation: -1,
		commonLangManager: commonLangManager,
		clientDetailManager: clientDetailManager,
		nickNameManager: [{}, {}], //[0]客服、机器人,[1]客户
	},
	methods: {
		chooseConversation: function (conversation) {
			this.currentConversation = conversation;
			conversation.hasNewMsg = false;
		},
		sendChatMsg: sendChatMsg,
		endConversation: function (conversation) {
			MControl.send(new ConversationEndReq(conversation.conversationId, conversation.userId));
		},
		changeClientDetail: function (conversation) {
			MControl.send(new ChangeClientDetail(clientDetailManager[conversation.userId]));
		},
		resetClientDetail: function (conversation) {
			MControl.send(new ClientDetailReq(conversation.userId));
		},
		removeTag: function (conversation, tag, detail) {
			let pos = detail.tagList.findIndex(o => o == tag);
			detail.tagList.splice(pos, 1);
			detail.unusedTagList.push(tag);
			MControl.send(new DeleteTag(conversation.userId, tag));
		},
		addTag: function (conversation, tag, detail) {
			let pos = detail.unusedTagList.findIndex(o => o == tag);
			detail.unusedTagList.splice(pos, 1);
			detail.tagList.push(tag);
			MControl.send(new AddTag(conversation.userId, tag));
		},
		addTagRecommand: function (conversation, tag, detail) {
			let pos = detail.recommandTagList.findIndex(o => o == tag);
			detail.recommandTagList.splice(pos, 1);
			detail.tagList.push(tag);
			MControl.send(new AddTag(conversation.userId, tag));
		},
		clickKnowledge:clickKnowledge,
	},
	updated:function(){
		if(FLAG_UPDATE_KNOWLEDGE){
			$('#chatRight-a-content')[0].scrollTop = 0;
			FLAG_UPDATE_KNOWLEDGE = false;
		}
		if(FLAG_UPDATE_CHAT){
			$('#chatMain-msgList')[0].scrollTop = $('#chatMain-msgList')[0].scrollHeight;
			FLAG_UPDATE_CHAT = false;
		}
	}
});

let statusBarApp = new Vue({
	el: '#statusBar',
	data: {
		serviceInfo: serviceInfo,
	},
});

let notificationApp = new Vue({
	el: '#notificationApp',
	data: {
		notificationList: notificationManager.notificationList,
	},
});

let layerClientSearchIndex;
let layerTransferWindowIndex;
let clientSearchApp = new Vue({
	el: '#clientSearch',
	data: {
		matchList: [],
		selectedClient:null,
	},
	methods:{
		selectClient:function(client){
			this.selectedClient=client;
		},
		confirm:function(selectedClient){
			if(selectedClient!=null){
				MControl.send(new ChangeClientConversationRelationship(chatApp.currentConversation.conversationId,selectedClient.clientId));
			}
			$('#clientSearchKeyword').val('');
			this.matchList=[];
			layer.close(layerClientSearchIndex);
		},
		cancel:function(){
			$('#clientSearchKeyword').val('');
			this.matchList=[];
			layer.close(layerClientSearchIndex);
		}
	},
});

let transferApp = new Vue({
	el: '#transferWindow',
	data: {
		groupList:[{
			groupName:'售后组',
			groupId:91,
			onlineMembers:[{
				serviceId:1,
				name:'小张',
			},
			{
				serviceId:2,
				name:'小王',
			}],
			expanded:false,
		},
		{
			groupName:'售前组',
			groupId:92,
			onlineMembers:[{
				serviceId:3,
				name:'小刘',
			},
			{
				serviceId:4,
				name:'小李',
			}],
			expanded:false,
		}],
		selected:null,
	},
	methods:{
		expand:function(group){
			group.expanded = !group.expanded;
		},
		chooseGroup:function(group){
			this.selected=group;
		},
		chooseMember:function(member){
			this.selected=member;
		},
		cancel:function(){
			layer.close(layerTransferWindowIndex);
		},
		confirm:function(){
			if(this.selected){
				console.log('发动转接：')
				console.log(this.selected);
				if(!this.selected.employeeId){
					MControl.send(new TransferReq(1,this.selected.groupId,chatApp.currentConversation.userId,chatApp.currentConversation.conversationId));
					layer.close(layerTransferWindowIndex);
				}else{
					MControl.send(new TransferReq(2,this.selected.serviceId,chatApp.currentConversation.userId,chatApp.currentConversation.conversationId));
					layer.close(layerTransferWindowIndex);
				}
			}
		}
	}
});