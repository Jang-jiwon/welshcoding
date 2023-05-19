package com.example.welshcoding.edit;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.welshcoding.board.BoardService;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Series;
import com.example.welshcoding.testjiwon.TestSService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class EditController {
	private final TestSService testSService;
	
	@RequestMapping("newPost")
	public String home(HttpSession session ,Model model) {
		log.info("edit Controller");
		Random random = new Random();
		int id = random.nextInt(10000);
//		return "redirect:/newPost/"+id;
		session.setAttribute("newPostId", id);
		return "edit/edit";
	}
	
//	@RequestMapping("newPost/{postId}")
	public String home2() {
		return "edit/edit";
	}
	
	@RequestMapping("openSeriesPopup")
	public String Popup(HttpSession session ,Model model) {
		Member member = (Member)session.getAttribute("member");
		List<Series> seriesList= testSService.findAll(member.getMemberId());
		
		model.addAttribute("seriesList", seriesList);
		return "edit/addSeriesPopup";	// home.html 로 찾아간다.
	}
}
