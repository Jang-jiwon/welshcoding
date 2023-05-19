package com.example.welshcoding.board;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.welshcoding.Tag.TagService;
import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.SeriesListDTO;
import com.example.welshcoding.domain.Tags;
import com.example.welshcoding.edit.TestMemberService;
import com.example.welshcoding.series.SeriesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private final TestMemberService testMemberService;
	private final SeriesService seriesService;
	private final TagService tagServices;
	
	@GetMapping("/mainBoard/{memberId}")
	public String list(@PathVariable Long memberId,Model model ,HttpSession session) throws ParseException {
		log.info("1818memberId : "+memberId);
		try {
			List<Board> boards = boardService.findBoards(memberId);
			model.addAttribute("boards", boards);
		} catch (Exception e) {
			log.info("18181818818");
		}
		
		
		/********* kdy - series 부분 *********/
		List<SeriesListDTO> seriesList = seriesService.findSeriesAll(memberId);
		
		if(seriesList.size() == 0) {
			model.addAttribute("seriesSize", false);
		} else {
			model.addAttribute("seriesSize", true);
		}
		
		model.addAttribute("seriesList", seriesList);
		/********* ------------------- *********/
		
		Member member = (Member)session.getAttribute("member");
		List<Tags> newtags = tagServices.findTags(member);
		String[] newtagsList = new String[newtags.size()];
		for(int j=0;j<newtags.size();j++) {
			newtagsList[j]=newtags.get(j).getTagsName();
		}
		Set<String> set = new HashSet<>(Arrays.asList(newtagsList));
		String[] result = set.toArray(new String[set.size()]);
		for(int j=0;j<result.length;j++) {
			log.info("NewTagService : "+result[j]);
		}
		
		model.addAttribute("alltags", result);
		return "mainbody/body";
	}
	
	@GetMapping("/mainBoard")
	public String list2(Model model ,HttpSession session) throws ParseException {
		
		Member member = (Member)session.getAttribute("member");
		long testmemberid = member.getMemberId();
		
		
		List<Board> boards = boardService.findBoards(testmemberid);
		log.info("Board Controller");
		System.out.println("================sdadadsadsdadad");//boards.get(0).getBoardTitle()+
		
//		List<String> tags = new ArrayList<>();
		List<Tags> newtags = tagServices.findTags(member);
		String[] newtagsList = new String[newtags.size()];
		for(int j=0;j<newtags.size();j++) {
			newtagsList[j]=newtags.get(j).getTagsName();
		}
		Set<String> set = new HashSet<>(Arrays.asList(newtagsList));
		String[] result = set.toArray(new String[set.size()]);
		for(int j=0;j<result.length;j++) {
			log.info("NewTagService : "+result[j]);
		}
		
//		String taglist = testMemberService.findTags(testmemberid);
//		if(taglist != null) {
//			String[] tagsArray = taglist.split(",");
//			for(int i=0;i<tagsArray.length;i++) {
//				if(tagsArray[i]!="") {
//					tags.add(tagsArray[i]);
//					
//				}
//			}
//		}
		
		/********* kdy - series 부분 *********/
		List<SeriesListDTO> seriesList = seriesService.findSeriesAll(testmemberid);
		
		if(seriesList.size() == 0) {
			model.addAttribute("seriesSize", false);
		} else {
			model.addAttribute("seriesSize", true);
		}
		model.addAttribute("memberId", testmemberid);
		model.addAttribute("seriesList", seriesList);
		/********* ------------------- *********/
		
		
		/*----------------- 게시물 소개글 들어가는 부분 --------------------*/
		for(int i=0;i<boards.size();i++) {
			String cont = boards.get(i).getBoardCont();
			cont  = cont.replaceAll("<.*?>", "");
			cont = cont.substring(0, Math.min(cont.length(), 300));
			boards.get(i).setBoardIntro(cont+"....");
		}
		/*-----------------------------------------------------------*/
		
		model.addAttribute("boards", boards);
		model.addAttribute("alltags", result);
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
	
	@PostMapping("search")
	@ResponseBody
	public String search(HttpSession session,@RequestParam("inputSearch") String inputSearch) {
		Member member = (Member)session.getAttribute("member");
		String resultList = boardService.search(member.getMemberId(),inputSearch);
		return resultList;
	}
	
}
