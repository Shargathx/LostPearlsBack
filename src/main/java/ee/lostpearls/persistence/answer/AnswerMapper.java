package ee.lostpearls.persistence.answer;

import ee.lostpearls.controller.keyword.dto.KeywordDto;
import ee.lostpearls.controller.keyword.dto.KeywordInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {


    @Mapping(source = "keyword", target = "keyword")
    Answer toAnswer(KeywordDto keywordDto);

    @Mapping(source = "id", target = "answerId")
    @Mapping(source = "keyword", target = "keyword")
    KeywordInfo toKeywordInfo(Answer answer);

    List<KeywordInfo> toKeywordInfos(List<Answer> answers);

}