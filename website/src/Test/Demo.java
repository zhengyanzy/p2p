import com.sun.tracing.dtrace.Attributes;
import com.zy.p2p.base.domain.Account;
import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.mapper.LogininfoMapper;
import com.zy.p2p.base.service.IAccountService;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.service.LogininfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;
import sun.swing.SwingUtilities2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_website.xml","classpath:mybatis-config.xml","classpath:spring-context-druid.xml"})
public class Demo {
    @Autowired
    public LogininfoService logininfoService;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IAccountService accountService;

    @Autowired
    public LogininfoMapper logininfoMapper;

    @Test
    public void test1(){
    }
}
