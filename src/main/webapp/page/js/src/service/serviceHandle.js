MControl.addHandle('ServiceInfo', function (info) {
	serviceInfo.serviceId = info.serviceId;
	serviceInfo.serviceName = info.serviceName;
	serviceInfo.nickName = info.nickName;
	serviceInfo.groupName = info.groupName;
	serviceInfo.employeeId = info.employeeId;
	serviceInfo.autoMessage = info.autoMessage;
	$('#headBar-name').html(serviceInfo.serviceName);
});

MControl.addHandle('TransferSignal', function (signal) {
	let map=new Map();
	for (let msg of signal.historyList) {
		msg.time = new Date(msg.time);
		map.set(msg.senderId,msg);
	}

	for(let msg of map.values()){
		getUserBriefInfo(msg.senderId, 1 - msg.fromClient);
	}

	conversationList.push(new Conversation(
		signal.conversationId,
		signal.clientId,
		signal.historyList
	));

	MControl.send(new ClientDetailReq(signal.clientId));
	notificationManager.add({title:'有新会话接入',notiContent:'您接入了新的会话。',time:new Date()});
});

MControl.addHandle('ClientChat', function (chat) {
	let conversation = conversationList.find(cco => cco.conversationId == chat.conversationId);
	conversation.msgList.push(
		new VueChatMessage(chat.clientId, 1, new Date(chat.time), chat.contentType, chat.content)
	);
	if(conversation != chatApp.currentConversation){
		conversation.hasNewMessage = true;
	} else {
		FLAG_UPDATE_CHAT = true;
	}
});

MControl.addHandle('ServiceChat', function (chat) {
	conversationList.find(cco => cco.conversationId == chat.conversationId).msgList.push(
		new VueChatMessage(serviceInfo.serviceId, 0, new Date(chat.time), chat.contentType, chat.content)
	);
	FLAG_UPDATE_CHAT = true;
});

MControl.addHandle('ServiceStatus', function (status) {
	serviceInfo.conversationCount = status.conversationCount;
	serviceInfo.queuePeopleCount = status.queuePeopleCount;
});

MControl.addHandle('UserInfoResp', function (info) {
	chatApp.$set(chatApp.nickNameManager[1 - info.userType], info.userId, info.nickName);
});

let colorList = ['#fafac6', '#fae1c6', '#f6dcf6', '#dcdcf6', '#f6f6dc'];
MControl.addHandle('RecommandKnowledges', function (kl) {
	let c = conversationList.find(function (o) {
		return o.conversationId == kl.conversationId;
	});
	let curMsg = c.msgList[c.msgList.length - 1];

	function addTag(content, tag) {
		let pos = content.indexOf(tag);
		if (pos != -1) {
			let left = content.slice(0, pos);
			let middle = content.slice(pos, pos + tag.length);
			let right = content.slice(pos + tag.length);
			content = left + '<span class="keyWordTag" style="background-color:' + colorList[c.colorCount] + '">' +
				middle + '</span>' + right;
		}
		return content;
	}

	for (let k of kl.knowledgeList) {
		curMsg.content = addTag(curMsg.content, k.keyWord[0]);

		k.bgColor=colorList[c.colorCount];

		c.colorCount++;
		if (c.colorCount >= colorList.length) {
			c.colorCount = 0;
		}
	}
	c.knowledgeList = kl.knowledgeList.concat(c.knowledgeList);
	FLAG_UPDATE_KNOWLEDGE=true;
});

MControl.addHandle('CommonLanguageList',function(resp){
	commonLangManager.commonLangList = resp.commonLanguageList;
});

MControl.addHandle('ClientDetailResp',function(detail){
	detail.recommandTagList=[];
	chatApp.$set(chatApp.clientDetailManager,detail.clientId,detail);
});

MControl.addHandle('ClientDetailResp2',function(detail){
	detail.recommandTagList=[];
	chatApp.$set(chatApp.clientDetailManager,detail.clientId,detail);
	conversationList.find(o=>o.conversationId == detail.conversationId).userId = detail.clientId;
	chatApp.$set(chatApp.nickNameManager[1], detail.clientId, detail.clientName);
});

function _conversationEnd(signal){
	let endedConversationId = signal.conversationId;
	let endedIndex = conversationList.findIndex(o => o.conversationId == endedConversationId);
	
	if(endedIndex != -1){
		let endedConversation = conversationList[endedIndex];
		conversationList.splice(endedIndex,1);
		if(chatApp.currentConversation == endedConversation){
			chatApp.currentConversation = conversationList[0] ? conversationList[0] : -1;
		}
	}
}
MControl.addHandle('ConversationEndSignal',_conversationEnd);
MControl.addHandle('TransferEndSignal',_conversationEnd);

MControl.addHandle('Notification',function(notification){
	notificationManager.add(notification);
});

MControl.addHandle('SearchClientByNameResp',function(resp){
	clientSearchApp.matchList = resp.clientList;
});

MControl.addHandle('RecommandTags',function(recommand){
	let conversation = conversationList.find(cco => cco.conversationId == recommand.conversationId);
	let clientId = conversation.userId;
	let tagList = chatApp.clientDetailManager[clientId].unusedTagList;
	for(let tag of recommand.tagList){
		let index = tagList.findIndex(t=>t==tag);
		tagList.splice(index,1);
	}
	chatApp.clientDetailManager[clientId].recommandTagList = chatApp.clientDetailManager[clientId].recommandTagList.concat(recommand.tagList);
})