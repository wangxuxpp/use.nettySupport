package use.com.nettySupport.initial;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import use.com.nettySupport.server.QJSignalServer;
import use.common.security.BaseInfo;
import use.common.util.propertyFile.ReadPropertyValue;

public class InitialSocket implements ServletContainerInitializer{

	protected final Logger log = LoggerFactory.getLogger(QJSignalServer.class);
	
	
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		// TODO Auto-generated method stub
		if (!(BaseInfo.getSecurityInfo().getInfo() instanceof Boolean))
		{
			return ;
		}
		log.info(SocketUtil.title+"读取配置参数");
		
    	Properties prop = new Properties();   
    	URL uPath = this.getClass().getResource("/"); 
    	InputStream in = null;
    	try 
    	{   
    		File f = new File(uPath.getFile() , "socket.properties");
    		if(!f.exists())
    		{
    			log.info(SocketUtil.title+"服务配置文件不存在。");
    			return;
    		}
    		in = new FileInputStream(f);
    		prop.load(in);
    		//奇骏信号机
    		SocketUtil.getQJSignalParameter().setEnable(ReadPropertyValue.getBoolean(prop, "qjsignal.enable", false));
    		SocketUtil.getQJSignalParameter().setPort(ReadPropertyValue.getInt(prop, "qjsignal.port", 7797));
    		SocketUtil.getQJSignalParameter().setReadIdle(ReadPropertyValue.getInt(prop, "qjsignal.readIdle", 5));
    		//前台flash通信
    		SocketUtil.getFlashParameter().setEnable(ReadPropertyValue.getBoolean(prop, "flashSocket.enable", false));
    		SocketUtil.getFlashParameter().setPort(ReadPropertyValue.getInt(prop, "flashSocket.port", 7788));	
    		SocketUtil.getFlashParameter().setCnnIp(ReadPropertyValue.getStr(prop, "flashSocket.Ip", "127.0.0.1"));
    		SocketUtil.getFlashParameter().setEnableSkipHead(ReadPropertyValue.getBoolean(prop, "flashSocket.enableSkipHead", false));
    		SocketUtil.getFlashParameter().setReadIdle(ReadPropertyValue.getInt(prop, "flashSocket.readIdle", 5));
    		//宇视
    		SocketUtil.getYSBayontParameter().setEnable(ReadPropertyValue.getBoolean(prop, "ysBayonet.enable", false));
    		SocketUtil.getYSBayontParameter().setPort(ReadPropertyValue.getInt(prop, "ysBayonet.port", 7797));
    		SocketUtil.getYSBayontParameter().setReadBufferSize(ReadPropertyValue.getInt(prop, "ysBayonet.maxBuffer", 10));
    		SocketUtil.getYSBayontParameter().setReadIdle(ReadPropertyValue.getInt(prop, "ysBayonet.readIdle", 5));
    		//易高分
    		SocketUtil.getEgaofenParameter().setEnable(ReadPropertyValue.getBoolean(prop, "eGaoFen.enable", false));
    		SocketUtil.getEgaofenParameter().setPort(ReadPropertyValue.getInt(prop, "eGaoFen.port", 6196));
    		SocketUtil.getEgaofenParameter().setMaxBuffer(ReadPropertyValue.getInt(prop, "eGaoFen.maxBuffer", 1));
    		SocketUtil.getEgaofenParameter().setReadIdle(ReadPropertyValue.getInt(prop, "eGaoFen.readIdle", 5));
    		//巨仑环保项目
    		SocketUtil.getYPTongHandParameter().setEnable(ReadPropertyValue.getBoolean(prop, "YPTong.enable", false));
    		SocketUtil.getYPTongHandParameter().setPort(ReadPropertyValue.getInt(prop, "YPTong.port", 6565));
    		SocketUtil.getYPTongHandParameter().setReadBufferSize(ReadPropertyValue.getInt(prop, "YPTong.maxBuffer", 1));
    		SocketUtil.getYPTongHandParameter().setReadIdle(ReadPropertyValue.getInt(prop, "YPTong.readIdle", 5));
    		//flash沙箱处理
    		SocketUtil.getFlashPolicyParameter().setEnable(ReadPropertyValue.getBoolean(prop, "flashPolicy.enable", false));
    		SocketUtil.getFlashPolicyParameter().setPort(ReadPropertyValue.getInt(prop, "flashPolicy.port", 843));

    		
    	}catch(Exception er)
    	{
    		log.error(SocketUtil.title+"读取配置文件异常，异常原因:"+er.getMessage());
    	}
	}

}
