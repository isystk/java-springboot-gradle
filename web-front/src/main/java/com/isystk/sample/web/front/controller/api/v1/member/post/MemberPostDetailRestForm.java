package com.isystk.sample.web.front.controller.api.v1.member.post;

import com.isystk.sample.web.base.controller.html.BaseForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class MemberPostDetailRestForm extends BaseForm {

	private static final long serialVersionUID = 7593564324192730932L;

	@NotNull
	Integer postId;

	@NotEmpty
	String title;

	@NotEmpty
	String text;

	@NotEmpty
	List<@Digits(integer = 9, fraction = 0) Integer> postImageId;

	List<@Digits(integer = 9, fraction = 0) Integer> postTagId;

}
