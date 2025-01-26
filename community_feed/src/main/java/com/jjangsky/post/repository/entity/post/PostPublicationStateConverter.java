package com.jjangsky.post.repository.entity.post;

import com.jjangsky.post.domain.content.PostPublicationState;
import jakarta.persistence.AttributeConverter;

public class PostPublicationStateConverter implements AttributeConverter<PostPublicationState, String> {

    @Override
    public String convertToDatabaseColumn(PostPublicationState attribute) {
        return attribute.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String dbData) {
        return PostPublicationState.valueOf(dbData);
    }
}
