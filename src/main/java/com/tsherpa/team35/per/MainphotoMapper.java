package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.Mainphoto;
import com.tsherpa.team35.entity.Market;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MainphotoMapper {
    public List<Mainphoto> mainphotoList(int marketNo) throws Exception;
    public void mainphotoInsert(Market market) throws Exception;

}
