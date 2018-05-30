package use.com.nettySupport.param;

public class EgaofenParameter implements ISocketParameter {

	private boolean isEnable = false;
	private int port = 0;
	private int maxBuffer = 1;
	//服务器心跳间隔
	private int readIdle = 5 ;
	public int getPort() {
		return port;
	}
	public void setPort(int aport) {
		port = aport;	
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean av) {
		isEnable = av;	
	}
	public int getReadIdle() {
		return readIdle;
	}
	public void setReadIdle(int idle) {
		readIdle = idle;
	}
	public int getMaxBuffer() {
		return maxBuffer;
	}
	public void setMaxBuffer(int maxBuffer) {
		this.maxBuffer = maxBuffer;
	}
}
