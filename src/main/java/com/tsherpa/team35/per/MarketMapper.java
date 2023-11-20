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

    public void marketEdit(Market market) throws Exception;


    public void readable(int readable,int marketNo);

<<<<<<< HEAD
=======
    public int cntSell(String loginId);
    public List<MainVO> userMainVOList(String loginId);

>>>>>>> fae8ca9d1fc6b860fe4f5d4a5e05b84219fe843a
    public int getMarketCnt() throws Exception;
    public List<MainVO> getMarketListForMain() throws Exception;

}
