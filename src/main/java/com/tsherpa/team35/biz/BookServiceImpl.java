package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.Book;
import com.tsherpa.team35.per.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> BookList() throws Exception {
        return bookMapper.BookList();
    }

    @Override
    public void BookInsert() throws Exception {
        bookMapper.BookInsert();
    }

    @Override
    public void NoticeUpdate() throws Exception {
        bookMapper.NoticeUpdate();
    }
}
