package com.jttpro.ragpro.repository;

import com.jttpro.ragpro.entity.UserProfileEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserProfileRepository {

    public UserProfileEntity findByUserId(String userId) {
        return new UserProfileEntity(
                1L,
                userId,
                "demo-user",
                "RAG系统, 智能问答, 知识库",
                "memory, retrieval, llm",
                LocalDateTime.now().minusDays(30),
                LocalDateTime.now()
        );
    }

    public List<UserProfileEntity> findAllActiveProfiles() {
        return List.of(findByUserId("user-001"));
    }
}
