package com.my_medi.api.expertNotification.service;

import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.ExpertNotificationDto;
import com.my_medi.api.expertNotification.mapper.ExpertNotificationConverter;
import com.my_medi.domain.notification.entity.ExpertNotification;
import com.my_medi.domain.notification.repository.ExpertNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertNotificationUseCase {
    private final ExpertNotificationRepository expertNotificationRepository;

    public Slice<ExpertNotificationDto> getPrioritizedNotificationDtoSliceByExpertId(Long expertId, Integer page, Integer size) {
        Integer offset = page * size;

        List<ExpertNotification> unreadList = expertNotificationRepository
                .findByExpertIdAndIsReadFalseOrderByIdDesc(expertId);
        Integer unreadTotal = unreadList.size();

        List<ExpertNotification> result = new ArrayList<>();

        if (offset < unreadTotal) {
            Integer unreadEnd = Math.min(offset + size, unreadTotal);
            result.addAll(unreadList.subList(offset, unreadEnd));

            Integer remaining = size - result.size();
            if (remaining > 0) {
                Pageable readPageable = PageRequest.of(0, offset + size, Sort.by("id").descending());
                List<ExpertNotification> readAll = expertNotificationRepository
                        .findByExpertIdAndIsReadTrueOrderByIdDesc(expertId, readPageable);

                Integer fromIndex = Math.min(offset, readAll.size());
                Integer toIndex = Math.min(fromIndex + remaining, readAll.size());
                if (fromIndex < toIndex) {
                    result.addAll(readAll.subList(fromIndex, toIndex));
                }
            }
        } else {
            Integer readOffset = offset - unreadTotal;
            Pageable readPageable = PageRequest.of(0, readOffset + size, Sort.by("id").descending());
            List<ExpertNotification> readAll = expertNotificationRepository
                    .findByExpertIdAndIsReadTrueOrderByIdDesc(expertId, readPageable);

            Integer fromIndex = Math.min(readOffset, readAll.size());
            Integer toIndex = Math.min(fromIndex + size, readAll.size());
            if (fromIndex < toIndex) {
                result.addAll(readAll.subList(fromIndex, toIndex));
            }
        }

        List<ExpertNotificationDto> dtoList = result.stream()
                .map(ExpertNotificationConverter::fromExpertNotification)
                .toList();

        Long readTotal = expertNotificationRepository.countByExpertIdAndIsReadTrue(expertId);
        Integer totalAvailable = unreadTotal + readTotal.intValue();
        Boolean hasNext = totalAvailable > offset + size;

        return new SliceImpl<>(dtoList, PageRequest.of(page, size), hasNext);
    }
}
