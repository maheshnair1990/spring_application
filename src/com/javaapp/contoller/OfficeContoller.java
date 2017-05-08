package com.javaapp.contoller;

import com.javaapp.beans.OfficeBean;
import com.javaapp.model.OfficeModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OfficeContoller {

    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {
        return new ModelAndView("welcome", "office", new OfficeBean());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("office") OfficeBean office, ModelMap model) {
        /*Call to create Office Method*/
        new OfficeModel().createOffice(office);
        return "edit";
    }

    @RequestMapping("/enlist")
    public ModelAndView enlist() {
        List<OfficeBean> list = new OfficeModel().getOffices();
        return new ModelAndView("ShowOffice", "list", list);
    }

    @RequestMapping(value = "/editoff/{id}")
    public ModelAndView edit(@PathVariable String id) {
        OfficeBean offBean = new OfficeBean();
        offBean = new OfficeModel().getOffice(id);
        return new ModelAndView("editOffice", "command", offBean);
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editsave(@ModelAttribute("offBean") OfficeBean off) {
        new OfficeModel().update(off);
        return new ModelAndView("redirect:/enlist");
    }

    @RequestMapping(value = "/deleteoff/{id}")
    public ModelAndView deleteoff(@PathVariable String id) {
        new OfficeModel().delete(id);
         return new ModelAndView("redirect:/enlist");
    }
    
     @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam String code) {
        // yourValue contain the value post from the html form
        String code1=code;
        System.out.println(code1);
        OfficeBean offBean=new OfficeModel().getOffice(code);
        List<OfficeBean> list=new ArrayList<>();
        list.add(offBean);
        return new ModelAndView("ShowOffice", "list", list);
    }

}
