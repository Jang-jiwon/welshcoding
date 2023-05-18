package com.example.welshcoding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class main3 {
	
	public static void main(String[] args) throws ParseException {
		String date1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String date2 = "2023-05-17 15:53:47";

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		/* Date타입으로 변경 */
		
		Date d1 = format.parse( date1 );
		Date d2 = format.parse( date2 );
		long Sec = (d1.getTime() - d2.getTime()) / 1000; // 초
		long Days = Sec / (24*60*60); // 일자수
		
		System.out.println(Days + "일 차이");
		
		
	}
}
