package fire.Server;

/*
 * 管理用户socket
 */

import java.util.Vector;

public class ChatManager {
	//使ChatManager单例化
	private ChatManager(){}
	private static final ChatManager chatmanager=new ChatManager();
	public static ChatManager getChatManager() {
		return chatmanager;
	}
	//线程集合
	Vector<ChatSocket> vector = new Vector<ChatSocket>();
	//增加
	public void add(ChatSocket cs) {
		vector.add(cs);
	}
	//删除
	public void remove(ChatSocket cs) {
		vector.remove(cs);
	}
	
	//发布信息
	public void publish(ChatSocket cs,String msg) {
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket chatsocket = vector.get(i);
			if (!cs.equals(chatsocket)) {
				chatsocket.sendMsg(msg);
			}
		}
	}
}
