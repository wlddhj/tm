/**
 * 
 */
package com.hhz.tms.web.sys;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangjian
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@RequestMapping(value = "")
	public String index(Model model) {
		
		return "admin/index";
	}
}
