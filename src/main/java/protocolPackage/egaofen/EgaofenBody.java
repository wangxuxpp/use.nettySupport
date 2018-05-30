package protocolPackage.egaofen;

import io.netty.buffer.ByteBuf;

/**
 * 易高分包对象
 * 项目名称:nettySupport
 * 类型名称:EgaofenBody
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年9月14日
 * @version:
 */
public class EgaofenBody {

	//命令码
	protected int commandCode =0;
	//整包大小
	protected int packageSize = 0;
	//包尾
	protected int bodyEnd = 0;
	//传送数据
	protected ByteBuf fData = null;
	//
	protected EgaofenQuestionBody questBody = null;
	
	public int getCommandCode() {
		return commandCode;
	}
	public void setCommandCode(int commandCode) {
		this.commandCode = commandCode;
	}
	public int getPackageSize() {
		return packageSize;
	}
	public void setPackageSize(int packageSize) {
		this.packageSize = packageSize;
	}
	public int getBodyEnd() {
		return bodyEnd;
	}
	public void setBodyEnd(int bodyEnd) {
		this.bodyEnd = bodyEnd;
	}
	public ByteBuf getfData() {
		return fData;
	}
	public void setfData(ByteBuf fData) {
		this.fData = fData;
	}
	public EgaofenQuestionBody getQuestBody() {
		return questBody;
	}
	public void setQuestBody(EgaofenQuestionBody questBody) {
		this.questBody = questBody;
	}
	
	
}
