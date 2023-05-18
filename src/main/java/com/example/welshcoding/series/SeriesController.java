package com.example.welshcoding.series;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.welshcoding.domain.BoardDTO;
import com.example.welshcoding.domain.SeriesListDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SeriesController {

	private final SeriesService seriesService;
	
	@GetMapping("/kdy/series/{memberId}")
	public String seriesList(@PathVariable Long memberId, Model model) throws ParseException {
		
		List<SeriesListDTO> seriesList = seriesService.findSeriesAll(memberId);
		model.addAttribute("seriesList", seriesList);
		return "series/series";
	}
	
	@GetMapping("/kdy/series/{memberId}/{seriesId}")
	public String boardListOfSeries(
			@PathVariable(name = "memberId") Long memberId,
			@PathVariable(name = "seriesId") Long seriesId,
			Model model
			) {
		
		List<BoardDTO> boardList = seriesService.findBoardsBySeries(seriesId);
		model.addAttribute("boardList",boardList);
		model.addAttribute("seriesName",boardList.get(0).getSeriesName());
		return "series/seriesDetail";
	}
	@GetMapping("/kdy/series/{memberId}/{seriesId}/edit")
	public String editBoardListOfSeries(
			@PathVariable(name = "memberId") Long memberId,
			@PathVariable(name = "seriesId") Long seriesId,
			Model model
			) {
		
		List<BoardDTO> boardList = seriesService.findBoardsBySeries(seriesId);
		model.addAttribute("boardList",boardList);
		model.addAttribute("seriesName",boardList.get(0).getSeriesName());
		return "series/seriesUpdate";
	}
}
