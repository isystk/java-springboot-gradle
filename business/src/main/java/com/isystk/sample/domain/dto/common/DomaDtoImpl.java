package com.isystk.sample.domain.dto.common;

import com.isystk.sample.domain.dao.DefaultEntityListener;
import org.seasar.doma.Entity;

import java.io.Serializable;

@SuppressWarnings("serial")
@Entity(listener = DefaultEntityListener.class) // 自動的にシステム制御項目を更新するためにリスナーを指定する
public abstract class DomaDtoImpl implements DomaDto, Serializable {

}
