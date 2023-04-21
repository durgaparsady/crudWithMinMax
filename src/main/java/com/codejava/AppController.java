package com.codejava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppController {
	@Autowired
	private ProductService service;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		String keyword ="";
		Integer min=0;
		System.out.println("back to home page");
		Integer max=Integer.MAX_VALUE;
		 
		return listByPage(model, 1, "id", "asc",keyword ,min,max);
	}

	@GetMapping("/page/{pageNo}")
	public String listByPage(Model model, @PathVariable("pageNo") int currentPage,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "min", defaultValue = "0") int min,
			@RequestParam(name = "max", defaultValue = "2147483647") int max
		  ) {

		Page<Product> page = service.listAll(currentPage, sortField, sortDir,keyword,min,max);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();

		List<Product> listProducts = page.toList();
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);
		model.addAttribute("min", min);
		model.addAttribute("max", max);
		 System.out.println("controller min"+min);
		 System.out.println("controller max"+max);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		return "index";
	}

	@RequestMapping("/new")
	public String showNewProductFrom(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product";

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") AddRequest addRequest, HttpSession httpSession  ) throws IOException {
 
	String img=	ProductImageData.ImageUploade(addRequest.getImage(), httpSession);
		
		service.addImage(addRequest, img, addRequest.getImage().getBytes());
		 
		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
	   System.out.println(product);
		mav.addObject("product", product);

		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") UpdateRequest rrequest , HttpSession httpSession  ) throws IOException{
	service.update(rrequest,httpSession);
	
		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {

		service.delete(id);
		return "redirect:/";
	}
//	@GetMapping("/back")
//	public String backToHomePage() {
//		System.out.println("back to home page");
//		return "redirect:/";
//	}

}
