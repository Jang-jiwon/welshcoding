package com.example.welshcoding.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SeriesListDTO {
	private Long seriesId;
	private String seriesName; 
	private int boardCnt;
	private String updateDateToNow;
	private String seriesImgUrl;
}
