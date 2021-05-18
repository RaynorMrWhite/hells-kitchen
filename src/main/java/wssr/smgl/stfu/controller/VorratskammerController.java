package wssr.smgl.stfu.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import wssr.smgl.stfu.model.Vorratskammer;
import wssr.smgl.stfu.model.Zutat;
import wssr.smgl.stfu.service.DaoException;
import wssr.smgl.stfu.service.VorratskammerDAO;
import wssr.smgl.stfu.service.ZutatDAO;

@Controller
@RequestMapping("/vorratskammer")
public class VorratskammerController {
    private static Logger log = LogManager.getLogger(ZutatController.class);
    @Autowired
    private VorratskammerDAO vorratskammerDAO;


    /**
     * <p>Searches for all Zutaten in Vorratskammer and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/findAll'.</p>
     */
    @RequestMapping(value="/findAll", method= RequestMethod.GET)
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "Vorratskammer");
        mv.addObject("message", "Hier findest du alle deine verfügbaren Zutaten");
        mv.addObject("vorratskammer", vorratskammerDAO.findAll());
        mv.addObject("edit", true);
        mv.setViewName("list_vorratskammer");
        return mv;
    }

    /**
     * <p> Vorratskammer form request.</p>
     * <p>Expected HTTP GET and request '/edit'.</p>
     * @return
     */
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam(required=false) Long id,
                             @RequestParam(required=false) String zutatname,
                             @RequestParam(required=false) Integer mengeingramm ) {
        log.info("edit " + id + " " + zutatname + " " + mengeingramm);
        ModelAndView mv = new ModelAndView();
        if (zutatname != null) {
            mv.addObject(vorratskammerDAO.findByName(zutatname));
        }else if (id == null) {
            mv.addObject(new Vorratskammer());
        } else {
            mv.addObject(vorratskammerDAO.findById(id));
        }
        mv.setViewName("edit_vorratskammer");
        return mv;
    }

    /**
     * <p>Saves a {@link Vorratskammer}.</p>
     *
     * <p>Expected HTTP POST and request '/save'.</p>
     * @return
     */
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String save(Vorratskammer vorratskammer) {
        vorratskammerDAO.save(vorratskammer);
        return "redirect:findAll";
    }

    /**
     * <p>Deletes a Zutat in Vorratskammer.</p>
     * <p>Expected HTTP GET and request '/delete'.</p>
     */
    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public ModelAndView delete(@RequestParam(required=true) Long id) {
        try {
            vorratskammerDAO.delete(id);
            return new ModelAndView("redirect:findAll");
        } catch (DaoException e) {
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", e.getMessage());
            return mv;
        }
    }

}

