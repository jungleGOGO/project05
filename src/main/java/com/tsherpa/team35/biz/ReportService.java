package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.Report;
import com.tsherpa.team35.per.ReportMapper;
import com.tsherpa.team35.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportMapper reportMapper;

    public void reportMarInsert(Report report) { reportMapper.reportMarInsert(report); }
    public void reportReqInsert(Report report) { reportMapper.reportReqInsert(report);};
    public List<Report> reportMarketList() { return reportMapper.reportMarketList(); }
    public List<Report> reportReqList() { return reportMapper.reportReqList(); }
    public int reportTotalMar() { return reportMapper.reportTotalMar(); }
    public int reportTotalReq() { return reportMapper.reportTotalReq(); }
    public int reportCountMar(int marketNo) { return reportMapper.reportCountMar(marketNo); }
    public int reportCountReq(int reqNo) { return reportMapper.reportCountReq(reqNo); }
}
