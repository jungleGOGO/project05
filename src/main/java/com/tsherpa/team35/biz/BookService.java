package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public List<Book> BookList() throws Exception;
    public void BookInsert() throws Exception;
    public void NoticeUpdate() throws Exception;
}
