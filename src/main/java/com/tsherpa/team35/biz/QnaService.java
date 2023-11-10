package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.Qna;
import com.tsherpa.team35.per.QnaMapper;
import com.tsherpa.team35.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaService {

    @Autowired
    private QnaMapper qnaMapper;

    public List<Qna> getList(Page page) { return qnaMapper.getList(page); }
    public int getCount(Page page) { return qnaMapper.getCount(page);}
}
