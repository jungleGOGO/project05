package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.Report;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ReportMapper {
    void reportInsert(Report report);
}
