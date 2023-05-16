package com.example.welshcoding.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.welshcoding.domain.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/mainBoard")
	public String list(Model model) {
		List<Board> boards = boardService.findBoards();
		log.info("Board Controller");
		System.out.println("================sdadadsadsdadad");//boards.get(0).getBoardTitle()+
		model.addAttribute("boards", boards);
		return "mainbody/body";
	}
	
	
}
