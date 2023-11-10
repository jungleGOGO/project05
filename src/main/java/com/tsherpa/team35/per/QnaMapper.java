package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.Qna;
import com.tsherpa.team35.util.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {

    List<Qna> getList(Page page);
    int getCount(Page page);
}
