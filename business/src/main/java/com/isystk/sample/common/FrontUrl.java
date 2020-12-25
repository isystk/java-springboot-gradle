package com.isystk.sample.common;

/**
 * フロント画面 URL
 */
public class FrontUrl {

	/** ---- URLs ---- **/
	public static final String TOP = "/";

	public static final String POST = "/post";

	public static final String POST_TAG = "/post/tag";

	public static final String SYSTEM_MAIL = "/system/mail";

	public static final String MEMBER = "/member";

	public static final String MEMBER_POST_REGIST = "/member/post/regist";

	public static final String MEMBER_POST_EDIT = "/member/post/edit";

	public static final String MEMBER_POST_DELETE = "/member/post/delete";

	public static final String ENTRY_REGIST = "/entry/regist";

	public static final String ENTRY_REMIND = "/entry/remind";

	public static final String ENTRY_REMIND_CONFIG = "/entry/remind/config";

	/** ---- APIｓ ---- **/

	public static final String API_V1_AUTH = "/api/v1/authenticate";

	public static final String API_V1_LOGIN_CHECK_URL = "/api/v1/loginCheck";

	public static final String API_V1_LOGIN_SUCCESS_URL = "/api/v1/loginSuccess";

	public static final String API_V1_LOGIN_FAILURE_URL = "/api/v1/loginFailure";

	public static final String API_V1_LOGOUT_URL = "/api/v1/logout";

	public static final String API_V1_LOGOUT_SUCCESS_URL = "/api/v1/logoutSuccess";

	public static final String API_V1_POSTS = "/api/v1/posts";

	public static final String API_V1_MEMBER = "/api/v1/member";

	public static final String API_V1_MEMBER_POSTS = "/api/v1/member/posts";

	public static final String API_V1_MEMBER_POSTS_DETAIL = "/api/v1/member/posts/p{postId}";

	public static final String API_V1_MEMBER_POSTS_EDIT = "/api/v1/member/posts/p{postId}/edit";

	public static final String API_V1_MEMBER_POSTS_DELETE = "/api/v1/member/posts/p{postId}/delete";

	public static final String API_V1_MEMBER_POSTS_NEW = "/api/v1/member/posts/new";

	public static final String API_V1_MEMBER_FILEUPLOAD = "/api/v1/member/fileupload";

	public static final String API_V1_ENTRY_REGIST = "/api/v1/entry/regist";

	public static final String API_V1_ENTRY_REMIND = "/api/v1/entry/remind";

	public static final String API_V1_ENTRY_REMIND_CONFIG = "/api/v1/entry/remind/config";

	public static final String API_V1_COMMON_CONST = "/api/v1/common/const";

}