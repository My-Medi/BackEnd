package com.my_medi.domain.user.entity;

import com.my_medi.common.consts.StaticVariable;
import com.my_medi.domain.member.entity.Member;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.api.user.dto.UpdateUserDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DiscriminatorValue(StaticVariable.USER)      //TODO ROLE ENUM key값으로
public class User extends Member {

    //키
    private Float height;

    //몸무게
    private Float weight;

    @Builder.Default
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Proposal proposals;

    // 수정용 메서드
    public void modifyUserInfo(UpdateUserDto dto) {
        this.modifyMemberInfoUser(dto);
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
    }

}
