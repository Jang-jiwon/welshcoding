package com.example.welshcoding.temporay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemporaryController {

	@GetMapping("/temporary")
	public String list() {
		return "temporary/temporary.html";
	}
	
	@GetMapping("/edittemporary")
	public String edit() {
		return "temporary/edittemporary.html";
	}
}
