package use.com.nettySupport.param;

/**
 * 元格加油socket配置参数
 * 项目名称:use.nettySupport
 * 类型名称:YPTongHandParameter
 * 类型描述:
 * 作者:wx
 * 创建时间:2018年3月27日
 * @version:
 */
public class YPTongHandParameter implements ISocketParameter
{

	private int port = 0;
	private boolean isEnable = false;
	private int readBufferSize = 10;
	private int readIdle = 5 ;
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	public int getReadBufferSize() {
		return readBufferSize;
	}
	public void setReadBufferSize(int readBufferSize) {
		this.readBufferSize = readBufferSize;
	}
	public int getReadIdle() {
		return readIdle;
	}
	public void setReadIdle(int readIdle) {
		this.readIdle = readIdle;
	}


	
}
