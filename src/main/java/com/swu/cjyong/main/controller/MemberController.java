package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.Member;
import com.swu.cjyong.main.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "获取单个团员信息")
    @GetMapping("/getById/{id}")
    public ResponseEntity<Member> getByMemberId(@PathVariable Long id) {
        Member result = memberService.getByMemberId(id);
        return new ResponseEntity<>(result == null ? Member.empty() : result, HttpStatus.OK);
    }

    @ApiOperation(value = "添加团员信息")
    @PostMapping("/addMember")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member result = memberService.addMember(member.setId(null));
        return new ResponseEntity<>(result == null ? Member.empty() : result, HttpStatus.OK);
    }

    @ApiOperation(value = "更新团员信息")
    @PutMapping("/updateMember")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        Member result = memberService.updateMember(member);
        return new ResponseEntity<>(result == null ? Member.empty() : result, HttpStatus.OK);
    }

    @ApiOperation(value = "根据ID删除团员信息")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable long id) {
        Member result = memberService.deleteMember(id);
        return new ResponseEntity<>(result == null ? Member.empty() : result, HttpStatus.OK);
    }

    @ApiOperation(value = "根据组织ID删除团员信息")
    @DeleteMapping("/deleteByOrgId/{orgId}")
    public Long deleteByOrgId(@PathVariable long orgId) {
        try {
            memberService.deleteByOrgId(orgId);
        } catch (Exception e) {
            return 0L;
        }
        return 1L;

    }

    @ApiOperation(value = "根据组织ID获取团员信息")
    @GetMapping("/getByOrgId/{orgId}")
    public ResponseEntity<List<Member>> getMembersByOrgId(@PathVariable long orgId) {
        List<Member> members = memberService.getMembersByOrgId(orgId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
}
