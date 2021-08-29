package com.Ammar.VM.controllers;

import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Ammar.VM.models.Item;
import com.Ammar.VM.models.Position;
import com.Ammar.VM.services.MainService;

@Controller
public class MainController {
	private final MainService mainServ;

	public MainController(MainService mainServ) {
		super();
		this.mainServ = mainServ;
	}
	@RequestMapping("/")
	public String drift() {
		return "redirect:/VendingMachine/home/";
	}
	@RequestMapping("/VendingMachine/home/")
	public String home(Model model,HttpSession session) {
		if(mainServ.allposition().size()==0) {
			mainServ.initiation(25);
		}
		if(session.getAttribute("count")!=null) {
			Integer number = (Integer) session.getAttribute("count");
			Position p=mainServ.findPositionByNumber(number);
			Item i=p.getItem();
			model.addAttribute("target",i);
		}
		
		
		model.addAttribute("itemss",mainServ.allposition().stream().map(p->p.getItem()).collect(Collectors.toList()));
		model.addAttribute("positions",mainServ.allposition());
		return "home.jsp";
	}
	@RequestMapping("/VendingMachine/home/{a}")
	public String para(Model model,@PathVariable("a") int a,HttpSession session) {
		model.addAttribute("first",a);
		if(mainServ.allposition().size()==0) {
			mainServ.initiation(25);
		}
		session.invalidate();
		model.addAttribute("itemss",mainServ.allposition().stream().map(p->p.getItem()).collect(Collectors.toList()));
		model.addAttribute("positions",mainServ.allposition());
		return "home.jsp";
	}
	@RequestMapping("/VendingMachine/home/{a}/{b}/")
	public String parab(Model model,@PathVariable("a") int a,@PathVariable("b") int b,HttpSession session) {
		model.addAttribute("first",a);
		model.addAttribute("second",b);
		if(mainServ.allposition().size()==0) {
			mainServ.initiation(25);
		}
		session.invalidate();
		model.addAttribute("itemss",mainServ.allposition().stream().map(p->p.getItem()).collect(Collectors.toList()));
		model.addAttribute("positions",mainServ.allposition());
		return "home.jsp";
		}
		
	@RequestMapping("/VendingMachine/home/{a}/{b}/{c}")
	public String parab(Model model,@PathVariable("a") int a,@PathVariable("b") int b,@PathVariable("c") int c,HttpSession session) {
		return "redirect:/VendingMachine/home/";	
		}
	@RequestMapping("/VendingMachine/add")
	public String vendor(Model model,@Valid@ModelAttribute("item") Item item,HttpSession session) {
		model.addAttribute("positions",mainServ.allposition());
		session.invalidate();
		model.addAttribute("itemss",mainServ.allposition().stream().map(p->p.getItem()).collect(Collectors.toList()));
		System.out.println(mainServ.allposition().stream().map(p->p.getItem()).collect(Collectors.toList()));
//		System.out.println(mainServ.findPositionByNumber(1).getItem().getName());

		return "index.jsp";
	}
	@RequestMapping(value="/adding/{number}",method=RequestMethod.POST)
	public String create(@PathVariable("number") int number,@Valid@ModelAttribute("item") Item item,BindingResult result) {
		if(result.hasErrors()) return "redirect:/VendingMachine/add";
		mainServ.creatitem(item,number);
		System.out.println(mainServ.findPositionByNumber(number).getItem().getName());
		return "redirect:/VendingMachine/add";
	}
	
	@RequestMapping(value="/edit/{number}",method=RequestMethod.POST)
	public String editing(@PathVariable("number") int number,@Valid@ModelAttribute("item") Item item,BindingResult result) {
		if(result.hasErrors()) return "redirect:/VendingMachine/add";
		mainServ.edititem(item,number);
		return "redirect:/VendingMachine/add";
	}
	
	@RequestMapping("/delete/{number}" )
	public String delete(@PathVariable("number") Long number) {
		mainServ.deleteItem(number);
		return "redirect:/VendingMachine/add";
	}
	
	@RequestMapping("/VendingMachine/home/{id}/buy")
	public String buy(@PathVariable("id") int number,RedirectAttributes redirectAttributes,HttpSession session) {
		if(number >25 || number<0) {
			redirectAttributes.addFlashAttribute("error", "does not exist");
			}
		else {
			session.setAttribute("count", number);
		}

		return "redirect:/VendingMachine/home/";
	}
	@RequestMapping("/buy/yo")
	public String buy(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("good", "successfully bought!!!");

		return "redirect:/VendingMachine/home/";
	}
	
	

}
