package high.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestListener implements ServletRequestListener{

	private static final Logger LOG = LoggerFactory.getLogger(RequestListener.class);
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		LOG.info("*** request销毁 ***");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		StringBuffer requestURL = request.getRequestURL();
		LOG.info("*** request初始化  请求路径为："+requestURL+" ***");
	}

}
