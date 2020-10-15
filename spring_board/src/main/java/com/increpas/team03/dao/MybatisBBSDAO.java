package com.increpas.team03.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.increpas.team03.model.BoardVO;

@Repository
public class MybatisBBSDAO {
@Autowired
	SqlSessionTemplate sqlSessionTemplate;
   
  public void selectBoard() {
	  BoardVO vo= new BoardVO();
	  vo.setTitle("테스트");
	  vo.setWriter("관리자");
	  List<BoardVO> list
	  = sqlSessionTemplate.selectList("sqlmap.bbs.selectBoard", vo);
	  System.out.println("검색결과 : " +list);
	
}
   
	
}
