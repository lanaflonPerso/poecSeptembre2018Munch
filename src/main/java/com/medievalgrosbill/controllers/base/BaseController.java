package com.medievalgrosbill.controllers.base;

import java.util.List;

import com.medievalgrosbill.database.DBItem;
import com.medievalgrosbill.dtos.base.BaseDeleteCriteriaDTO;
import com.medievalgrosbill.services.base.BaseService;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class BaseController<T extends DBItem> {
	
	private static final String BASE_ATTRIBUT_LIST = "items";
	private static final String BASE_ATTRIBUT = "item";
	
	protected abstract BaseService<T> getBaseService();
	protected abstract String getBaseURL();
	protected abstract String getBasePageName();
	protected abstract void setOtherAttributes(Model model);
	protected abstract void setupOtherFields(T item);

	@RequestMapping(value = {"","/","/index"}, method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute(BASE_ATTRIBUT_LIST,this.getBaseService().findAll());
		model.addAttribute("pageName",this.getBasePageName()+" index");
		model.addAttribute("detailPath",this.getBaseURL());

		return this.getBaseURL()+"/index";
	}

    @RequestMapping(value = {"/delete/{id}"}, method=RequestMethod.GET)
    public String deleteId(Model model, @PathVariable Integer id) {
        this.getBaseService().deleteById(id);
        return "redirect:"+this.getBaseURL()+"/index";
    }

    @RequestMapping(value = {"/delete"}, method=RequestMethod.GET)
    public String deleteCriteria(Model model) {
        model.addAttribute("pageName",this.getBasePageName()+" deletion criteria");
        model.addAttribute("detailPath",this.getBaseURL());
        return this.getBaseURL()+"/delete";
    }

    @RequestMapping(value = {"/delete"}, method=RequestMethod.POST)
    public String deleteCriteriaSearch(Model model, @ModelAttribute T item) {
        List<T> items = this.getBaseService().findWithCriteria(item);
        if (items.size() > 0) {
            model.addAttribute(BASE_ATTRIBUT_LIST,items);
        }else {
            model.addAttribute("notFound","No match");
        }

        model.addAttribute("pageName",this.getBasePageName()+" deletion criteria");
        model.addAttribute("detailPath",this.getBaseURL());
        return this.getBaseURL()+"/delete";
    }

    @RequestMapping(value = {"/deletecriteria"}, method=RequestMethod.POST)
    public String deleteCriteriaDelete(@ModelAttribute BaseDeleteCriteriaDTO<T> form) {
        this.getBaseService().delete(form.getItems());
        return "redirect:"+this.getBaseURL()+"/delete";
    }

    @RequestMapping(value = {"/find"}, method=RequestMethod.GET)
    public String findCriteria(Model model) {
        model.addAttribute("pageName",this.getBasePageName()+" find criteria");
        model.addAttribute("detailPath",this.getBaseURL());
        return this.getBaseURL()+"/find";
    }

    @RequestMapping(value = {"/find"}, method=RequestMethod.POST)
    public String findCriteriaSearch(Model model, @ModelAttribute T item) {
        List<T> roles = this.getBaseService().findWithCriteria(item);
        if (roles.size() > 0) {
            model.addAttribute(BASE_ATTRIBUT_LIST,roles);
        }else {
            model.addAttribute("notFound","No match");
        }

        model.addAttribute("pageName",this.getBasePageName()+" find criteria");
        model.addAttribute("detailPath",this.getBaseURL());
        return this.getBaseURL()+"/find";
    }

    @RequestMapping(value = {"/edit"}, method=RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("pageName",this.getBasePageName()+" create");
        model.addAttribute("detailPath",this.getBaseURL());
        return this.getBaseURL()+"/edit";
    }

    @RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.GET)
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute(BASE_ATTRIBUT,this.getBaseService().find(id).get());
        model.addAttribute("pageName",this.getBasePageName()+" edit");
        model.addAttribute("detailPath",this.getBaseURL());
        return this.getBaseURL()+"/edit";
    }

    @RequestMapping(value = {"/edit"}, method=RequestMethod.POST)
    public String editSave(@ModelAttribute T item) {
        this.getBaseService().save(item);
        return "redirect:"+this.getBaseURL()+"/index";
    }

    @RequestMapping(value = {"/edit"}, method=RequestMethod.DELETE)
    public String editDelete() {
        return "redirect:"+this.getBaseURL()+"/index";
    }
    
    @RequestMapping(value = {"/register"}, method=RequestMethod.GET)
    public String register() {
        return "/register";
    }
}
