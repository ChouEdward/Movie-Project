package com.moviesapp.project.dm_project.data.bean;

import com.moviesapp.project.dm_project.data.ModuleAddressBean;

import java.util.List;

public class GetMoviesResponse extends BaseResponse {

    public List<ModuleAddressBean> getModuleAddressBeans() {
        return moduleAddressBeans;
    }

    public void setModuleAddressBeans(List<ModuleAddressBean> moduleAddressBeans) {
        this.moduleAddressBeans = moduleAddressBeans;
    }

    public List<ModuleAddressBean> moduleAddressBeans;

    public GetMoviesResponse(int code, String msg) {
        super(code, msg);
    }

}
