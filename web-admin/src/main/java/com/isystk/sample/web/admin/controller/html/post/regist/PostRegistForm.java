package com.isystk.sample.web.admin.controller.html.post.regist;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.isystk.sample.web.base.controller.html.BaseForm;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRegistForm extends BaseForm {

  private static final long serialVersionUID = 7593564324192730932L;

  @NotNull
  Integer userId;

  @NotEmpty
  String title;

  @NotEmpty
  String text;

  @NotEmpty
  List<@Digits(integer = 9, fraction = 0) Integer> postImageId;

  List<@Digits(integer = 9, fraction = 0) Integer> postTagId;

}
