package protocolPackage.ypTong;

import java.io.Serializable;

import protocolPackage.common.TransUtil;

public class YPTongBody implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8720051143374802392L;
	
	private int deviceId=0;
	private int command=0;
	private int version=0;
	private String jsonStr="";
	
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getCommand() {
		return command;
	}
	public void setCommand(int command) {
		this.command = command;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	
	public byte[] getBytes()
	{
		byte[] info = this.getJsonStr().getBytes();
		
		int size = info.length + 12;
		byte [] packagelen = TransUtil.int32ToByte4BigEndian(size);
		byte [] deviceId = TransUtil.int32ToByte4BigEndian(this.deviceId);  
		byte [] command = TransUtil.int32ToByte4BigEndian(this.command);      
		byte [] version = TransUtil.int32ToByte4BigEndian(this.version); 
		
		byte [] r = new byte[size+4];
		int i= 0;
		for(int j = 0; j < 4; j++)
		{             
			r[i] = packagelen[j];
			i++;
		}
		for(int j = 0; j < 4; j++){
			r[i] = deviceId[j];
			i++;
		}
		/*命令*/
		for(int j = 0; j < 4; j++){
			r[i] = command[j];
			i++;
		}
		/*版本*/
		for(int j = 0; j < 4; j++){
			r[i] = version[j];
			i++;
		}
		/*内容*/
		for(int j = 0; j < info.length; j++){
			r[i] = info[j];
			i++;
		}
		return r;
	}
}
