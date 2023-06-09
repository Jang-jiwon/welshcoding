package com.example.welshcoding.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.example.welshcoding.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.welshcoding.service.TagService;
import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Introduce;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.dto.SeriesListDTO;
import com.example.welshcoding.domain.Tags;
import com.example.welshcoding.service.TestMemberService;
import com.example.welshcoding.service.IntroduceService;
import com.example.welshcoding.service.SeriesService;
import com.example.welshcoding.service.MemberService;

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
    private final IntroduceService introduceService;
    private final MemberService memberService;

    @GetMapping("/mainBoard/{memberId}")
    public String list(@PathVariable Long memberId, Model model, HttpSession session) throws ParseException {
        log.info("1818memberId : " + memberId);
        try {
            List<Board> boards = boardService.findBoards(memberId);
            model.addAttribute("boards", boards);
        } catch (Exception e) {
            log.info("18181818818");
        }


        /********* kdy - series 부분 *********/
        List<SeriesListDTO> seriesList = seriesService.findSeriesAll(memberId);

        if (seriesList.size() == 0) {
            model.addAttribute("seriesSize", false);
        } else {
            model.addAttribute("seriesSize", true);
        }

        model.addAttribute("seriesList", seriesList);
        /********* ------------------- *********/

        Member member = (Member) session.getAttribute("member");
        List<Tags> newtags = tagServices.findTags(member);
        String[] newtagsList = new String[newtags.size()];
        for (int j = 0; j < newtags.size(); j++) {
            newtagsList[j] = newtags.get(j).getTagsName();
        }
        Set<String> set = new HashSet<>(Arrays.asList(newtagsList));
        String[] result = set.toArray(new String[set.size()]);
        for (int j = 0; j < result.length; j++) {
            log.info("NewTagService : " + result[j]);
        }

        System.out.println(memberId);

        Introduce introduce = introduceService.findById(memberId);

        model.addAttribute("introduce", introduce);

        model.addAttribute("alltags", result);
        model.addAttribute("memberId", memberId);


        return "mainbody/body";
    }

    @GetMapping("/mainBoard")
    public String list2(Model model, HttpSession session) throws ParseException {

        Member member = (Member) session.getAttribute("member");
        long testmemberid = member.getMemberId();
        List<Board> boards = boardService.findBoards(testmemberid);

        log.info("Board Controller");
        System.out.println("================sdadadsadsdadad");//boards.get(0).getBoardTitle()+

        List<Tags> newtags = tagServices.findTags(member);
        String[] newtagsList = new String[newtags.size()];
        for (int j = 0; j < newtags.size(); j++) {
            newtagsList[j] = newtags.get(j).getTagsName();
        }
        Set<String> set = new HashSet<>(Arrays.asList(newtagsList));
        String[] result = set.toArray(new String[set.size()]);

        /********* kdy - series 부분 *********/
        List<SeriesListDTO> seriesList = seriesService.findSeriesAll(testmemberid);

        if (seriesList.size() == 0) {
            model.addAttribute("seriesSize", false);
        } else {
            model.addAttribute("seriesSize", true);
        }
        model.addAttribute("memberId", testmemberid);
        model.addAttribute("seriesList", seriesList);
        /********* ------------------- *********/


        /*----------------- 게시물 소개글 들어가는 부분 --------------------*/
        for (int i = 0; i < boards.size(); i++) {
            String cont = boards.get(i).getBoardCont();
            cont = cont.replaceAll("<.*?>", "");
            cont = cont.substring(0, Math.min(cont.length(), 300));
            cont = removeSpecialCharacters(cont);
            boards.get(i).setBoardIntro(cont + "....");
        }

        /*-----------------------------------------------------------*/

        Member recentMember = memberService.findOne(member.getMemberId());

        Introduce introduce = introduceService.findById(testmemberid);

        model.addAttribute("introduce", introduce);
        model.addAttribute("recentMember", recentMember);
        model.addAttribute("boards", boards);
        model.addAttribute("alltags", result);


        return "mainbody/body";
    }

    @GetMapping("/goPost/{postId}")
    public String goPost(@PathVariable Long postId, Model model, HttpSession session) {
        log.info("1818postId : " + postId);
        model.addAttribute("postId", postId);

        Member member = (Member) session.getAttribute("member");
        Board board = boardService.findOne(postId, member.getMemberId());

        Member recentMember = memberService.findOne(member.getMemberId());
        model.addAttribute("recentMember", recentMember);
        model.addAttribute("board", board);
        return "boardPost/post";
    }

    @PostMapping("search")
    @ResponseBody
    public String search(HttpSession session, @RequestParam("inputSearch") String inputSearch) {
        Member member = (Member) session.getAttribute("member");
        String resultList = boardService.search(member.getMemberId(), inputSearch);
        return resultList;
    }


    // 게시물삭제
    @GetMapping("/delPost/{boardId}")
    public String delPost(@PathVariable Long boardId, Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        boardService.deleteById(member, boardId);
        return "redirect:/mainBoard";
    }

    // 게시물수정
    @GetMapping("/changePost/{boardId}")
    public String changePost(@PathVariable Long boardId, Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
//		model.addAttribute("boardId", boardId);
        Board board = boardService.findOne((Long) boardId, member.getMemberId());
//		log.info("tagCont : "+board.getTags().get(0).getTagsName());
        model.addAttribute("changePost", board);
        return "/boardPost/changePost";
    }


    

    @PostMapping("delProfile")
    @ResponseBody
    public String delProfile(@RequestParam("imgsrc") String imgsrc, HttpSession session) {
        String re = "";
        Member member = (Member) session.getAttribute("member");
//		log.info("내용 : "+imgsrc);
        Member recentMember = memberService.findOne(member.getMemberId());
        try {
            testMemberService.updateSrc(" ", recentMember.getMemberId());
            re = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }


        return re;
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
