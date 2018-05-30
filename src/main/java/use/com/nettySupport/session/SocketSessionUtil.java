package use.com.nettySupport.session;

public class SocketSessionUtil {

	private static NetSession fQJNetSession = new NetSession();
	private static NetSession fFlashSession = new  NetSession();
	private static NetSession fYSBayonetSession = new NetSession();
	private static NetSession fEgaofenSession = new NetSession();
	
	private static NetSession fYPTongSession = new NetSession();
	
	public static NetSession flashSession()
	{
		return fFlashSession;
	}
	public static NetSession qjIOSession()
	{
		return fQJNetSession;
	}
	public static NetSession ysBayonetSession()
	{
		return fYSBayonetSession;
	}
	public static NetSession eGaoFenSession()
	{
		return fEgaofenSession;
	}
	public static NetSession getYPTongSession()
	{
		return fYPTongSession;
	}
}
