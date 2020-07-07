package com.answer.model.biz;

import java.util.List;

import com.answer.model.dao.AnswerDao;
import com.answer.model.dao.AnswerDaoImpl;
import com.answer.model.dto.AnswerDto;

public class AnswerBiz {
	
	AnswerDao dao = new AnswerDaoImpl(); 
	
	public List<AnswerDto> selectList(){
		return dao.selectList();
	};
	public AnswerDto selectOne(int boardno) {
		return dao.selectOne(boardno);
	};
	public int insert(AnswerDto dto) {
		return dao.insert(dto);
	};
	public int update(AnswerDto dto) {
		return dao.update(dto);
	};
	public int delete(int boardno) {
		return dao.delete(boardno);
	};
	
	public int answerproc(AnswerDto dto) {
		
		// 클릭한 해당글(자세히 보기에서 글작성으로 들어가게됨)의 boardno를 받아오는것이기 때문의 부모의 boardno
		int updateres = dao.answerUpdate(dto.getBoardno());
		
		// 원본글의 내용을 html에 뿌려주고 부모의 boardno를 가지고 +1 처리를 해주는 sql쿼리문을 가지고있음
		int insertres = dao.answerInsert(dto);
		
		int result  = 0;
		
		if (updateres > 0 && insertres > 0) {
			result = updateres + insertres;
		} else {
			result = 0;
		}
		
		// 해당글의 boardno를 받아와서 해당글의 그룹seq와 해당 글 답글의 마지막 그룹seq가 같다면(즉 답글이 하나라면)
		// 업데이트가 필요하지 않고, 그게 아니라면 업데이트 해주어야함
		// 따라서 dao에 메서드가 하나 더 필요함
		
		return result;
		
	};

}
