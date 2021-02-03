package com.projectc.ted.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.projectc.ted.service.BoardService;

@RestController
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	BoardService boardSerive;
	/* 101 게시글/댓글 생성 */	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public @ResponseBody JSONObject createBoard (@RequestBody JSONObject reqJsonData) throws Exception {		
		return boardSerive.createBoard(reqJsonData);		
	}
	/* 102 게시글/댓글 조회 */
	@RequestMapping(value = "/read", method=RequestMethod.GET)
	public @ResponseBody JSONObject readBoard (@RequestParam String _id) throws Exception {		
		return boardSerive.readBoard(_id);		
	}
	/* 103 게시글/댓글 수정 */
	@RequestMapping(value = "/update", method=RequestMethod.PUT)
	public @ResponseBody JSONObject updateBoard (@RequestParam String _id, @RequestBody JSONObject reqJsonData) throws Exception {		
		return boardSerive.updateBoard(_id, reqJsonData);		
	}
	/* 104 게시글/댓글 삭제 */
	@RequestMapping(value = "/delete", method=RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteBoard (@RequestParam String _id) throws Exception {		
		return boardSerive.deleteBoard(_id);		
	}
	/* 106 게시글 전체 호출 */
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public @ResponseBody JSONObject listBoard (@RequestParam String type) throws Exception {		
		return boardSerive.listBoard(type);		
	}
	/* 107 게시글 댓글 호출 */
	@RequestMapping(value = "/reply", method=RequestMethod.GET)
	public @ResponseBody JSONObject replyBoard (@RequestParam String boardId) throws Exception {		
		return boardSerive.replyBoard(boardId);		
	}
	

}