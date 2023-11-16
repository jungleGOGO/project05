package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.Qna;
import com.tsherpa.team35.util.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {

    List<Qna> getList(Page page);
    List<Qna> noAnswerList(Page page);
    int noAnswerCount(Page page);
    int getCount(Page page);
    Qna qnaDetail(int qno);
    Qna questionDetail(int par);
    Qna answerDetail(int par);
    void questionInsert(Qna qna);
    void answerInsert(Qna qna);
    void parUpdate(Qna qna);
    void qnaEdit(Qna qna);
    void qnaDelete(int qno);
}
