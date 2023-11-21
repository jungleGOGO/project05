package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.DetailVO;
import com.tsherpa.team35.entity.MainVO;
import com.tsherpa.team35.entity.Market;
import com.tsherpa.team35.per.MainphotoMapper;
import com.tsherpa.team35.per.MarketMapper;
import com.tsherpa.team35.per.PhotosMapper;
import com.tsherpa.team35.util.Page;
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

    public List<MainVO> mainVOList(Page page) throws Exception{
       return marketMapper.mainVOList(page);
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
    @Transactional
    public void marketEdit(Market market) throws Exception{
        marketMapper.marketEdit(market);
        if(market.getFileInfoList().get(0).getSaveFolder() != null && market.getMainphotoList().get(0).getSaveFolder()!= null){
            photosMapper.photosDelete(market.getMarketNo());
            mainphotoMapper.mainphotoDelete(market.getMarketNo());
            photosMapper.photosInsert(market);
            mainphotoMapper.mainphotoInsert(market);
        }

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
    public List<MainVO> likeMarketList(String loginId) {
        return marketMapper.likeMarketList(loginId);
    }


    public int mainListCount(Page page){ return marketMapper.mainListCount(page); }
}
