package com.my_medi.api.userNotification.service;

import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.UserNotificationDto;
import com.my_medi.api.userNotification.mapper.UserNotificationConverter;
import com.my_medi.domain.notification.entity.UserNotification;
import com.my_medi.domain.notification.repository.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNotificationUseCase {
    private final UserNotificationRepository userNotificationRepository;

    public Slice<UserNotificationDto> getPrioritizedNotificationDtoSliceByUserId(Long userId, Integer page, Integer size) {
        Integer offset = page * size;

        List<UserNotification> unreadList = userNotificationRepository
                .findByUserIdAndIsReadFalseOrderByIdDesc(userId);
        Integer unreadTotal = unreadList.size();

        List<UserNotification> result = new ArrayList<>();

        if (offset < unreadTotal) {
            Integer unreadEnd = Math.min(offset + size, unreadTotal);
            result.addAll(unreadList.subList(offset, unreadEnd));

            Integer remaining = size - result.size();
            if (remaining > 0) {
                Pageable readPageable = PageRequest.of(0, offset + size, Sort.by("id").descending());
                List<UserNotification> readAll = userNotificationRepository
                        .findByUserIdAndIsReadTrueOrderByIdDesc(userId, readPageable);

                Integer fromIndex = Math.min(offset, readAll.size());
                Integer toIndex = Math.min(fromIndex + remaining, readAll.size());
                if (fromIndex < toIndex) {
                    result.addAll(readAll.subList(fromIndex, toIndex));
                }
            }
        } else {
            Integer readOffset = offset - unreadTotal;
            Pageable readPageable = PageRequest.of(0, readOffset + size, Sort.by("id").descending());
            List<UserNotification> readAll = userNotificationRepository
                    .findByUserIdAndIsReadTrueOrderByIdDesc(userId, readPageable);

            Integer fromIndex = Math.min(readOffset, readAll.size());
            Integer toIndex = Math.min(fromIndex + size, readAll.size());
            if (fromIndex < toIndex) {
                result.addAll(readAll.subList(fromIndex, toIndex));
            }
        }

        List<UserNotificationDto> dtoList = result.stream()
                .map(UserNotificationConverter::fromUserNotification)
                .toList();

        Long readTotal = userNotificationRepository.countByUserIdAndIsReadTrue(userId);
        Integer totalAvailable = unreadTotal + readTotal.intValue();
        Boolean hasNext = totalAvailable > offset + size;

        return new SliceImpl<>(dtoList, PageRequest.of(page, size), hasNext);
    }
}
