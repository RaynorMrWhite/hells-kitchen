package wssr.smgl.stfu.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wssr.smgl.stfu.model.Rezept;
import wssr.smgl.stfu.model.User;
import wssr.smgl.stfu.model.Zutat;
import wssr.smgl.stfu.service.DaoException;
import wssr.smgl.stfu.service.RezeptDAO;
import wssr.smgl.stfu.service.ZutatDAO;

@Controller
@RequestMapping("/rezept")
public class RezeptController {
    private static Logger log = LogManager.getLogger(ZutatController.class);
    @Autowired
    private RezeptDAO rezeptDAO;
    @Autowired
    private ZutatDAO zutatDAO;


    /**
     * <p> Rezept form request.</p>
     * <p>Expected HTTP GET and request '/edit'.</p>
     * @return
     */
    @RequestMapping(value="/edit", method=RequestMethod.GET)
        public ModelAndView edit(@RequestParam(required=false) Long id,
                                 @RequestParam(required=false) String rezeptName){
            log.info("edit " + id + " " + rezeptName);
            ModelAndView mv = new ModelAndView();
            if (rezeptName != null) {
                mv.addObject(rezeptDAO.findByName(rezeptName));
            } else if (id == null) {
                mv.addObject(new Rezept());
            } else {
                mv.addObject(rezeptDAO.findById(id));
            }
            mv.setViewName("edit_rezept");
            return mv;
        }

    /**
     * <p>Saves a {@link Rezept}.</p>
     *
     * <p>Expected HTTP POST and request '/save'.</p>
     * @return
     */
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public ModelAndView save(Rezept rezept) {
        rezeptDAO.save(rezept);
        return new ModelAndView("redirect:findAll");
    }

    /**
     * <p>Searches all Rezepte and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/findAll'.</p>
     */
    @RequestMapping(value="/findAll", method=RequestMethod.GET)
        public ModelAndView findAll() {
            ModelAndView mv = new ModelAndView();
            mv.addObject("title", "Alle Rezepte");
            mv.addObject("message", "Hier findest du alle Rezepte:");
            mv.addObject("rezepte", rezeptDAO.getAllrezepte());
            mv.addObject("edit", true);
            mv.setViewName("list_rezepte");
            return mv;
        }

        /**
         * <p> Lists all verfuegbare Rezepte </p>
         * <p>Expected HTTP GET and request '/findAll'.</p>
         */

    @RequestMapping(value="/findVerfuegbare", method=RequestMethod.GET)
    public ModelAndView findVerfuegbare(User user) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "Kochbare Rezepte");
        mv.addObject("message", "Hier findest du alle Rezepte, die du mit deinen verfügbaren Zutaten kochen kannst:");
        mv.setViewName("list_verfuegbare");
        return mv;
    }

    /**
     * <p>Searches for all free Rezepte and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/freieRezepte'.</p>
     */
    @RequestMapping(value="/freieRezepte", method=RequestMethod.GET)
    public ModelAndView freieRezepte() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "Rezept zusammenstellen");
        mv.addObject("message", "Folgende Rezept sind für ein Zusammenstellung verfügbar. Wählen Sie ein Rezept aus:");
        mv.addObject("rezepte", rezeptDAO.getAllrezepte());
        mv.addObject("edit", false);
        mv.setViewName("list_rezepte");
        return mv;
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public ModelAndView delete(@RequestParam(required=true) Long id) {
        try {
            rezeptDAO.delete(id);
            return new ModelAndView("redirect:findAll");
        } catch (DaoException e) {
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", e.getMessage());
            return mv;
        }
    }
}
