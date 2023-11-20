package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.DetailVO;
import com.tsherpa.team35.entity.MainVO;
import com.tsherpa.team35.entity.Market;
import com.tsherpa.team35.per.MainphotoMapper;
import com.tsherpa.team35.per.MarketMapper;
import com.tsherpa.team35.per.PhotosMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MarketService {
    @Autowired
    private MarketMapper marketMapper;
    @Autowired
    private PhotosMapper photosMapper;
    @Autowired
    private MainphotoMapper mainphotoMapper;
    @Transactional
    public void marketInsert(Market market) throws Exception {
        marketMapper.marketInsert(market);
        photosMapper.photosInsert(market);
        mainphotoMapper.mainphotoInsert(market);

    }

    public List<MainVO> mainVOList() throws Exception{
       return marketMapper.mainVOList();
    }

    public DetailVO detailVOList(int marketNo) throws Exception{
        return marketMapper.detailVOList(marketNo);
    }

    public MainVO mainlistForDetailVOList(int marketNo) throws Exception{
        return marketMapper.mainlistForDetailVOList(marketNo);
    }

    @Transactional
    public void marketDelete(int marketNo) throws Exception{
        marketMapper.marketDelete(marketNo);
        photosMapper.photosDelete(marketNo);
        mainphotoMapper.mainphotoDelete(marketNo);
    }

    public Market marketDetail(int marketNo) throws Exception {
        return marketMapper.marketDetail(marketNo);
    }

    public void readable(int readable,int marketNo){
        marketMapper.readable(readable, marketNo);
    }

    public int getMarketCnt() throws Exception {
        return marketMapper.getMarketCnt();
    }

    public List<MainVO> getMarketListForMain() throws Exception {
        return marketMapper.getMarketListForMain();
    }

    public List<MainVO> getInfo(String loginId) throws Exception {
        return marketMapper.getInfo(loginId);
    }

    public int cntSell(String loginId) {
        return marketMapper.cntSell(loginId);
    }

    public List<MainVO> userMainVOList(String loginId) {
        return marketMapper.userMainVOList(loginId);
    }

}
