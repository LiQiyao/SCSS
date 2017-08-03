class Message {
	constructor(type, content) {
		this.type = type;
		this.content = content;
	}
}

class ClientLogin {
	constructor(accessId, account) {
		this.accessId = accessId;
		this.account = account;
	}
}

class UserInfoReq {
	constructor(userType, userId) {
		this.userType = userType;
		this.userId = userId;
	}
}

class ServiceLogin {
	constructor(token) {
		this.token = token;
	}
}

class ServiceChat {
	constructor(conversationId, clientId, contentType, content, time) {
		this.conversationId = conversationId;
		this.clientId = clientId;
		this.contentType = contentType;
		this.content = content;
		this.time = time;
	}
}

class ConversationEndReq {
	constructor(conversationId, clientId) {
		this.conversationId = conversationId;
		this.clientId = clientId;
	}
}

class PullReq {
	constructor(serviceId) {
		this.serviceId = serviceId;
	}
}

class CommonLanguageClick {
	constructor(commonLanguageId) {
		this.commonLanguageId = commonLanguageId;
	}
}

class ClientDetailReq {
	constructor(clientId){
		this.clientId = clientId;
	}
}

class FastSearchKnowledges {
	constructor(conversationId,searchSentence){
		this.conversationId = conversationId;
		this.searchSentence = searchSentence;
	}
}

class ChangeClientDetail{
	constructor(clientDetail){
		this.clientId=clientDetail.clientId;
		this.clientName=clientDetail.clientName;
		this.sex=clientDetail.sex;
		this.phoneNum=clientDetail.phoneNum;
		this.email=clientDetail.email;
		this.wx=clientDetail.wx;
		this.qq=clientDetail.qq;
		this.weibo=clientDetail.weibo;
		this.taobao=clientDetail.taobao;
		this.alipay=clientDetail.alipay;
		this.address=clientDetail.address;
	}
}

class DeleteTag{
	constructor(clientId,tag){
		this.clientId=clientId;
		this.tag=tag;
	}
}

class AddTag{
	constructor(clientId,tag){
		this.clientId=clientId;
		this.tag=tag;
	}
}

class SearchClientByNameReq{
	constructor(searchWord){
		this.searchWord=searchWord;
	}
}

class ChangeClientConversationRelationship{
	constructor(conversationId,clientId){
		this.conversationId=conversationId;
		this.clientId=clientId;
	}
}

class TransferReq{
	constructor(transferType,targetId,clientId,conversationId){
		this.transferType=transferType;
		this.targetId=targetId;
		this.clientId=clientId;
		this.conversationId=conversationId;
	}
}