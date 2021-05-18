package wssr.smgl.stfu.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private static final Logger log = LogManager.getLogger(HomeController.class);
    @Autowired
    /**
     * Custom handler for the welcome view.
     * Note that this handler relies on the RequestToViewNameTranslator to
     * determine the logical view name based on the request URL: "/home"
     * @return View name "home" selecting view "/view/home.jsp"
     */
    @RequestMapping("home")
    public ModelAndView home() {
        log.info("Controller für die Methode home");
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "Message");
        mv.setViewName("home");;
        return mv;
    }

}