
package com.projectc.ted.service;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectc.ted.constant.ReturnCode;
import com.projectc.ted.constant.TypeCode;
import com.projectc.ted.dao.CommonDao;

@Service
public class CommonService {

	@Autowired
	CommonDao commonDao;

	public JSONObject pagingCommon(JSONObject reqJsonData) throws Exception {
		JSONObject resJsonData = new JSONObject();
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		int listSize = reqJsonData.get(TypeCode.LIST_SIZE) == null ? 5 : Integer.parseInt(reqJsonData.get(TypeCode.LIST_SIZE).toString());
		int curPage = reqJsonData.get(TypeCode.CURRENT_PAGE) == null ? 1 : Integer.parseInt(reqJsonData.get(TypeCode.CURRENT_PAGE).toString());		
		int resultCount = pagingCountCommon(reqJsonData);
		
		if(resultCount > 0) {
			// 페이지 계산
			int pCnt1 = resultCount / listSize;
			int pCnt2 = resultCount % listSize > 0 ? 1 : 0;
			int sumCnt = pCnt1 + pCnt2;
			if(sumCnt != 0 && sumCnt < curPage) {
				curPage = sumCnt;
			}
			
			retMap.put("pageTotalCount", pCnt1 + pCnt2);
			retMap.put("currentPage", curPage);
			retMap.put("listSize", listSize);
			retMap.put("resultCount", resultCount);
			resJsonData.put(TypeCode.RETURN_INFO, retMap);
			resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_SUCCESS);				
		}else {
			resJsonData.put(TypeCode.RESULT_CODE, ReturnCode.REQ_NO_DATA);	
		}
		return resJsonData;
	}

	public int pagingCountCommon(JSONObject reqJsonData) throws Exception {
		return commonDao.pagingCountCommon(reqJsonData);
	}

}
