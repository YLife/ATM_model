package config;

import interfaces.Dao.CardInfoDao;
import interfaces.Dao.TradeInfoDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import biz.BankBiz;

/**
 * ���������ļ���Ϣ����ͨ�����似����̬�õ����ʵ��
 * @author YangLun
 *
 */
public class ApplicationContext {
	//�����ļ���Ϣ
	private static Map<String, String> config = new HashMap<String, String>();
	
	//��ȡ�����ļ���Ϣ
	static{
		SAXReader reader = new SAXReader();
		try {
			//tomcat��Ŀ¼
			String catalina_base = System.getProperty("catalina.base");
			//�����ļ�applicationConfig.xml�ĸ�Ŀ¼
			String path = catalina_base+"/webapps/bs_bank/WEB-INF/classes/ApplicationConfig.xml";
			Document doc = reader.read(path);
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			for (Element element : list) {
				config.put(element.attributeValue("id"), element.attributeValue("impl"));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	//���·���ͨ�����似����̬�õ����ʵ��
	
	public static BankBiz getBankBiz(){
		String className = config.get("bankBiz");
		try {
			Object obj = Class.forName(className).newInstance();
			return (BankBiz) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static CardInfoDao getCardInfoDao(){
		String className = config.get("cardInfoDao");
		try {
			Object obj = Class.forName(className).newInstance();
			return (CardInfoDao) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static TradeInfoDao getTradeInfoDao(){
		String className = config.get("tradeInfoDao");
		try {
			Object obj = Class.forName(className).newInstance();
			return (TradeInfoDao) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}