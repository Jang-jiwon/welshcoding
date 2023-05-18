package com.example.welshcoding.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.edit.TestMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private final TestMemberService testMemberService;
	
	@GetMapping("/mainBoard/{memberId}")
	public String list(@PathVariable Long memberId,Model model ,HttpSession session) {
		log.info("1818memberId : "+memberId);
		
		
//		Member member = (Member)session.getAttribute("member");
//		long testmemberid = member.getMemberId();
		
		
		List<Board> boards = boardService.findBoards(memberId);
		log.info("Board Controller");
		System.out.println("================sdadadsadsdadad");//boards.get(0).getBoardTitle()+
		
		
		String tags = testMemberService.findTags(memberId);
		
		model.addAttribute("boards", boards);
		model.addAttribute("tags", tags);
		return "mainbody/body";
	}
	
	@GetMapping("/mainBoard")
	public String list2(Model model ,HttpSession session) {
		
		Member member = (Member)session.getAttribute("member");
		long testmemberid = member.getMemberId();
		
		
		List<Board> boards = boardService.findBoards(testmemberid);
		log.info("Board Controller");
		System.out.println("================sdadadsadsdadad");//boards.get(0).getBoardTitle()+
		
		List<String> tags = new ArrayList<>();
		String taglist = testMemberService.findTags(testmemberid);
		if(taglist != null) {
			String[] tagsArray = taglist.split(",");
			for(int i=0;i<tagsArray.length;i++) {
				tags.add(tagsArray[i]);
			}
		}
		
		
		model.addAttribute("boards", boards);
		model.addAttribute("tags", tags);
		return "mainbody/body";
	}
	
	@GetMapping("/goPost/{postId}")
	public String goPost(@PathVariable Long postId,Model model ,HttpSession session) {
		log.info("1818postId : "+postId);
		model.addAttribute("postId", postId);
		
		Member member = (Member)session.getAttribute("member");
		Board board = boardService.findOne(postId,member.getMemberId());
		model.addAttribute("board", board);
		return "post/post";
	}
	
	
}
