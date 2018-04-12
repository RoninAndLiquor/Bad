package high.main;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalDefaultExceptionHandle {

	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView formatErrorHandler(HttpServletRequest req,Exception e){
		ModelAndView model = new ModelAndView();
		model.addObject("url", req.getRequestURI());
		model.addObject("exception", e);
		model.addObject("error", "数字转换异常");
		model.setViewName(DEFAULT_ERROR_VIEW);
		return model;
	}
	
}
