package use.com.nettySupport.param;

public interface ISocketParameter {

	int getPort();
	void setPort(int aport);
	
	boolean isEnable();
	void setEnable(boolean av);
	
	int getReadIdle();
	void setReadIdle(int idle);
}
