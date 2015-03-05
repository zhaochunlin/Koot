

package demo.maven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/jMobile/index")
    public String jMobileIndex(){
        return "jMobile/index";
    }
}

