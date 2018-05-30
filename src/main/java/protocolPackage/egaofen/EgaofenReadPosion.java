package protocolPackage.egaofen;

/**
 * 
 * 项目名称:nettySupport
 * 类型名称:EgaofenReadPosion
 * 类型描述:易高分包体位置
 * 作者:wx
 * 创建时间:2017年9月14日
 * @version:
 */
public class EgaofenReadPosion {
	/**
	 * 命令头 包头偏移 固定开始【偏移0】 ,长度4字节
	 */
	public static final int commandPostion = 0;
	/**
	 * 包体大小【偏移4】 ,长度4字节
	 */
	public static final int packageSizePostion = commandPostion + 4;
	
	public static final int paperIdPostion = packageSizePostion + 4;
	public static final int questionIdPostion = paperIdPostion + 4;
	public static final int questionSectionIdPostion = questionIdPostion + 4;
	public static final int studenSNPostion = questionSectionIdPostion + 4;
}
