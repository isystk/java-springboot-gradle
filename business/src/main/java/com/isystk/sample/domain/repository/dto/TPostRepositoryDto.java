package com.isystk.sample.domain.repository.dto;

import com.isystk.sample.domain.entity.TPost;
import com.isystk.sample.domain.entity.TPostImage;
import com.isystk.sample.domain.entity.TPostTag;
import com.isystk.sample.domain.entity.TUser;

import java.util.List;

public class TPostRepositoryDto extends TPost {

	TUser tUser;

	List<TPostImage> tPostImageList;

	List<TPostTag> tPostTagList;

	public TUser getTUser() {
		return this.tUser;
	}

	public List<TPostImage> getTPostImageList() {
		return this.tPostImageList;
	}

	public List<TPostTag> getTPostTagList() {
		return this.tPostTagList;
	}

	public void setTUser(TUser tUser) {
		this.tUser = tUser;
	}

	public void setTPostImageList(List<TPostImage> tPostImageList) {
		this.tPostImageList = tPostImageList;
	}

	public void setTPostTagList(List<TPostTag> tPostTagList) {
		this.tPostTagList = tPostTagList;
	}
}