package web.handler.main;

import blservice.StockInfoBlService.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LENOVO on 2017/6/5.
 */

@Controller
@RequestMapping("/HomePage")
public class main{

    @Autowired
    private StockInfoService stockInfoService;

    @RequestMapping(value = "/ToHomePage",method = RequestMethod.GET)
    public String toHomePage(){
        
        return "HomePage";
    }

}
