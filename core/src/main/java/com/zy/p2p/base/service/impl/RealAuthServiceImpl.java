package com.zy.p2p.base.service.impl;

import java.util.Date;
import java.util.List;

import com.zy.p2p.base.domain.RealAuth;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.event.RealAuthSuccessEvent;
import com.zy.p2p.base.mapper.RealAuthMapper;
import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.query.RealAuthQueryObject;
import com.zy.p2p.base.service.IRealAuthService;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.utils.BitStatesUtils;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class RealAuthServiceImpl implements IRealAuthService {

	@Autowired
	private RealAuthMapper realAuthMapper;

	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IRealAuthService realAuthService;

	@Autowired
	private ApplicationContext ctx;

	@Value("${jdbc.timeout}")
	private String key;

	@Override
	public RealAuth get(Long id) {
		return realAuthMapper.selectByPrimaryKey(id);
	}


	/**
	 *	后台分页查询 审核实名认证信息
	 */
	@Override
	public PageResult query(RealAuthQueryObject qo) {
		int count = this.realAuthMapper.queryForCount(qo);
		if (count > 0) {
			List<RealAuth> list = this.realAuthMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(),
					qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	/**
	 *  前台申请实名认证
	 */
	@Override
	public void apply(RealAuth realAuth) {
		Userinfo userinfo = this.userinfoService.getUserinfo();
		// 判断当前用户没有实名认证并且当前用户不处于待审核状态
		if (!userinfo.getIsRealAuth() && userinfo.getRealAuthId() == null) {
			//判断实名对象中的数据是否为空（略,前端也需要验证一遍）
			//保存一个实名认证对象;
			realAuth.setState(RealAuth.STATE_NORMAL);
			realAuth.setApplier(UserContext.getCurrent());
			realAuth.setApplyTime(new Date());
			this.realAuthMapper.insert(realAuth);

			//把实名认证的id设置给userinfo
			userinfo.setRealAuthId(realAuth.getId());
			this.userinfoService.update(userinfo);
		}
	}

	/**
	 * 后台实名认证审核
	 * @param id :     RealAuth 对象id
	 * @param remark   审核的附加信息
	 * @param state    前端按钮控制审核的通过或者不通过
	 */
	@Override
	public void audit(Long id, String remark, int state) {
		// 根据id得到实名认证对象
		RealAuth realAuth = this.get(id);
		// 如果对象存在,并且对象处于待审核状态
		if (realAuth != null && realAuth.getState() == RealAuth.STATE_NORMAL) {
			// 1,设置通用属性;
			realAuth.setAuditor(UserContext.getCurrent());
			realAuth.setAuditTime(new Date());
			realAuth.setState(state);
			Userinfo applier = this.userinfoService.get(realAuth.getApplier().getId());
			// 通过审核
			if (state == RealAuth.STATE_AUDIT) {
				// 3,如果状态是审核通过;
				// 1,保证用户处于未审核状态;
				if (!applier.getIsRealAuth()) {
					// 2,添加审核的状态码;设置userinfo上面的冗余数据;重新realauthid;
					applier.addState(BitStatesUtils.OP_REAL_AUTH);
					//设置两条冗余数据
					applier.setRealName(realAuth.getRealName());
					applier.setIdNumber(realAuth.getIdNumber());
					applier.setRealAuthId(realAuth.getId());
				}
				// 发布一个实名认证审核通过的消息
				ctx.publishEvent(new RealAuthSuccessEvent(this, realAuth));
			}
			// 拒绝通过
			else {
				// 1,userinfo中的realauthid设置为空,等待用户重新申请实名认证
				applier.setRealAuthId(null);
			}
			this.userinfoService.update(applier);
			this.realAuthMapper.updateByPrimaryKey(realAuth);
		}
	}
}
