package com.my_medi.api.license.mapper;

import com.my_medi.api.license.dto.LicenseRequestDto;
import com.my_medi.api.licenseImage.dto.LicenseImageRequestDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.license.entity.License;
import com.my_medi.domain.licenseImage.entity.LicenseImage;
import org.springframework.stereotype.Component;

//TODO 컴포넌트 어노테이션이 필요한 이유 클래스 지우기전에 꼭 찾아보기(필수)
// 힌트1. 비슷한 메서드인 LicenseRequestDto의 toEntity는 빈 주입이 필요없음
// 힌트2. 빈주입이 필요없는 클래스인데 빌드가 안된다면 어디에선가 "강제"중이지 않을까?
@Component
public class LicenseConverter {

    //TODO converter 지우기
    // 이유 : LicenseRequestDto에 동일 메서드 존재
    public static License toEntity(LicenseRequestDto dto, Expert expert) {
        License license = License.builder()
                .licenseName(dto.getLicenseName())
                .licenseDate(dto.getLicenseDate())
                .licenseDescription(dto.getLicenseDescription())
                .expert(expert)
                .build();
        return license;
    }
}

