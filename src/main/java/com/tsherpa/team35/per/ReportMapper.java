package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.Report;
import com.tsherpa.team35.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ReportMapper {
    void reportInsert(Report report);
    List<Report> reportMarketList();
    List<Report> reportReqList();
    int reportTotalMar();
    int reportTotalReq();
    int reportCountMar(int marketNo);
    int reportCountReq(int reqNo);
}
