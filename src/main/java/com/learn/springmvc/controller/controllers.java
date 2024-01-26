package com.learn.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.learn.springmvc.dao.EntryDAO;
import com.learn.springmvc.entity.EntryEntity;

@Controller
public class controllers {
	
	@Autowired
	private EntryDAO entryDAO;

	@RequestMapping("/home")
	public String home(Model model)
	{
		List<EntryEntity> list =entryDAO.getAllEntries();
		model.addAttribute("entry", list);
		model.addAttribute("function", "home");
		return "home";
	}
	@RequestMapping(value="/add")
	public String add()
	{
		return "add";
	}
	@RequestMapping(value="/addEntry", method=RequestMethod.POST)
	public RedirectView addNotes(@ModelAttribute EntryEntity entry,HttpServletRequest request, ModelMap model)
	{
		System.out.println(entry);
		entryDAO.insertEntry(entry);
		RedirectView rv= new RedirectView();
		rv.setUrl(request.getContextPath()+"/home");
		return rv;
	}
	@RequestMapping(value="/modify")
	public String modify(Model model)
	{
		List<EntryEntity> list=entryDAO.getAllEntries();
		model.addAttribute("entry", list);
		return "modify";
	}
	@RequestMapping(value="/modifyItem")
	public String modifyItemjsp()
	{
		return "modifyItem";
	}
	@RequestMapping("/modifyItem/{id}")
	public String modifyItem(@PathVariable("id") int id,HttpServletRequest request,Model model)
	{
		EntryEntity entry=entryDAO.getEntry(id);
		model.addAttribute("entry", entry);
		return "modifyItem";
	}
	@RequestMapping("/delete/{id}")
	public RedirectView deleteItem(@PathVariable("id") int id,HttpServletRequest request,Model model)
	{
		System.out.println(id);
		entryDAO.deleteEntry(id);
		RedirectView rv= new RedirectView();
		rv.setUrl(request.getContextPath()+"/home");
		return rv;
	}
}
