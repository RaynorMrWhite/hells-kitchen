package wssr.smgl.stfu.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wssr.smgl.stfu.model.Zutat;
import wssr.smgl.stfu.service.ZutatDAO;
import wssr.smgl.stfu.service.DaoException;

@Controller
@RequestMapping("/zutat")
public class ZutatController {
    private static Logger log = LogManager.getLogger(ZutatController.class);
    @Autowired
    private ZutatDAO zutatDAO;


    /**
     * <p>Controller to edit a {@link Zutat} object. If id == null, an empty form is presented.</p>
     * @param id of a {@link Zutat}
     * @return a ModelAndView object to be used by view "edit-person"
     */
    @RequestMapping(value="/edit", method=RequestMethod.GET)
        public ModelAndView edit(@RequestParam(required=false) Long id,
                                 @RequestParam(required=false) String name,
                                 @RequestParam(required=false) Integer mengeingramm ) {
            log.info("edit " + id + " " + name + " " + mengeingramm);
            ModelAndView mv = new ModelAndView();
            if (name != null) {
                mv.addObject(zutatDAO.findByName(name));
            } else if (id == null) {
                mv.addObject(new Zutat());
            } else {
                mv.addObject(zutatDAO.findById(id));
            }
            mv.setViewName("edit_zutat");
            return mv;
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String save(Zutat zutat) {
        zutatDAO.save(zutat);
        return "redirect:findAll";
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public ModelAndView delete(@RequestParam(required=true) Long id) {
        try {
            zutatDAO.delete(id);
            return new ModelAndView("redirect:findAll");
        } catch (DaoException e) {
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", e.getMessage());
            return mv;
        }
    }

    @RequestMapping(value="/findAll", method=RequestMethod.GET)
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "Alle Rezepte");
        mv.addObject("message", "Hier findest du alle Rezepte:");
        mv.addObject("zutaten", zutatDAO.getAllZutaten());
        mv.addObject("edit", true);
        mv.setViewName("list_zutaten");
        return mv;
    }
}
