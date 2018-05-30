package use.com.nettySupport.param;

public class QJSignalParameter implements ISocketParameter
{

	private int port = 0;
	private boolean isEnable = false;
	private int readIdle = 5 ;
	
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
	public int getReadIdle() {
		// TODO Auto-generated method stub
		return readIdle;
	}
	public void setReadIdle(int idle) {
		// TODO Auto-generated method stub
		readIdle = idle;
	}
	

	
	
}
