package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.Faq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqMapper {

    List<Faq> getList();
}
