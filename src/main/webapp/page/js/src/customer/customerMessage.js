class Message{
	constructor(type,content){
		this.type=type;
		this.content=content;
	}
}

class ClientLogin{
	constructor(accessId,account){
		this.accessId=accessId;
		this.account=account;
	}
}

class UserInfoReq{
	constructor(userType,userId){
		this.userType=userType;
		this.userId=userId;
	}
}

class ServiceLogin{
	constructor(token){
		this.token=token;
	}
}

class ClientChat{
	constructor(conversationId,clientId,contentType,content,time){
		this.conversationId=conversationId;
		this.clientId=clientId;
		this.contentType=contentType;
		this.content=content;
		this.time=time;//只有服务端才填,可不填
	}
}

class SetScore{
	constructor(conversationId,score){
		this.conversationId=conversationId;
		this.score=score;
	}
}
