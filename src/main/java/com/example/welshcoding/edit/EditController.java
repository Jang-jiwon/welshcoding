package com.example.welshcoding.edit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EditController {
	
	@RequestMapping("newPost")
	public String home() {
		log.info("edit Controller");
		return "edit/edit";	// home.html 로 찾아간다.
	}
}
