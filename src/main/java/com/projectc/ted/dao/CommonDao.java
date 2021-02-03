package com.projectc.ted.dao;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.projectc.ted.constant.DBCollections;
import com.projectc.ted.constant.TypeCode;


@Repository
public class CommonDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public int pagingCountCommon(JSONObject reqJsonData) throws Exception {
		int retInt = 0;
		Query query = new Query();
		String pagingType = reqJsonData.get(TypeCode.PAGING_TYPE) != null ?  reqJsonData.get(TypeCode.PAGING_TYPE).toString() : TypeCode.NO_REQ_DATA;
		query.addCriteria(Criteria.where(DBCollections.DELETE_YES).is(TypeCode.NO));
		if(pagingType.equals(TypeCode.PAGING_TYPE_BOARD)) {
			query.addCriteria(Criteria.where(DBCollections.BOARD_TYPE).is(TypeCode.BOARD_CODE_MAIN));
			retInt = (int) mongoTemplate.count(query, HashMap.class, DBCollections.BOARD_INFO);
		}		
		return retInt;
	}

}