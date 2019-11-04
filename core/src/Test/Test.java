import com.alibaba.druid.filter.AutoLoad;
import com.sun.tracing.dtrace.Attributes;
import com.zy.p2p.base.domain.*;
import com.zy.p2p.base.mapper.*;
import com.zy.p2p.base.query.*;
import com.zy.p2p.base.service.IMailService;
import com.zy.p2p.base.service.IRealAuthService;
import com.zy.p2p.base.utils.BidConst;
import com.zy.p2p.base.utils.BitStatesUtils;
import com.zy.p2p.base.utils.UserContext;
import com.zy.p2p.business.domain.Bid;
import com.zy.p2p.business.domain.BidRequest;
import com.zy.p2p.business.mapper.BidMapper;
import com.zy.p2p.business.mapper.BidRequestMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.zip.Inflater;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:mybatis-config.xml", "classpath:spring-context-druid.xml"})
public class Test {
//    @Autowired
//    private UserinfoMapper userinfoMapper;
//
//    @Autowired
//    private IMailService mailService;
//
//    @Autowired
//    private IRealAuthService realAuthService;
//    @Autowired
//    private RealAuthMapper realAuthMapper;
//
//    @Autowired
//    private VedioAuthMapper vedioAuthMapper;
//    @Autowired
//    public UserfileMapper userfileMapper;

    @Autowired
    private BidRequestMapper bidRequestMapper;
    @Autowired
    private BidMapper bidMapper;
    @Autowired
    private LogininfoMapper logininfoMapper;
}
