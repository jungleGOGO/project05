package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.Notice;
import com.tsherpa.team35.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<Notice> getList(Page page);
    Notice getNotice(@Param("no") int no);
    int getCount(Page page);
    void noticeInsert(@Param("param") Notice param);
    void noticeUpdate(@Param("param") Notice param);
    void noticeDelete(int no);
    void cntUpdate(int no);
}
