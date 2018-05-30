package protocolPackage.common;

public class CastValue {

	public static final int preFixStart = 0x77aa77aa;
	public static final int preFixEnd = 0x77ab77ab;
												//77AB77AB77AA77AA
	public static final long egaofenPreFixEnd = 8623117489861719978L;
	
	public static final int egaofenRevFixEnd = 0x99ab99ab;
	
	public static byte[] getEgaofenReserveFixEnd()
	{
		return  TransUtil.int32ToByte4LittleEndian(egaofenRevFixEnd);//TransUtil.int32ToByte4LittleEndian(egaofenPreFixEnd);
	}
	public static byte[] getEgaofenPreFixEnd()
	{
		return  TransUtil.int64ToByte4LittleEndian(egaofenPreFixEnd);//TransUtil.int32ToByte4LittleEndian(egaofenPreFixEnd);
	}
	public static byte[] getFixStartByte()
	{
		return TransUtil.int32ToByte4BigEndian(preFixStart);
	}
	public static byte[] getFixEndByte()
	{
		return TransUtil.int32ToByte4BigEndian(preFixEnd);
	}
}
