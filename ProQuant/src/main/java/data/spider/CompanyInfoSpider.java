package data.spider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import DAO.dao.InfoDataDao;
import PO.InfoData;

@Component("InfoSpider")
public class CompanyInfoSpider {
	
	@Autowired
	InfoDataDao infoDataDao;
	@Autowired
	SessionFactory sessionFactory;
	
	
	
	public static final String[] URL=
		{"http://stockpage.10jqka.com.cn/",
		 "/company/"};
	
	public InfoData spide(InfoData infoData) {
		String url = URL[0]+infoData.getCode()+URL[1];
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";
		try {
			Document document =  Jsoup.connect(url).header("User-Agent",userAgent).timeout(3000).get();
			Elements elements = document.select("table[class=m_table]");
			Elements spans = elements.first().select("span");
			
			infoData.setCpName(spans.get(0).text());
			infoData.setCpProvince(spans.get(1).text());
			infoData.setCpEnName(spans.get(2).text());
			if (!spans.get(3).text().equals("-")) {
				infoData.setCName(spans.get(3).text().substring(0,4));
			}
			
			infoData.setCpWebsite(spans.get(5).text());
			
			elements = document.select("table[class=m_table ggintro]");
			spans = elements.first().select("span");
			infoData.setCpBusiness(spans.get(0).text());
			infoData.setCpAddress(spans.last().text());
			
			Element p = elements.first().select("p").first();
			infoData.setCpInfo(p.text());
			return infoData;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		File file = new File("C:/Users/凡/Desktop/error.txt");
		Writer writer = null; 
		try {
			writer = new OutputStreamWriter(new FileOutputStream(file,true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
		CompanyInfoSpider companyInfoSpider = (CompanyInfoSpider) context.getBean("InfoSpider");
		Session session= companyInfoSpider.sessionFactory.openSession();
		List<InfoData> infoDatas = session.createQuery("from InfoData I where I.id>3155").list();
		for (InfoData infoData : infoDatas) {
			System.out.println(infoData.getId());
			if (infoData.getCpName()!=null) {
				continue;
			}
			InfoData newOne = companyInfoSpider.spide(infoData);
			if (newOne!=null) {
				Transaction tx = session.beginTransaction();
				session.update(newOne);
				tx.commit();
				
			}else {
				try {
					writer.write(infoData.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		session.close();
	/*	InfoData infoData = new InfoData();
		
		infoData.setCode("000012");
		
		new CompanyInfoSpider().spide(infoData);*/
	}
	
}
