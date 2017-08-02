//咨询者端的
MControl.addHandle('ConversationStart',function(content) {
	$.cookie('account',content.account,{expires: 15, path: '/' });
	conversationId=content.conversationId;
	clientId=content.clientId;
});