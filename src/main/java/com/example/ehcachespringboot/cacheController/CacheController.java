package com.example.ehcachespringboot.cacheController;

import com.example.ehcachespringboot.model.Member;
import com.example.ehcachespringboot.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableCaching
@RestController
public class CacheController {

	private static final Logger log = LoggerFactory.getLogger(CacheController.class);

	private final MemberRepository memberRepository;

	public CacheController(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@GetMapping("/member/nocache/{name}")
	public Member getNoCacheMember(@PathVariable String name){

		long start = System.currentTimeMillis();
		Member member = memberRepository.findByNameNoCache(name);
		long end = System.currentTimeMillis();

		log.info(name+ "의 NoCache 수행시간 : "+ Long.toString(end-start));

		return member;
	}

	@GetMapping("/member/cache/{name}")
	public Member getCacheMember(@PathVariable String name){

		long start = System.currentTimeMillis();
		Member member = memberRepository.findByNameCache(name);
		long end = System.currentTimeMillis();

		log.info(name+ "의 Cache 수행시간 : "+ Long.toString(end-start));

		return member;
	}

	@GetMapping("/member/refresh/{name}")
	public String refresh(@PathVariable String name){
		memberRepository.refresh(name);
		return "cache clear!";
	}

	@GetMapping("/")
	public String index(){
		return "ehcache!";
	}


}
