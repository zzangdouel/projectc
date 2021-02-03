package com.projectc.ted.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.projectc.ted.service.CommonService;

@RestController
@RequestMapping("common")
public class CommonController {
	
	@Autowired
	CommonService commonSerive;
	/* paging 조회 */	
	@RequestMapping(value = "/paging", method=RequestMethod.POST)
	public @ResponseBody JSONObject pagingCommon (@RequestBody JSONObject reqJsonData) throws Exception {		
		return commonSerive.pagingCommon(reqJsonData);		
	}
	
	
}