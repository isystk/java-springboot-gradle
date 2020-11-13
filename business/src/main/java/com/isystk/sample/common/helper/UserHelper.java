package com.isystk.sample.common.helper;

import com.isystk.sample.common.exception.NoDataFoundException;
import com.isystk.sample.common.util.DateUtils;
import com.isystk.sample.domain.dao.AuditInfoHolder;
import com.isystk.sample.domain.dao.TUserDao;
import com.isystk.sample.domain.dto.TUserCriteria;
import com.isystk.sample.domain.entity.TUser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ユーザーヘルパー
 */
@Component
public class UserHelper {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserHelper.class);
    @Autowired
	TUserDao tUserDao;

	/**
	 * ユーザーを全件取得します。
	 *
	 * @return
	 */
	public List<TUser> getUserList() {
		TUserCriteria criteria = new TUserCriteria();
		criteria.setDeleteFlgFalse(true);
		return tUserDao.findAll(criteria);
	}

	/**
	 * ユーザーを取得します。
	 *
	 * @return
	 */
	public TUser getLoginUser(Integer userId) {
		TUserCriteria criteria = new TUserCriteria();
		criteria.setUserIdEq(userId);
		return tUserDao.findOne(criteria).orElseThrow(
				() -> new NoDataFoundException("userId=" + userId + "のデータが見つかりません。"));
	}

	/**
	 * ログインユーザーを取得します。
	 *
	 * @return
	 */
	public Integer getLoginUserId() {
		return getLoginUser().getUserId();
	}

	/**
	 * ログインユーザーを取得します。
	 *
	 * @return
	 */
	public TUser getLoginUser() {
		TUserCriteria criteria = new TUserCriteria();
		criteria.setEmailEq(AuditInfoHolder.getAuditUser());
		return tUserDao.findOne(criteria).orElseThrow(
				() -> new NoDataFoundException("email=" + AuditInfoHolder.getAuditUser() + "のデータが見つかりません。"));
	}

	/**
	 * 最終ログイン日時を更新します。
	 *
	 * @return
	 */
	public void updateLastLogin() {
		TUser tUser = getLoginUser();
		tUser.setLastLoginTime(DateUtils.getNow());
		tUserDao.update(tUser);
	}

}
