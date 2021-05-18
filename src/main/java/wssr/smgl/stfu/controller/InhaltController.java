package wssr.smgl.stfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import wssr.smgl.stfu.service.DaoException;
import wssr.smgl.stfu.service.InhaltDAO;
import wssr.smgl.stfu.service.RezeptDAO;
import wssr.smgl.stfu.service.ZutatDAO;

@Controller
@RequestMapping("/inhalt")
public class InhaltController {
    @Autowired
    private InhaltDAO inhaltDAO;
    @Autowired
    private ZutatDAO zutatDAO;
    @Autowired
    private RezeptDAO rezeptDAO;

    /**
     * @param id der Primärschlüssel des {@link wssr.smgl.stfu.model.Rezept}
     * @return
     */
    @RequestMapping(value="/erstelle", method= RequestMethod.GET)
    public ModelAndView erstelle(@RequestParam(required=true) Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "Rezept eintragen");
        mv.addObject("message", "Tragen Sie hier die Zutaten des Rezepts ein.");
        mv.addObject("rezept", rezeptDAO.findById(id));
        mv.addObject("zutaten", zutatDAO.findAll());

        mv.setViewName("edit_inhalt");
        return mv;
    }
    /**
     * <p>Saves a Inhalt.</p>
     *
     * <p>Expected HTTP POST and request '/save'.</p>
     * @param id of a Rezept
     * @param anzahlZutaten array of ids of Zutaten objects.
     * @return
     */
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public ModelAndView save(@RequestParam(required=true) Long id, Long[] anzahlZutaten) {
        try {
            inhaltDAO.erstelle(id, anzahlZutaten);
            return new ModelAndView("redirect:../rezept/findAll");
        } catch (DaoException e) {
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", e.getMessage());
            mv.setViewName("list_rezepte");
            return mv;
        }
    }

    /**
     * <p>Searches for all {@link wssr.smgl.stfu.model.Inhalt} objects and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/findAll'.</p>
     */
    @RequestMapping(value="/findAll", method=RequestMethod.GET)
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "Rezepte");
        mv.addObject("message", "Alle Rezepte.");
        mv.addObject("rezept", inhaltDAO.findAll());
        mv.setViewName("list_rezepte");
        return mv;
    }
}
