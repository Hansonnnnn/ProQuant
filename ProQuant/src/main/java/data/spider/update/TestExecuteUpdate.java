package data.spider.update;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TestExecuteUpdate {

	public static void main(String[] args) {
		TestExecuteUpdate testExecuteUpdate = new TestExecuteUpdate();
		
		try {
			String path = java.net.URLDecoder.decode(testExecuteUpdate.getClass().getResource("/").getPath().substring(1),"utf-8");
			System.out.println(path);
			System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void test() {
		String path = null;
		try {
			path = java.net.URLDecoder.decode(new CurrentStockDataUpdateSpiderByPython().getClass().getClassLoader().getResource("").getPath().substring(1),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String command = "python "+path+"pythonSpider/getCurrentData.py";
		try {
			//Runtime.getRuntime().exec(command);
			
			String ls_1; 
			Process process = Runtime.getRuntime().exec(command); 
		/*	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream())); 
			while ( (ls_1=bufferedReader.readLine()) != null) 
			System.out.println(ls_1);*/
			process.waitFor(); 
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Stock run=="+command);
	}
}
