package protocolPackage.common;

import io.netty.buffer.ByteBuf;
/**
 * 
 * 项目名称:use.nettySupport
 * 类型名称:TransUtil
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年12月13日
 * @version:
 */
public class TransUtil {

/**
 * 	大端模式，是指数据的高字节保存在内存的低地址中，而数据的低字节保存在内存的高地址中，这样的存储模式有点儿类似于把数据当作字符串顺序处理：地址由小向大增加，而数据从高位往低位放；这和我们的阅读习惯一致。
	小端模式，是指数据的高字节保存在内存的高地址中，而数据的低字节保存在内存的低地址中，这种存储模式将地址的高低和数据位权有效地结合起来，高地址部分权值高，低地址部分权值低。
 * @param bytes
 * @return
 */
	public static long ByteArrayToInt64LittleEndian(byte[] bytes)
	{
		long r = 0L;
		for (int i= 0 ; i<8 ; i++)
		{
			int s =(8-1-i) *8;
			r +=(bytes[i]&0x00000000000000FF) << s;
		}
		return r;
	}
	public static byte[] int64ToByte4LittleEndian(long number)
	{
		byte[] bytes = new byte[8];  
		bytes[7] = (byte) (number >>> 56);  
        bytes[6] = (byte) (number >>> 48);  
        bytes[5] = (byte) (number >>> 40);  
        bytes[4] = (byte) (number >>> 32);
        bytes[3] = (byte) (number >>> 24);  
        bytes[2] = (byte) (number >>> 16);  
        bytes[1] = (byte) (number >>> 8);  
        bytes[0] = (byte) number;
        return bytes;
	}
	/**
	 * 小端模式是指数据的低位保存在内存的低地址中，而数据的高位保存在内存的高地址中。
	 * @param number
	 * @return
	 */
	public static byte[] int32ToByte4LittleEndian(int number)
	{
		byte[] bytes = new byte[4];  
        bytes[3] = (byte) (number >>> 24);  
        bytes[2] = (byte) (number >>> 16);  
        bytes[1] = (byte) (number >>> 8);  
        bytes[0] = (byte) number;
        return bytes;
	}
	/**
	 *  小端模式是指数据的低位保存在内存的低地址中，而数据的高位保存在内存的高地址中。
	 * @param bytes
	 * @return
	 */
	public static int ByteArrayToInt32LittleEndian(byte[] bytes)
	{
		int r = 0;
		for (int i= 0 ; i<4 ; i++)
		{
			int s =(i) *8;
			r +=(bytes[i]&0x000000FF) << s;
		}
		return r;
	}
	public static int getInt32LittleEndian(ByteBuf data , int postion)
	{
		byte [] r = new byte[4];
		data.getBytes(postion, r);
		return ByteArrayToInt32LittleEndian(r);
	}
	/**
	 * 大端模式，是指数据的低位保存在内存的高地址中，而数据的高位，保存在内存的低地址中；
	 * @param number
	 * @return
	 */
	public static byte[] int32ToByte4BigEndian(int number)
	{
		byte[] bytes = new byte[4];  
        bytes[0] = (byte) (number >>> 24);  
        bytes[1] = (byte) (number >>> 16);  
        bytes[2] = (byte) (number >>> 8);  
        bytes[3] = (byte) number;
        return bytes;
	}
	/**
	 * 大端模式，是指数据的低位保存在内存的高地址中，而数据的高位，保存在内存的低地址中；
	 * @param bytes
	 * @return
	 */
	public static int ByteArrayToInt32BigEndian(byte[] bytes)
	{
		int r = 0;
		for (int i= 0 ; i<4 ; i++)
		{
			int s =(4-1-i) *8;
			r +=(bytes[i]&0x000000FF) << s;
		}
		return r;
	}
	public static int getInt32BigEndian(ByteBuf data , int postion)
	{
		byte [] r = new byte[4];
		data.getBytes(postion, r);
		return ByteArrayToInt32LittleEndian(r);
	}
}
