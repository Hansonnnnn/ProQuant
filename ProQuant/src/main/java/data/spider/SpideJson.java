package data.spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONObject;

public class SpideJson {

public static void main(String[] args) {
	String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36 OPR/45.0.2552.812";

	try {
		Document document =  Jsoup.connect("http://q.10jqka.com.cn/api.php?t=indexflash&").header("User-Agent",userAgent).timeout(3000).get();
		String jsonstr = document.body().text();

		JSONObject json = JSONObject.fromObject(jsonstr);
		System.out.println(json);
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
