package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    void deleteByOrgId(Long orgId);

    List<Member> findAllByOrgId(Long orgId);
}
