package com.example.welshcoding.edit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.welshcoding.board.BoardService;
import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Comments;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Series;
import com.example.welshcoding.domain.Sns;
import com.example.welshcoding.domain.Temporary;
import com.example.welshcoding.testjiwon.TestSService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SaveController {
	
	private final BoardService boardService ;
	private final TestMemberService testMemberService;
	private final TestSService testSService;
	
	@RequestMapping("save")
	public String home() {	 //여기서 시리즈 목록 가져오기
		log.info("save Controller");
		return "edit/save";	// home.html 로 찾아간다.
	}
	
	@RequestMapping("controller")
	public String goController() {
		log.info("controller Controller");
		return "edit/controller";	// home.html 로 찾아간다.
	}
	
	@PostMapping("/save-data")
	public String saveData(@RequestParam("gridData") String gridData  ,@RequestParam("gridtitle") String gridtitle,
			@RequestParam("tag") String tagList ,@RequestParam("selSeries") String selSeries ,HttpSession session) throws IllegalAccessException {
//		System.out.println("!!!!!!!!!!!!!!"+postintro+"!!!!!!!!!!!!!!"); //입력받은거 텍스트만 받아서 나중에 소개로 쓸거임
		
		// 현재 로컬 시간 받기
        LocalDateTime localDateTime = LocalDateTime.now();
		// 원하는 형식으로 시간 표시
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        System.out.println("로컬 시간: " + formattedDateTime);
		
		
	    /* ... */log.info("save-data Controller");
		System.out.println("===================================\n"+gridData +", "+gridtitle); // 잘넘어옴
//		Member member = new Member();
//		member.setUserEmail("test");
		Member member = (Member)session.getAttribute("member");
		
		Board board = new Board();
		board.setBoardTitle(gridtitle);
		board.setBoardCont(gridData);
		board.setBoardDate(formattedDateTime);
		board.setBoardLike("3");
		if(tagList != null) {
			member = testMemberService.addTags(member.getMemberId(),tagList);
			board.setBoardTag(tagList);
		}
//		board.setBoardTag(tagList);
		board.setSeries(new Series());
		board.setThumbnailPath("test");
		board.setMember(member);
		
		
		
		if((!selSeries.isEmpty()) && (selSeries != null) ) {
			System.out.println("===========들어온다개굴이==============");
			Series series = new Series();
			series.setSeriesName(selSeries);
			series.addBoard(board);
			series.setCreateDate(formattedDateTime);
			series.setUpdateDate(formattedDateTime);
			series.setMember(member);					/////여기가 문제
			testSService.save(series);	
		}else {
			System.out.println("===========들어온다개굴개굴이==============");
			Series series = new Series();
			series.addBoard(board);
			testSService.save(series);
		}
        
		System.out.println("===========saveData==============");
		boardService.insertData(board);
		System.out.println("=========================저장완료=============================");
		
	    return "redirect:/mainBoard";
	}
}
