package ee.lostpearls.persistence.answer;

import ee.lostpearls.controller.keyword.dto.KeywordInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {
    Answer toAnswer(KeywordInfo keywordInfo);

    @Mapping(source = "id", target = "answerId")
    @Mapping(source = "keyword", target = "keyword")
    KeywordInfo toKeywordInfo(Answer answer);

    List<KeywordInfo> toKeywordInfos(List<Answer> answers);

}