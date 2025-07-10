package com.my_medi.domain.expert.service;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.expert.repository.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;

    @Override
    public Expert save(Expert expert) {
        return expertRepository.save(expert);
    }

    @Override
    public Optional<Expert> findById(Long id) {
        return expertRepository.findById(id);
    }

    @Override
    public List<Expert> findAll() {
        return expertRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        expertRepository.deleteById(id);
    }

    @Override
    public Optional<Expert> findByLoginId(String loginId) {
        return expertRepository.findByLoginId(loginId);
    }

    @Override
    public Optional<Expert> findByNickname(String nickname) {
        return expertRepository.findByNickname(nickname);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return expertRepository.existsByNickname(nickname);
    }

    @Override
    public boolean existsByEmail(String email) {
        return expertRepository.existsByEmail(email);
    }

    @Override
    public Optional<Expert> findByExpertUuid(String expertUuid) {
        return expertRepository.findByExpertUuid(expertUuid);
    }

    @Override
    public List<Expert> findBySpecialty(Specialty specialty) {
        return expertRepository.findBySpecialty(specialty);
    }
}
