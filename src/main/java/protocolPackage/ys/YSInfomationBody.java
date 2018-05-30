package protocolPackage.ys;

import java.nio.ByteBuffer;

import protocolPackage.common.CastValue;
import protocolPackage.common.TransUtil;

public class YSInfomationBody{

	//包头
	protected int bodyStart = 0;
	//包长度 = 总长度-8 =总长度 - 包头 - 包尾
	protected int bodyLength =0 ;
	/*
	 * 2-xml部分采用XML描述
	 * 3-xml部分采用JSON描述
	 */
	protected int version = 0;
	//命令码
	protected int commandCode =0;
	//包尾
	protected int bodyEnd = 0;
	
	protected ByteBuffer fData = null;

	public int getBodyStart() {
		return bodyStart;
	}

	public void setBodyStart(int bodyStart) {
		this.bodyStart = bodyStart;
	}

	public int getBodyLength() {
		return bodyLength;
	}

	public void setBodyLength(int bodyLength) {
		this.bodyLength = bodyLength;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getCommandCode() {
		return commandCode;
	}

	public void setCommandCode(int commandCode) {
		this.commandCode = commandCode;
	}

	public int getBodyEnd() {
		return bodyEnd;
	}

	public void setBodyEnd(int bodyEnd) {
		this.bodyEnd = bodyEnd;
	}

	public ByteBuffer getfData() {
		return fData;
	}

	public void setfData(ByteBuffer fData) {
		this.fData = fData;
	}
	
	public byte[] readByte(byte[] data)
	{
		int max = data.length + 20;
		bodyLength = max -12;
		byte[] r = new byte[max];
		
		byte[] s = CastValue.getFixStartByte();
		byte[] e = CastValue.getFixEndByte();
		byte[] v = TransUtil.int32ToByte4BigEndian(this.version);
		byte[] l = TransUtil.int32ToByte4BigEndian(this.bodyLength);
		byte[] c = TransUtil.int32ToByte4BigEndian(this.commandCode);
		int index =0;
		
		for (int i=0 ; i< 4 ; i++)
		{
			r[index] = s[i];
			index++;
		}
		for (int i=0 ; i< 4 ; i++)
		{
			r[index] = l[i];
			index++;
		}
		for (int i=0 ; i< 4 ; i++)
		{
			r[index] = v[i];
			index++;
		}
		for (int i=0 ; i< 4 ; i++)
		{
			r[index] = c[i];
			index++;
		}
		for (int i=0 ; i< data.length ; i++)
		{
			r[index] = data[i];
			index++;
		}
		for (int i=0 ; i< 4 ; i++)
		{
			r[index] = e[i];
			index++;
		}
		return r;
	}
	
}
