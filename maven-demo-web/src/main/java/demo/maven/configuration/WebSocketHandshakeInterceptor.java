package demo.maven.configuration;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import demo.maven.Constants;

/**
 * ClassName:WebSocketHandshakeInterceptor <br/>
 * Function: 主要作用是取得当前请求中的用户名，并且保存到当前的WebSocketHandler中，以便确定WebSocketHandler所对应的用户，具体可参考HttpSessionHandshakeInterceptor <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年5月28日 下午3:15:43 <br/>
 * 
 * @author chunlin.zhao
 * @version 1.0.0
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	private static Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);

	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest()
					.getSession(false);
			if (session != null) {
				// 使用userName区分WebSocketHandler，以便定向发送消息
				String userName = (String) session
						.getAttribute(Constants.SESSION_USERNAME);
				if(null != userName) attributes.put(Constants.WEBSOCKET_USERNAME, userName);
			}
		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {

	}
}
