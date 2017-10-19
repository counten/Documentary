package com.swu.cjyong.main.service;

import com.swu.cjyong.main.entity.Member;

import java.util.List;

public interface MemberService {
    /**
     * 通过ID获取团员信息
     *
     * @param id
     * @return
     */
    Member getByMemberId(Long id);

    /**
     * 添加团员信息
     *
     * @param member
     * @return
     */
    Member addMember(Member member);

    /**
     * 更新团员信息
     *
     * @param member
     * @return
     */
    Member updateMember(Member member);

    /**
     * 根据ID删除团员
     *
     * @param id
     * @return
     */
    Member deleteMember(long id);

    /**
     * 根据OrgId删除团员
     *
     * @param orgId
     */
    void deleteByOrgId(long orgId);

    /**
     * 根据OrgId获取团员信息
     *
     * @param orgId
     */
    List<Member> getMembersByOrgId(long orgId);
}
