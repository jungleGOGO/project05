package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.Mainphoto;
import com.tsherpa.team35.per.MainphotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class MainPhotoService {
    @Autowired
    private MainphotoMapper mainphotoMapper;
    public List<Mainphoto> mainphotoList(int marketNo) throws Exception{
        return mainphotoMapper.mainphotoList(marketNo);
    }
}
