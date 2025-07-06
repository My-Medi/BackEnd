package com.my_medi.domain.expert.entity;

import com.my_medi.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DiscriminatorValue("type_expert")      //TODO ROLE ENUM key값으로
public class Expert extends Member {

    private String expertUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // FK 컬럼명
    private Member member_id;

    private String licenseFileUrl;

    private String introduction;

    //분야는 ExpertCategory의 enum으로 조회(ArrayList로 가져와야하는지 고민)

    // @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<경력테이블> careers = new ArrayList<>();

}
