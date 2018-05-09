package fire.Server;

/*
 * 与客户端连接的socket
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread {
	
	protected Socket s;
	protected SIMServer simserver;
	protected PrintWriter sendmsg;
	protected String clientIP;
	
	public ChatSocket(Socket s,SIMServer simserver){
		this.s = s;
		this.simserver=simserver;
		clientIP=s.getInetAddress().toString();
		try {
			sendmsg=new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
			sendmsg=null;
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String msg_out) {
		if(sendmsg!=null)
		{
			sendmsg.write(msg_out+"\n");
			sendmsg.flush();
		}
		else
		{
			simserver.appendOut("用户"+clientIP+"已断开连接");
			ChatManager.getChatManager().remove(this);
		}
	}

	@Override
	public void run() {
		try {
			BufferedReader receivemsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String msg_receive = null;
			while ((msg_receive = receivemsg.readLine()) != null) {
				ChatManager.getChatManager().publish(this, msg_receive);
			}
			receivemsg.close();
			simserver.appendOut("用户"+clientIP+"已断开连接");
			ChatManager.getChatManager().remove(this);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			simserver.appendOut("用户"+clientIP+"已断开连接");
			ChatManager.getChatManager().remove(this);
			e.printStackTrace();
		}
		
	}
}

