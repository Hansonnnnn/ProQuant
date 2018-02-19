package web.handler.login;

import PO.user.UserData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dataservice.UserLogService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/Login")
public class LoginHandler {

	@Autowired
	private UserLogService userLogService;

	public static boolean hasLogin;
	public static String userName;

	@RequestMapping(value = "/validate", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JsonObject validate(HttpServletRequest request) {
		String account = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println("验证执行到此处111111111111111！！！！！！！！！！！！");
		boolean logInSuccess = userLogService.logIn(account, password);
		if(logInSuccess){
			hasLogin = true;
			userName = account;
			request.getSession().setAttribute("username",userName);
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("logInSuccess", logInSuccess);
		System.out.println("验证执行到此处222222222222222！！！！！！！2222222222！！！！！");
		System.out.println(jsonObject);
		return jsonObject;
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JsonObject signup(HttpServletRequest request){
		System.out.println("@RequestMapping(value = /signUp");
		String name = request.getParameter("name");
		String userName = request.getParameter("username");
		this.userName = userName;
		String password = request.getParameter("password");
		System.out.println("验证执行到此处/signUp！！！！！！！11111111111111111！！！！！");
		System.out.println(userName);
		System.out.print(password);
		boolean signupSuccess = userLogService.signUp(new UserData(userName, password));
		System.out.println("验证执行到此处/signUp！！！！！！！！！！2222222222222222222！！");
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("signupSuccess", signupSuccess);
		System.out.println(signupSuccess);
		System.out.println("验证执行到此处/signUp！！！！！！！！！！33333333！！");
		return jsonObject;
	}

	@RequestMapping(value = "/testHasLogin", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JsonObject testHasLogin(){
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("hasLogin", hasLogin);
//		System.out.println("@RequestMapping(value = @RequestMapping(value = ");
		return jsonObject;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JsonObject logout(HttpServletRequest request){
		JsonObject jsonObject = new JsonObject();
		request.getSession().setAttribute("username", null);
		hasLogin = false;
		userName = "";
		jsonObject.addProperty("logoutResult", true);
		return jsonObject;
	}

	@RequestMapping(value = "/getCurrentUsername", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JsonObject getCurrentUser(){
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("username", userName);
		return jsonObject;
	}


}
