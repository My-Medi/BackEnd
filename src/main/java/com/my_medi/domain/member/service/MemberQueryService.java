package com.my_medi.domain.member.service;

import com.my_medi.domain.member.entity.Member;

public interface MemberQueryService {
    Member getMemberByUsername(String username);
    Member getByKakaoEmail(String email);

    Member getByLoginId(String loginId);
}
