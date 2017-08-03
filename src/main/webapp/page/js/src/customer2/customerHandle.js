//咨询者端的
MControl.addHandle('ConversationStart',function(content) {
	$.cookie('account',content.account,{expires: 15, path: '/' });
	conversationId=content.conversationId;
    clientId=content.clientId;

    let map = new Map();
    for (let msg of content.chatLogList){
        msg.time = new Date(msg.time);
        map.set(msg.senderId,msg);
    }
    for(let msg of map.values()){
		getUserBriefInfo(msg.senderId, 1 - msg.fromClient);
    }
    

    chatApp.msgList = content.chatLogList;

    FLAG_UPDATE_CHAT =true;
});

MControl.addHandle('UserInfoResp', function (info) {
	chatApp.$set(chatApp.nickNameManager[1 - info.userType], info.userId, info.nickName);
});

MControl.addHandle('ClientChat', function (chat) {
    getUserBriefInfo(clientId,0);
	chatApp.msgList.push(
		new VueChatMessage(chat.clientId, 1, new Date(chat.time), chat.contentType, chat.content)
    );
    
    FLAG_UPDATE_CHAT =true;
});

MControl.addHandle('ServiceChat', function (chat) {
    getUserBriefInfo(chat.serviceId,1);
	chatApp.msgList.push(
		new VueChatMessage(chat.serviceId, 0, new Date(chat.time), chat.contentType, chat.content)
    );
    
    FLAG_UPDATE_CHAT =true;
});

MControl.addHandle('RobotChat',function(chat){
    getUserBriefInfo(0,1);
    let msg = new VueChatMessage(0,0,new Date(chat.time),0,chat.answer);
    msg.questionPush = chat.questionPush;
    chatApp.msgList.push(msg);

    FLAG_UPDATE_CHAT =true;
});

MControl.addHandle('ScoreReq',function(data){
    openScore();
});