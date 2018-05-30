package use.com.nettySupport.param;

public class FlashSocketParameter implements ISocketParameter {

	private int port = 0;
	private boolean isEnable = false;
	private String cnnIp = "127.0.0.1";
	private boolean enableSkipHead = false;
	//服务器心跳间隔
	private int readIdle = 5 ;
	
	
	public boolean isEnableSkipHead() {
		return enableSkipHead;
	}
	public void setEnableSkipHead(boolean enableSkipHead) {
		this.enableSkipHead = enableSkipHead;
	}
	public int getPort() {
		// TODO Auto-generated method stub
		return port;
	}
	public void setPort(int aport) {
		// TODO Auto-generated method stub
		port = aport;
	}
	public boolean isEnable() {
		// TODO Auto-generated method stub
		return isEnable;
	}
	public void setEnable(boolean av) {
		// TODO Auto-generated method stub
		isEnable = av;
	}
	public String getCnnIp() {
		return cnnIp;
	}
	public void setCnnIp(String cnnIp) {
		this.cnnIp = cnnIp;
	}
	public int getReadIdle() {
		// TODO Auto-generated method stub
		return readIdle;
	}
	public void setReadIdle(int idle) {
		// TODO Auto-generated method stub
		readIdle = idle;
	}

	
}
