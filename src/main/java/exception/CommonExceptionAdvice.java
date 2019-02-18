package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class CommonExceptionAdvice
{
    @ExceptionHandler(Exception.class)
    @RequestMapping("error/error-common")
    public String common(Exception e, Model model)
    {
        Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
        logger.info(e.toString());
        model.addAttribute("exception", e);
        return "error/error-common";
    }
}
