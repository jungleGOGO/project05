package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.DetailVO;
import com.tsherpa.team35.entity.MainVO;
import com.tsherpa.team35.entity.Market;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MarketMapper {
    public void marketInsert(Market market) throws Exception;
    public List<MainVO> mainVOList() throws Exception;
    public Market marketDetail(int marketNo) throws Exception;
    public DetailVO detailVOList(int marketNo) throws Exception;
    public MainVO mainlistForDetailVOList(int marketNo) throws Exception;
    public void marketDelete(int marketNo) throws Exception;
    public void readable(int readable,int marketNo);

    public int cntSell(String loginId);
    public List<MainVO> userMainVOList(String loginId);
    public List<MainVO> getInfo(String loginId) throws Exception;
    public int getMarketCnt() throws Exception;
    public List<MainVO> getMarketListForMain() throws Exception;

}
