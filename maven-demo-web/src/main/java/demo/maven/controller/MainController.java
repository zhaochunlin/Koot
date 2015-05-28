

package demo.maven.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import demo.maven.configuration.SystemWebSocketHandler;

/**
 * ClassName:MainController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年2月28日 下午2:29:34 <br/>
 * @author chunlin.zhao
 * @version
 * @since 1.0
 * @see
 */

@Controller
public class MainController {

	@Resource(name = SystemWebSocketHandler.DINAME)
	SystemWebSocketHandler msgHandler;
	
    /**
     * 首页
    * main:(这里用一句话描述这个方法的作用). <br/>
    * TODO(这里描述这个方法适用条件 – 可选).<br/>
    * TODO(这里描述这个方法的执行流程 – 可选).<br/>
    * TODO(这里描述这个方法的使用方法 – 可选).<br/>
    * TODO(这里描述这个方法的注意事项 – 可选).<br/>
    *
    * @author chunlin.zhao
    * @return
    * @since 1.0
     */
    
    @RequestMapping("/index")
    public String main(){
        return "welcome";
    }
    
    /**
     * jquery mobile 首页
     * 
    * jMobileIndex:(这里用一句话描述这个方法的作用). <br/>
    * TODO(这里描述这个方法适用条件 – 可选).<br/>
    * TODO(这里描述这个方法的执行流程 – 可选).<br/>
    * TODO(这里描述这个方法的使用方法 – 可选).<br/>
    * TODO(这里描述这个方法的注意事项 – 可选).<br/>
    *
    * @author chunlin.zhao
    * @return
    * @since 1.0
     */
    @RequestMapping("/jMobile/index")
    public String jMobileIndex(){
        return "jMobile/index";
    }
    /**
     * 
    * wscoketIndex:WebSocket. <br/>
    * TODO(这里描述这个方法适用条件 – 可选).<br/>
    * TODO(这里描述这个方法的执行流程 – 可选).<br/>
    * TODO(这里描述这个方法的使用方法 – 可选).<br/>
    * TODO(这里描述这个方法的注意事项 – 可选).<br/>
    *
    * @author chunlin.zhao
    * @return
    * @since 1.0.0
    * @history
     */
    @RequestMapping("/wsocket/index")
    public String wscoketIndex(){
        return "wsocket";
    }
    

    @RequestMapping("/wsocket/brocast")
    @ResponseBody
    public Map<String, String> sendToAllUsers(){
    	msgHandler.sendMessageToUsers(new TextMessage("广播测试消息-"+ new Random().nextInt(9899999)));
    	
    	Map<String, String> model = new HashMap<String, String>();
    	model.put("code", "OK");
    	return model;
    }
}

