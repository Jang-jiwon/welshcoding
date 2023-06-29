package com.example.welshcoding.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Temporary;
import com.example.welshcoding.service.MemberService;
import com.example.welshcoding.testjiwon.TestTemporaryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TemporaryController {
	private final TestTemporaryService testTemporaryService;
	private final MemberService memberService;
	@GetMapping("goTempForm")
	public String goForm(Model model) {
		return "/edit/tempController";
	}
	
	@GetMapping("/goTempEdit/{temporaryId}")
	public String goTempEdit(Model model, @PathVariable Long temporaryId,HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		Temporary temp = testTemporaryService.findOne2(temporaryId, member.getMemberId());
		model.addAttribute("changeTemp", temp);
		return "/temporary/edit";
	}
	
	@GetMapping("/gotemporary")
	public String go(Model model,HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		List<Temporary> temps = testTemporaryService.findAll(member.getMemberId());
		for(int i=0;i<temps.size();i++) {
			String cont = temps.get(i).getTemporaryDetail();
			if(cont==null || cont.trim()=="") {
				cont="";
				
			}else {
				cont  = cont.replaceAll("<.*?>", "");
				cont = cont.substring(0, Math.min(cont.length(), 300));
				cont =removeSpecialCharacters(cont);
			}
			temps.get(i).setTemporaryDetail(cont);
		}
		member = memberService.getMemberById(member.getMemberId());
		model.addAttribute("recentMember", member);
		model.addAttribute("temps", temps);
		return "/temporary/temporary";
	}
	
	
	
	@PostMapping("temporary")
	@ResponseBody
	public String home(HttpSession session,Model model, @RequestParam("temporaryTitle") String temporaryTitle ,
			@RequestParam("temporaryDetail") String temporaryDetail) throws IllegalAccessException {
		log.info("temporaryLog:"+temporaryDetail);
		int newpostId = (int) session.getAttribute("newTempId");
		String result="";
		try {
			Temporary temporary = testTemporaryService.findOne(newpostId);
			if(temporary == null) {
				log.info("개굴스");
				temporary = new Temporary();
				temporary.setTemporaryId((long)newpostId);
			}log.info("개굴스22");
			LocalDateTime localDateTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateTime = localDateTime.format(formatter);
			Member member = (Member)session.getAttribute("member");
//			Temporary temporary = new Temporary();
			temporary.setMember(member);
			temporary.setTemporaryTitle(temporaryTitle);
			temporary.setTemporaryDetail(temporaryDetail);
			temporary.setTemporaryDate(formattedDateTime);
			
			testTemporaryService.save(temporary);
			log.info("temporary sucesses");
			result="ok";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("개굴스3");
		}
		
		return result;	// home.html 로 찾아간다.
	}
	
	@PostMapping("/goTempEdit/changeTemporary")
	@ResponseBody
	public String changeTemporary(HttpSession session,Model model, @RequestParam("temporaryTitle") String temporaryTitle ,
			@RequestParam("temporaryId") long temporaryId ,
			@RequestParam("temporaryDetail") String temporaryDetail) {
		log.info("interJIWON18");
		String result="";
		try {
			Member member = (Member)session.getAttribute("member");
			LocalDateTime localDateTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateTime = localDateTime.format(formatter);
			Temporary temp = new Temporary();
			temp.setTemporaryId(temporaryId);
			temp.setTemporaryDetail(temporaryDetail);
			temp.setTemporaryTitle(temporaryTitle);
			temp.setMember(member);
			temp.setTemporaryDate(formattedDateTime);
			testTemporaryService.updateTemp(temp);
			result = "ok";
		} catch (Exception e) {
			log.info("interJIWON181818");
			e.printStackTrace();
		}
		return result;	// home.html 로 찾아간다.
	}
	
	// 임시글삭제
	@GetMapping("/delTemp/{temporaryId}")
	public String delTemp(@PathVariable Long temporaryId,Model model ,HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		testTemporaryService.deleteById(member,temporaryId);
		return "redirect:/gotemporary";
	}
	
	
	public static String removeSpecialCharacters(String input) {
        // 특수문자 제외한 문자열을 저장할 StringBuilder 생성
        StringBuilder sb = new StringBuilder();
        
        // 입력 문자열을 한 글자씩 순회
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            
            // 특수문자가 아닌 경우만 StringBuilder에 추가
            if (Character.isLetterOrDigit(c) || Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        
        // StringBuilder의 내용을 문자열로 반환
        return sb.toString();
    }
}

