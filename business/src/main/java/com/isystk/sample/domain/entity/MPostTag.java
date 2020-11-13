package com.isystk.sample.domain.entity;

import com.isystk.sample.domain.dto.common.DomaDtoImpl;
import org.seasar.doma.*;

import java.time.LocalDateTime;

/**
 * 
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "m_post_tag")
public class MPostTag extends DomaDtoImpl {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;


    /** 投稿タグID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_TAG_ID")
    Integer postTagId;

    /** 名称 */
    @Column(name = "NAME")
    String name;

    /** 登録日時 */
    @Column(name = "REGIST_TIME")
    LocalDateTime registTime;

    /** 更新日時 */
    @Column(name = "UPDATE_TIME")
    LocalDateTime updateTime;

    /** 削除フラグ */
    @Column(name = "DELETE_FLG")
    Boolean deleteFlg;

    /** 楽観チェック用バージョン */
    @Version
    @Column(name = "VERSION")
    Long version;

    public Integer getPostTagId() {
        return this.postTagId;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getRegistTime() {
        return this.registTime;
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    public Boolean getDeleteFlg() {
        return this.deleteFlg;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setPostTagId(Integer postTagId) {
        this.postTagId = postTagId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistTime(LocalDateTime registTime) {
        this.registTime = registTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void setDeleteFlg(Boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}