

package demo.maven.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * ClassName:SpringWebAppInitializer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年5月28日 下午5:24:35 <br/>
 * @author chunlin.zhao
 * @version 1.0.0
 */
public class SpringWebAppInitializer implements WebApplicationInitializer {

	
	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
	*/
	
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		
		// TODO Auto-generated method stub
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocation("/WEB-INF/spring-mvc.xml");
		
		ServletRegistration.Dynamic dispatcher = container.addServlet("appServlet", new DispatcherServlet(appContext));
		dispatcher.setAsyncSupported(true);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

}

