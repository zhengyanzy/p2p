package com.zy.p2p.base.utils;


import lombok.Data;

import java.io.Serializable;
import java.util.Map;

//给ajax请求返回的数据
@Data
public class JsonResult implements Serializable {
    private boolean success = true;
    private String msg;

    public JsonResult() {
    }

    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
