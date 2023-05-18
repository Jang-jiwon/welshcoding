package com.example.welshcoding.board;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.SeriesListDTO;
import com.example.welshcoding.series.SeriesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@RequiredArgsConstructor
public class BoardController_kdy {
	private final BoardService boardService;
	private final SeriesService seriesService;
	
//	@GetMapping("/mainBoard/{memberId}")
	/*
	 * html import 한게 thymeleaf 로 렌더링이 안되네여 ㅎㅎ;;
	 * 테스트 할려고 만들어놨습니다. 무시하셔도 됩니다!
	 */
	public String list(@PathVariable Long memberId, Model model) throws ParseException {
		List<Board> boards = boardService.findBoards();
		log.info("Board Controller");
		System.out.println("================sdadadsadsdadad");//boards.get(0).getBoardTitle()+
		model.addAttribute("boards", boards);
		
		List<SeriesListDTO> seriesList = seriesService.findSeriesAll(memberId);
		model.addAttribute("seriesList", seriesList);
		
		return "mainbody/body";
	}
	
	
}
