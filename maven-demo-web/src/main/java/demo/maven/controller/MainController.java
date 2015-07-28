

package demo.maven.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value="/jMobile/index", produces = "text/html;charset=UTF-8")
    public String jMobileIndex(){
        return "jMobile/index";
    }

    @RequestMapping("/testJson")
    @ResponseBody
    public Map<String, String> testJson(){
    	Map<String, String> result = new HashMap<String, String>();
    	result.put("name", "58陪练");
    	result.put("address", "望京·北京香颂223号楼204室");
    	
    	return result;
    }
    
    @RequestMapping(value="/bstrap/index", produces = "text/html;charset=UTF-8")
    public String bootstrap(){
        return "bootstrap/index";
    }
}

