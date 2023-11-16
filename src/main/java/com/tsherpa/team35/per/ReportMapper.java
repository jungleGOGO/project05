package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.Report;
import com.tsherpa.team35.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ReportMapper {
    void reportMarInsert(Report report);
    void reportReqInsert(Report report);
    List<Report> reportMarketList(Page page);
    List<Report> reportReqList(Page page);
    int reportTotalMar(Page page);
    int reportTotalReq(Page page);
    int reportCountMar(int marketNo);
    int reportCountReq(int reqNo);
    List<Report> reasonReqList(int reqNo);
    List<Report> reasonMarList(int marketNo);
}
