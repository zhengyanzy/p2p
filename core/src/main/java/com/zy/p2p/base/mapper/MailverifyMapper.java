package com.zy.p2p.base.mapper;

import com.zy.p2p.base.domain.Mailverify;
import java.util.List;

public interface MailverifyMapper {
    int insert(Mailverify record);

    /**
     *  更具uuid查询对象
     */
    Mailverify selectByUuid(String uuid);
}