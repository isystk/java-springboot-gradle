package com.isystk.sample.web.front.controller.api.v1.auth;

import com.isystk.sample.common.helper.UserHelper;
import com.isystk.sample.common.util.ObjectMapperUtils;
import com.isystk.sample.domain.dao.AuditInfoHolder;
import com.isystk.sample.domain.entity.TUser;
import com.isystk.sample.web.base.controller.api.AbstractRestController;
import com.isystk.sample.web.base.controller.api.resource.Resource;
import com.isystk.sample.web.front.dto.auth.AuthUserDto;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;

import static com.isystk.sample.common.FrontUrl.*;

@RestController
public class AuthRestController extends AbstractRestController {

  @Override
  public String getFunctionName() {
    return "API_AUTH";
  }

  @Autowired
  UserHelper userHelper;

  /**
   * ログインチェック
   *
   * @return
   */
  @PostMapping(API_V1_LOGIN_CHECK_URL)
  public Resource loginCheck(HttpSession session) {

    String userId = AuditInfoHolder.getAuditUser();

    Resource resource = resourceFactory.create();
    if (Optional.of(userId).isEmpty()) {
      // 未ログイン状態です
      return null;
    }

    TUser tUser = userHelper.getUser();
    AuthUserDto dto = ObjectMapperUtils.map(tUser, AuthUserDto.class);
    dto.setSessionId(session.getId());
    resource.setData(Arrays.asList(dto));
    resource.setMessage("ログイン状態です。");

    return resource;
  }

  /**
   * ログイン成功
   *
   * @return
   */
  @PostMapping(API_V1_LOGIN_SUCCESS_URL)
  public Resource loginSuccess(HttpSession session) {

    // 最終ログイン日時を更新します。
    userHelper.updateLastLogin();

    Resource resource = resourceFactory.create();
    TUser tUser = userHelper.getUser();
    AuthUserDto dto = ObjectMapperUtils.map(tUser, AuthUserDto.class);
    dto.setSessionId(session.getId());
    resource.setData(Arrays.asList(dto));
    resource.setMessage(getMessage("login.success"));

    return resource;
  }

  /**
   * ログイン失敗
   *
   * @return
   */
  @GetMapping(API_V1_LOGIN_FAILURE_URL)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Resource loginFailure(HttpServletResponse response) {
    Resource resource = resourceFactory.create();
    resource.setMessage(getMessage("login.failed"));
    return resource;
  }

  /**
   * ログアウト
   *
   * @return
   */
  @GetMapping(API_V1_LOGOUT_SUCCESS_URL)
  public Resource logoutSuccess() {
    Resource resource = resourceFactory.create();
    resource.setMessage(getMessage("login.success"));

    return resource;
  }

}
