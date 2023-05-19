package com.example.welshcoding.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Series;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	
	public List<Board> findBoards(long testmemberid) {
		return boardRepository.findAll(testmemberid);
	}
	
	@Transactional
	public void insertData(Board board)throws IllegalAccessException {
		System.out.println("===========insertData==============");
		boardRepository.save(board);
	}
	
	public Board findOne(long postId , long memberId) {
		return boardRepository.findOne(postId,memberId);
	}
	
	public String search(long memberId,String inputSearch) {
		List<Board> results = boardRepository.search(memberId,inputSearch);
		String resultList = "";
		for(int i=0;i<results.size();i++) {
			resultList += ","+results.get(i).getBoardId();
		}
		resultList = resultList.trim().replaceAll("^,|,$", "");
		
		return resultList;
	}
	
	@Transactional
	public void deleteById(Member member,Long boardId) {
		Board board = boardRepository.findOne(boardId,member.getMemberId());
		boardRepository.deleteBoard(board);
	}
	
}
