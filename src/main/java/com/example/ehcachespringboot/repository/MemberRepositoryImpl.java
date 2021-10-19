package com.example.ehcachespringboot.repository;

import com.example.ehcachespringboot.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	private static final Logger log = LoggerFactory.getLogger(MemberRepositoryImpl.class);

	@Override
	public Member findByNameNoCache(String name) {
		slowQuery(2000);
		return new Member(0, name+"@gmail.com", name);
	}

	@Cacheable(value = "findMemberCache",key = "#name")
	@Override
	public Member findByNameCache(String name) {
		slowQuery(2000);
		return new Member(0, name+ "@gmail.com", name);
	}

	@CacheEvict(value = "findMemberCache", key = "#name")
	@Override
	public void refresh(String name) {
		log.info(name + "의 Cache Clear!");
	}

	private void slowQuery(int seconds) {
		try {
			Thread.sleep(seconds);
		}catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}
