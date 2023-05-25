package com.example.welshcoding.service;

import java.util.List;

import com.example.welshcoding.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public List<Board> findBoards(long testmemberid) {
        return boardRepository.findAll(testmemberid);
    }

    @Transactional
    public void insertData(Board board) throws IllegalAccessException {
        System.out.println("===========insertData==============");
        boardRepository.save(board);
    }

    public Board findOne(long postId, long memberId) {
        return boardRepository.findOne(postId, memberId);
    }

    public String search(long memberId, String inputSearch) {
        List<Board> results = boardRepository.search(memberId, inputSearch);
        String resultList = "";
        for (int i = 0; i < results.size(); i++) {
            resultList += "," + results.get(i).getBoardId();
        }
        resultList = resultList.trim().replaceAll("^,|,$", "");

        return resultList;
    }

    @Transactional
    public void deleteById(Member member, Long boardId) {
        Board board = boardRepository.findOne(boardId, member.getMemberId());
        boardRepository.deleteBoard(board);
    }

    @Transactional
    public void updateBoard(Board board, long boardId, long memberId) {
        Board oldboard = boardRepository.findOne(boardId, memberId);
        oldboard.setBoardTitle(board.getBoardTitle());
        oldboard.setBoardCont(board.getBoardCont());
        oldboard.setBoardDate(board.getBoardDate());
//		oldboard.setTags(board.getTags());
    }

}
