package com.projectc.ted.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.projectc.ted.constant.DBCollections;
import com.projectc.ted.constant.ReturnCode;
import com.projectc.ted.constant.TypeCode;
import com.projectc.ted.util.DateUtil;


@Repository
public class BoardDao {

	@Autowired
	MongoTemplate mongoTemplate;

	public JSONObject createBoard(JSONObject reqJsonData) {
		HashMap<String, Object> saveMap = new HashMap<String, Object>();
		JSONObject resJsonData = new JSONObject();
		try {
			String _id = reqJsonData.get(DBCollections._ID) != null ? reqJsonData.get(DBCollections._ID).toString()
					: TypeCode.NO_REQ_DATA;
			String writer = reqJsonData.get(DBCollections.BOARD_WRITER) != null
					? reqJsonData.get(DBCollections.BOARD_WRITER).toString()
					: TypeCode.NO_REQ_DATA;
			String contents = reqJsonData.get(DBCollections.BOARD_CONTENTS) != null
					? reqJsonData.get(DBCollections.BOARD_CONTENTS).toString()
					: TypeCode.NO_REQ_DATA;
			String thumbnail = reqJsonData.get(DBCollections.BOARD_THUMBNAIL) != null
					? reqJsonData.get(DBCollections.BOARD_THUMBNAIL).toString()
					: TypeCode.NO_REQ_DATA;
			String type = reqJsonData.get(DBCollections.BOARD_TYPE) != null
					? reqJsonData.get(DBCollections.BOARD_TYPE).toString()
					: TypeCode.NO_REQ_DATA;
			String createData = DateUtil.curDate(1);
			
			saveMap.put(DBCollections._ID, UUID.randomUUID().toString());				
			saveMap.put(DBCollections.BOARD_WRITER, writer);
			saveMap.put(DBCollections.BOARD_CONTENTS, contents);
			saveMap.put(DBCollections.BOARD_TYPE, type);
			saveMap.put(DBCollections.CREATE_DATE, createData);
			saveMap.put(DBCollections.DELETE_YES, TypeCode.NO);
			
			if (type.equals(TypeCode.BOARD_CODE_MAIN)) {
				saveMap.put(DBCollections.BOARD_THUMBNAIL, thumbnail);
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_SUCCESS);
				mongoTemplate.save(saveMap, DBCollections.BOARD_INFO);
			} else if (type.equals(TypeCode.BOARD_CODE_REPLY) && !_id.equals(TypeCode.NO_REQ_DATA)) {
				saveMap.put(DBCollections.BOARD_ID, _id);
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_SUCCESS);
				mongoTemplate.save(saveMap, DBCollections.BOARD_INFO);
			} else {
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			}
			
			return resJsonData;
		} catch (Exception e) {
			resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			return resJsonData;
		}
	}
	
	public JSONObject readBoard(String _id) {
		JSONObject resJsonData = new JSONObject();
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		try {
			Query query = new Query(Criteria.where(DBCollections._ID).is(_id));
			retMap = mongoTemplate.findOne(query, HashMap.class, DBCollections.BOARD_INFO);
			if (retMap != null) {
				resJsonData.put(TypeCode.RETURN_INFO, retMap);
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_SUCCESS);
			}else {
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			}
			return resJsonData;
		} catch (Exception e) {
			resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			return resJsonData;
		}
	}

	public JSONObject updateBoard(String _id, JSONObject reqJsonData) {
		JSONObject resJsonData = new JSONObject();
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		try {
			Query query = new Query(Criteria.where(DBCollections._ID).is(_id));
			retMap = mongoTemplate.findOne(query, HashMap.class, DBCollections.BOARD_INFO);
			if (retMap != null) {
				String type = retMap.get(DBCollections.BOARD_TYPE) != null	? retMap.get(DBCollections.BOARD_TYPE).toString(): TypeCode.NO_REQ_DATA;				
				String writer = reqJsonData.get(DBCollections.BOARD_WRITER) != null	? reqJsonData.get(DBCollections.BOARD_WRITER).toString(): TypeCode.NO_REQ_DATA;
				String contents = reqJsonData.get(DBCollections.BOARD_CONTENTS) != null	? reqJsonData.get(DBCollections.BOARD_CONTENTS).toString(): TypeCode.NO_REQ_DATA;				
				String updateData = DateUtil.curDate(1);
				
				Update upd = new Update();
				upd.set(DBCollections.BOARD_WRITER, writer);
				upd.set(DBCollections.BOARD_CONTENTS, contents);				
				upd.set(DBCollections.UPDATE_DATE, updateData);

				if(type.equals(TypeCode.BOARD_CODE_MAIN)) {
					String thumbnail = reqJsonData.get(DBCollections.BOARD_THUMBNAIL) != null	? reqJsonData.get(DBCollections.BOARD_THUMBNAIL).toString(): TypeCode.NO_REQ_DATA;
					upd.set(DBCollections.BOARD_THUMBNAIL, thumbnail);
					mongoTemplate.updateFirst(query, upd, DBCollections.BOARD_INFO);
					resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_SUCCESS);
				}else{
					mongoTemplate.updateFirst(query, upd, DBCollections.BOARD_INFO);
					resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_SUCCESS);
				}								
			}else {
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			}
			return resJsonData;
		} catch (Exception e) {
			resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			return resJsonData;
		}
	}
	
	public JSONObject deleteBoard(String _id) {
		JSONObject resJsonData = new JSONObject();
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		try {
			Query query = new Query(Criteria.where(DBCollections._ID).is(_id));
			retMap = mongoTemplate.findOne(query, HashMap.class, DBCollections.BOARD_INFO);
			if (retMap != null) {
				String deleteYes = retMap.get(DBCollections.DELETE_YES) != null	? retMap.get(DBCollections.DELETE_YES).toString(): TypeCode.NO_DATA;	
				if(deleteYes.equals(TypeCode.NO)) {
					String updateData = DateUtil.curDate(1);
					Update upd = new Update();
					upd.set(DBCollections.DELETE_YES, TypeCode.YES);
					upd.set(DBCollections.UPDATE_DATE, updateData);
					mongoTemplate.updateFirst(query, upd, DBCollections.BOARD_INFO);
					resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_SUCCESS);	
				}else {
					resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);	
				}				
			}else {
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			}
			return resJsonData;
		} catch (Exception e) {
			resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			return resJsonData;
		}
	}
	
	public JSONObject listBoard(String type) {
		JSONObject resJsonData = new JSONObject();
		try {
			Query query = new Query(Criteria.where(DBCollections.BOARD_TYPE).is(type).and(DBCollections.DELETE_YES).is(TypeCode.NO));
			ArrayList<HashMap> retList = (ArrayList<HashMap>) mongoTemplate.find(query, HashMap.class, DBCollections.BOARD_INFO);
			if (retList != null) {
				resJsonData.put(TypeCode.RETURN_INFO, retList);
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_SUCCESS);
			}else {
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			}
			return resJsonData;
		} catch (Exception e) {
			resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			return resJsonData;
		}
	}
	
	
	public JSONObject replyBoard(String boardId) {
		JSONObject resJsonData = new JSONObject();
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		try {
			Query query = new Query(Criteria.where(DBCollections.BOARD_ID).is(boardId));
			retMap = mongoTemplate.findOne(query, HashMap.class, DBCollections.BOARD_INFO);
			if (retMap != null) {
				resJsonData.put(TypeCode.RETURN_INFO, retMap);
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_SUCCESS);
			}else {
				resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			}
			return resJsonData;
		} catch (Exception e) {
			resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_FAIL);
			return resJsonData;
		}
	}

}