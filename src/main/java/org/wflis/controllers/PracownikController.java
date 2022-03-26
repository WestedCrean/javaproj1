package org.wflis.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.wflis.beans.Pracownik;
import org.wflis.dao.PracownikDao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PracownikController {
    @Autowired
    PracownikDao dao; //wstrzyknięcie dao z pliku XML

    @RequestMapping("/addForm")
    public String showform(Model m){
        m.addAttribute("command", new Pracownik());
        return "addForm";
    }

    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("pr") Pracownik pr){
        dao.save(pr);
        return "redirect:/viewAll";
    }

    @RequestMapping("/viewAll")
    public String viewAll(Model m){
        List<Pracownik> list=dao.getAll();
        m.addAttribute("list",list);
        return "viewAll";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        Pracownik pr=dao.get(id);
        m.addAttribute("command",pr);
        return "editForm";
    }

    //  update Pracownik p based on data sent from form /edit/{id}
    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@ModelAttribute("pr") Pracownik pr){
        dao.update(pr);
        return "redirect:/viewAll";
    }

    // delete Pracownik based on path variable id
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        dao.delete(id);
        return "redirect:/viewAll";
    }
}
