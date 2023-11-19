package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.Request;
import com.tsherpa.team35.per.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestMapper requestMapper;



    public void reqInsert(Request request) throws Exception{
        requestMapper.requestInsert(request);
    }
    public List<Request> requestList() throws Exception{
        return requestMapper.requestList();
    }
    public Request requestDetail(int reqNo) throws Exception{
        return requestMapper.requestDetail(reqNo);
    }
    public List<Request> getMoreRequests(int offset, int limit) {
        return requestMapper.getMoreRequests(offset, limit);
    }
    public void requestEdit(Request request) throws Exception {
        requestMapper.requestEdit(request);
    }

    public void requestDelete(int reqNo) throws Exception{
        requestMapper.requestDelete(reqNo);
    }

    public List<Request> allRequest() throws Exception{
        return requestMapper.allRequest();
    }

    public void requestEditAll(Request request) throws Exception {
        requestMapper.requestEditAll(request);
    }

    public void readable(int readable,int reqNo){
        requestMapper.readable(readable, reqNo);
    }

    public int getRequestCnt() throws Exception {
        return requestMapper.getRequestCnt();
    }

    public List<Request> getRequestListForMain() throws Exception {
        return requestMapper.getRequestListForMain();
    }

}
