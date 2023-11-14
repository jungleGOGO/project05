package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BookMapper {
    public List<Book> BookList() throws Exception;
    public void BookInsert() throws Exception;
    public void NoticeUpdate() throws Exception;
}
