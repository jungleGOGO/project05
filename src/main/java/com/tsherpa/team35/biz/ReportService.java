package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.Report;
import com.tsherpa.team35.per.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportMapper reportMapper;

    public void reportInsert(Report report) { reportMapper.reportInsert(report); }
}
