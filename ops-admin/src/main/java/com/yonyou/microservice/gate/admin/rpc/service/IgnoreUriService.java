package com.yonyou.microservice.gate.admin.rpc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.microservice.gate.admin.biz.IgnoreUriBiz;
import com.yonyou.microservice.gate.admin.entity.AuthProvider;
import com.yonyou.microservice.gate.admin.entity.IgnoreUri;
import com.yonyou.microservice.gate.common.vo.authority.IgnoreUriInfo;
import com.yonyou.microservice.gate.common.vo.user.AuthProviderInfo;

/**
 * @author joy
 */
@Service
public class IgnoreUriService {
    @Autowired
    private IgnoreUriBiz ignoreUriBiz;
    
    public  List<IgnoreUriInfo> getIgnoreUris() {
    	List<IgnoreUri> list=ignoreUriBiz.selectListAll();
    	List<IgnoreUriInfo> r=new ArrayList();
    	for(IgnoreUri p:list){
    		IgnoreUriInfo i=new IgnoreUriInfo();
    		i.setId(p.getId());
    		i.setUri(p.getUri());
    		r.add(i);
    	}
        return r;
    }
}
