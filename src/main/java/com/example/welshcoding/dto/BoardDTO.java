package com.example.welshcoding.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardDTO {
	private Long boardId;
	private Long seriesId;
	private String seriesName;
	private String boardTitle;
	private String boardTag;
	private String boardDate;
	private String boardCont;
	private String boardLike;
	private String thumbnailPath;
	
	public BoardDTO(Long boardId, Long seriesId, String seriesName, String boardTitle, String boardTag,
			String boardDate, String boardCont, String boardLike, String thumbnailPath) {
		this.boardId = boardId;
		this.seriesId = seriesId;
		this.seriesName = seriesName;
		this.boardTitle = boardTitle;
		this.boardTag = boardTag;
		this.boardDate = boardDate;
		this.boardCont = boardCont;
		this.boardLike = boardLike;
		this.thumbnailPath = thumbnailPath;
	}
	
	
}
