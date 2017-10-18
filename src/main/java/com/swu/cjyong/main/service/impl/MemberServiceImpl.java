package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.MemberRepository;
import com.swu.cjyong.main.entity.Member;
import com.swu.cjyong.main.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member getByMemberId(Long id) {
        return memberRepository.findOne(id);
    }

    public Member addMember(Member member){
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        Member oldOne = memberRepository.findOne(member.getId());
        if (oldOne != null) {
            memberRepository.saveAndFlush(member);
        }
        return memberRepository.findOne(oldOne.getId());
    }

    public Member deleteMember(long id) {
        Member oldOne = memberRepository.findOne(id);
        if (oldOne != null) {
            memberRepository.delete(id);
        }

        return oldOne;
    }

    public void deleteByOrgId(long orgId) {
        memberRepository.deleteByOrgId(orgId);
    }

    public List<Member> getMembersByOrgId(long orgId) {
        return memberRepository.findAllByOrgId(orgId);
    }
}
