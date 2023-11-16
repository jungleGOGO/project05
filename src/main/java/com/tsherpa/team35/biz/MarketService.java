package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.Market;
import com.tsherpa.team35.entity.Notice;
import com.tsherpa.team35.per.MarketMapper;
import com.tsherpa.team35.per.PhotosMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarketService {
    @Autowired
    private MarketMapper marketMapper;
    @Autowired
    private PhotosMapper photosMapper;
    @Transactional
    public void marketInsert(Market market) throws Exception {
        marketMapper.marketInsert(market);
        photosMapper.photosInsert(market);
    }

    public Market getMarket(@Param("no") int no) {
        return marketMapper.getMarket(no);
    }

}
