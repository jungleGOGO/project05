package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.Market;
import com.tsherpa.team35.entity.Photos;
import com.tsherpa.team35.per.PhotosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotosService {
    @Autowired
    private PhotosMapper photosMapper;

    public List<Photos> photosList(int marketNo) throws Exception{
      return  photosMapper.photosList(marketNo);
    }

    public void photosEdit(Market market) throws Exception{
        photosMapper.photosEdit(market);
    }
}
