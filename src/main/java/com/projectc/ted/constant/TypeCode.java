package com.projectc.ted.constant;

public interface TypeCode {
	//paging 코드
	public static String LIST_SIZE				= "listSize"; //목록 갯수
	public static String CURRENT_PAGE				= "currentPage"; //현재 페이지
	public static String PAGING_TYPE				= "pagingType"; //페이징 종류
	
	//pagingType 코드
	public static String PAGING_TYPE_BOARD				= "board"; //페이징 종류
	
	//게시판 코드
	public static String BOARD_CODE_MAIN				= "main"; //게시판 게시글
	public static String BOARD_CODE_REPLY				= "reply"; //게시판 댓글
    
	//기타 코드
	public static String NO_DATA				= "noData"; //데이터가 없을경우
	public static String NO_REQ_DATA				= "noReqData"; //요청 데이터가 없을경우
	public static String RESULT_CODE				= "resultCode"; //응답 데이터 코드
	public static String RETURN_INFO				= "returnInfo"; //응답 데이터
	public static String YES				= "yes"; 
	public static String NO				= "no";
	
}
