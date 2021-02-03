
package com.projectc.ted.service;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectc.ted.dao.BoardDao;


@Service
public class BoardService {
	
	@Autowired
	BoardDao boardDao;

	public JSONObject createBoard(JSONObject reqJsonData) throws Exception {		
		return boardDao.createBoard(reqJsonData);
	}
	
	public JSONObject readBoard(String _id) throws Exception {		
		return boardDao.readBoard(_id);
	}
	
	public JSONObject updateBoard(String _id, JSONObject reqJsonData) throws Exception {		
		return boardDao.updateBoard(_id, reqJsonData);
	}
	
	public JSONObject deleteBoard(String _id) throws Exception {		
		return boardDao.deleteBoard(_id);
	}
	
	public JSONObject listBoard(String type) throws Exception {		
		return boardDao.listBoard(type);
	}
	
	public JSONObject replyBoard(String boardId) throws Exception {		
		return boardDao.replyBoard(boardId);
	}

}
