package com.isystk.sample.web.front.controller.api.v1.auth;

import com.isystk.sample.common.helper.UserHelper;
import com.isystk.sample.web.base.controller.api.AbstractRestController;
import com.isystk.sample.web.base.controller.api.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.isystk.sample.common.Const.*;
import static com.isystk.sample.common.FrontUrl.API_V1_AUTH;

@RestController
@RequestMapping(path = API_V1_AUTH, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthRestController extends AbstractRestController {

	@Override
	public String getFunctionName() {
		return "API_AUTH";
	}

	@Autowired
	UserHelper userHelper;

	/**
	 * ログイン成功
	 *
	 * @return
	 */
	@PostMapping(LOGIN_SUCCESS_URL)
	public Resource loginSuccess() {

		// 最終ログイン日時を更新します。
		userHelper.updateLastLogin();

		Resource resource = resourceFactory.create();
		resource.setMessage(getMessage("login.success"));

		return resource;
	}

	/**
	 * ログイン失敗
	 *
	 * @return
	 */
	@GetMapping(LOGIN_FAILURE_URL)
	public Resource loginFailure() {

		Resource resource = resourceFactory.create();
		resource.setMessage(getMessage("login.failed"));

		return resource;
	}

	/**
	 * タイムアウトした時
	 *
	 * @return
	 */
	@GetMapping(LOGIN_TIMEOUT_URL)
	public Resource loginTimeout() {
		Resource resource = resourceFactory.create();
		resource.setMessage(getMessage("login.timeout"));

		return resource;
	}

	/**
	 * ログアウト
	 *
	 * @return
	 */
	@GetMapping(LOGOUT_SUCCESS_URL)
	public Resource logoutSuccess() {
		Resource resource = resourceFactory.create();
		resource.setMessage(getMessage("login.success"));

		return resource;
	}

}
