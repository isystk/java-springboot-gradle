package com.isystk.sample.web.front.controller.api.v1.member.post;

import com.isystk.sample.common.exception.NoDataFoundException;
import com.isystk.sample.common.helper.UserHelper;
import com.isystk.sample.common.util.DateUtils;
import com.isystk.sample.common.util.ObjectMapperUtils;
import com.isystk.sample.common.values.ImageSuffix;
import com.isystk.sample.domain.entity.TPostImage;
import com.isystk.sample.domain.entity.TPostTag;
import com.isystk.sample.domain.repository.TPostRepository;
import com.isystk.sample.domain.repository.dto.TPostRepositoryDto;
import com.isystk.sample.solr.dto.SolrPost;
import com.isystk.sample.web.base.controller.api.AbstractRestController;
import com.isystk.sample.web.base.controller.api.resource.Resource;
import com.isystk.sample.web.base.controller.html.AbstractHtmlController;
import com.isystk.sample.web.front.controller.html.member.post.regist.MemberPostRegistForm;
import com.isystk.sample.web.front.dto.FrontPostDto;
import com.isystk.sample.web.front.service.PostService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.isystk.sample.common.Const.MESSAGE_SUCCESS;
import static com.isystk.sample.common.Const.NO_DATA_FOUND_ERROR;
import static com.isystk.sample.common.FrontUrl.API_V1_MEMBER_POSTS_DETAIL;
import static com.isystk.sample.common.FrontUrl.API_V1_MEMBER_POSTS_EDIT;
import static com.isystk.sample.common.FrontUrl.API_V1_MEMBER_POSTS_NEW;
import static com.isystk.sample.common.FrontUrl.API_V1_MEMBER_POSTS_DELETE;

@RestController
@Slf4j
@SessionAttributes(types = { MemberPostDetailRestForm.class })
public class MemberPostDetailRestController extends AbstractRestController {

	@Autowired
	PostService postService;

	@Autowired
	UserHelper userHelper;

	@Autowired
	MemberPostDetailRestValidator memberPostDetailRestValidator;

	@ModelAttribute("memberPostDetailRestForm")
	public MemberPostDetailRestForm memberPostDetailRestForm() {
		return new MemberPostDetailRestForm();
	}

	@InitBinder("memberPostDetailRestForm")
	public void validatorBinder(WebDataBinder binder) {
		binder.addValidators(memberPostDetailRestValidator);
	}

	@Override
	public String getFunctionName() {
		return "API_MEMBER_POSTS_DETAIL";
	}

	/**
	 * 指定した投稿IDに紐づく自分の投稿データを取得します。
	 *
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping(API_V1_MEMBER_POSTS_DETAIL)
	public Resource editIndex(@ModelAttribute MemberPostDetailRestForm form, Model model) {

//		// SessionAttributeを再生成する
//		model.addAttribute("memberPostDetailRestForm", new MemberPostEditForm());

		// 1件取得する
		val post = postService.findMyDataById(form.getPostId());

		Resource resource = resourceFactory.create();
		resource.setData(Arrays.asList(post.orElse(new FrontPostDto())));
		resource.setMessage(getMessage(MESSAGE_SUCCESS));

		return resource;
	}

	/**
	 * 更新処理
	 *
	 * @param form
	 * @param br
	 * @param sessionStatus
	 * @param attributes
	 * @return
	 */
	@PutMapping(API_V1_MEMBER_POSTS_EDIT)
	public Resource update(@Validated @ModelAttribute MemberPostDetailRestForm form, BindingResult br,
								 SessionStatus sessionStatus, RedirectAttributes attributes) {

		Resource resource = resourceFactory.create();

		// 入力チェックエラーがある場合は、元の画面にもどる
		if (br.hasErrors()) {
			resource.setMessage("入力エラー");
			return resource;
		}

		// 入力値を詰め替える
		val tPostDto = ObjectMapperUtils.map(form, TPostRepositoryDto.class);
		// 投稿画像
		tPostDto.setTPostImageList(
				Optional.ofNullable(form.getPostImageId())
						.map(list -> list.stream()
								.map(imageId -> {
									TPostImage tPostImage = new TPostImage();
									tPostImage.setImageId(imageId);
									return tPostImage;
								})
								.collect(Collectors.toList())
						)
						.orElse(null)
		);
		// 投稿タグ
		tPostDto.setTPostTagList(
				Optional.ofNullable(form.getPostTagId())
						.map(list -> list.stream()
								.map(tagId -> {
									TPostTag tPostTag = new TPostTag();
									tPostTag.setPostTagId(tagId);
									return tPostTag;
								})
								.collect(Collectors.toList())
						)
						.orElse(null)
		);
		// 更新する
		postService.update(tPostDto);

		// セッションのmemberPostFormをクリアする
		sessionStatus.setComplete();

		resource.setMessage(getMessage(MESSAGE_SUCCESS));

		return resource;
	}

	/**
	 * 登録処理
	 *
	 * @param form
	 * @param br
	 * @param sessionStatus
	 * @param attributes
	 * @return
	 */
	@PostMapping(API_V1_MEMBER_POSTS_NEW)
	public Resource regist(@Validated @ModelAttribute MemberPostRegistForm form, BindingResult br,
								 SessionStatus sessionStatus, RedirectAttributes attributes) {

		Resource resource = resourceFactory.create();

		// 入力チェックエラーがある場合は、元の画面にもどる
		if (br.hasErrors()) {
			resource.setMessage("入力エラー");
			return resource;
		}

		// 入力値からDTOを作成する
		val tPostDto = ObjectMapperUtils.map(form, TPostRepositoryDto.class);
		// ログインユーザーID
		tPostDto.setUserId(userHelper.getLoginUserId());
		// 投稿画像
		tPostDto.setTPostImageList(
				Optional.ofNullable(form.getPostImageId())
						.map(list -> list.stream()
								.map(imageId -> {
									TPostImage tPostImage = new TPostImage();
									tPostImage.setImageId(imageId);
									return tPostImage;
								})
								.collect(Collectors.toList())
						)
						.orElse(null)
		);
		// 投稿タグ
		tPostDto.setTPostTagList(
				Optional.ofNullable(form.getPostTagId())
						.map(list -> list.stream()
								.map(tagId -> {
									TPostTag tPostTag = new TPostTag();
									tPostTag.setPostTagId(tagId);
									return tPostTag;
								})
								.collect(Collectors.toList())
						)
						.orElse(null)
		);
		val postId = postService.create(tPostDto);

		resource.setMessage(getMessage(MESSAGE_SUCCESS));

		return resource;
	}

	/**
	 * 削除処理
	 *
	 * @param postId
	 * @return
	 */
	@DeleteMapping(API_V1_MEMBER_POSTS_DELETE)
	public Resource delete(@PathVariable Integer postId) {
		postService.delete(postId);

		Resource resource = resourceFactory.create();
		resource.setMessage(getMessage(MESSAGE_SUCCESS));
		return resource;
	}

}
