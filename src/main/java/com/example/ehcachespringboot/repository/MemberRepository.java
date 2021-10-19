package com.example.ehcachespringboot.repository;

import com.example.ehcachespringboot.model.Member;

public interface MemberRepository {

	Member findByNameNoCache(String name);
	Member findByNameCache(String name);
	void refresh(String name);

}
