package protocolPackage.ys;

/**
 * 宇视数据库偏移量
 * 项目名称:nettySupport
 * 类型名称:YSInformationReadPosition
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年4月25日
 * @version:
 */
public class YSInformationReadPosition {

	/**
	 * 包头偏移 固定开始【偏移0】 ,长度4字节
	 */
	public static final int packageStartOffset = 0;
	/**
	 * 数据包长度偏移 【包头偏移+4】 ,长度4字节
	 */
	public static final int packagelengthOffset = packageStartOffset +4;
	/**
	 * 数据包协议版本偏移 【数据包长度偏移+4】 ,长度4字节
	 */
	public static final int packageVersionOffset = packagelengthOffset+4;
	/**
	 * 命令码偏移 【数据包协议版本偏移+4】 ,长度4字节
	 */
	public static final int packageCommandCodeOffset = packageVersionOffset+4;
	/**
	 * 数据内容开始偏移【命令码偏移+4】 ,长度4字节
	 */
	public static final int packageDataInfoOffset = packageCommandCodeOffset+4;
	
	
}
