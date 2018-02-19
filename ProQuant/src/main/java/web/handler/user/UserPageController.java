package web.handler.user;

import PO.user.UserData;
import VO.RequestResult;
import VO.UserVO.AccountPageTotalVO;
import VO.UserVO.StrategyRecordVO;
import VO.UserVO.UserOptionalStocksListVO;
import blservice.strategyBlService.StrategyService;
import blservice.userDataBlService.UserDataService;
import com.google.gson.JsonObject;
import dataservice.UserLogService;
import dataservice.UserTradeService;
import model.UserTradeSingal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import net.sf.json.JSONArray;
import web.handler.login.LoginHandler;


/**
 * Created by xiezhenyu on 2017/6/6.
 */
@Controller
@RequestMapping("/user")
public class UserPageController {
    private Logger logger = LoggerFactory.getLogger(UserPageController.class);

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private UserTradeService userTradeService;

    @RequestMapping(value = "/toUserCenter", method = RequestMethod.GET)
    public String toUserCenter(Model model, HttpServletRequest request){
//        RequestResult<AccountPageTotalVO> result = null;
//        try {
//            AccountPageTotalVO accountPageTotalVO = userDataService.getUserAccountData(username);
//            result = new RequestResult<AccountPageTotalVO>(true, accountPageTotalVO);
//        }catch (Exception e){
//            logger.error(e.getMessage(), e);
//            System.out.println("获得用户中心界面账户数据时抛出异常，请检查UserPageController.java");
//        }
        //界面数据获取逻辑
//        System.out.println("1:" + System.currentTimeMillis());
        String username = (String) request.getSession().getAttribute("username");
//        System.out.println("跳转到用户中心：         ******"+username);
        AccountPageTotalVO accountPageTotalVO = userDataService.getUserAccountData(username);
//        if(accountPageTotalVO!=null){
//            model.addAttribute("accountPageTotalVO", accountPageTotalVO);
//        }
        model.addAttribute("accountPageTotalVO", accountPageTotalVO);


//        System.out.println("2:" + System.currentTimeMillis());
        ArrayList<UserOptionalStocksListVO> userOptionalStocksListVOArrayList = userDataService.getUserOptionalStocks(username);
//        if(userOptionalStocksListVOArrayList!=null){
//            model.addAttribute("optionalStocks", userOptionalStocksListVOArrayList);
//        }
        model.addAttribute("optionalStocks", userOptionalStocksListVOArrayList);


//        System.out.println("3:" + System.currentTimeMillis());
        ArrayList<StrategyRecordVO> strategyRecordVOArrayList = userDataService.getStrategyRecordVO(username);
//        if(strategyRecordVOArrayList!=null){
//            model.addAttribute("strategyRecords", strategyRecordVOArrayList);
//        }
        model.addAttribute("strategyRecords", strategyRecordVOArrayList);

        return "UserCenterPage";
    }


    //ajax json
    @RequestMapping(value = "/deleteMyStock", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonObject deleteMyStock(HttpServletRequest request){
        String code = request.getParameter("code");
        String username = (String)request.getSession().getAttribute("username");
        ArrayList<String> deletedList = new ArrayList<>();
        //如果传下来的code为0，说明传下来的是一个list
        if(code.equals("0")){
            String[] list = request.getParameter("list").split(";");
            deletedList = new ArrayList<>(Arrays.asList(list));
            userDataService.deleteUserOptionalStocks(deletedList, username);
            System.out.println("要删除的自选股的列表大小为 ： " + deletedList.size());
        }else{
            deletedList.add(code);
            userDataService.deleteUserOptionalStocks(deletedList, username);
            System.out.println(code + "  " + username + "line83");
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleteSuccess", true);
        System.out.println("返回结果");
        return jsonObject;
    }


    @RequestMapping(value = "/sell", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonObject sell(HttpServletRequest request){
        String username = request.getParameter("username");
        String code = request.getParameter("stockCode");
        String num = request.getParameter("num");
        System.out.println("**************");
        System.out.println(username);
        System.out.println(code);
        System.out.println(num);
        System.out.println("**************");
        UserTradeSingal userTradeSingal = userTradeService.sell(username, code, Integer.parseInt(num));
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("singal", userTradeSingal.toString());
        return jsonObject;
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonObject log(HttpServletRequest request){
        String username = request.getParameter("username");
        int conDays = userLogService.signIn(username);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("conDays", conDays);
        return jsonObject;
    }

    @RequestMapping(value = "/testHasLog", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonObject testHasLog(HttpServletRequest request) {
        String username = request.getParameter("username");
//        System.out.println("检查是否签到" + username);
        boolean hasLog = userLogService.hasSigned(username);
//        System.out.println(hasLog);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hasLog", hasLog);
        return  jsonObject;
    }


    @RequestMapping(value = "alterUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonObject modifyPassword(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserData userData = new UserData(username, password);
        boolean result = userLogService.alterUserInfo(userData);
        System.out.println("修改密码" + result + "修改密码");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("r", result);
        return jsonObject;
    }

}
