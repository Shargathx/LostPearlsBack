package ee.lostpearls.persistence.keyword;

import ee.lostpearls.controller.keyword.dto.KeywordDto;
import ee.lostpearls.controller.keyword.dto.KeywordInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface KeywordMapper {


    @Mapping(source = "keyword", target = "keyword")
    Keyword toKeyword(KeywordDto keywordDto);

    @Mapping(source = "id", target = "keywordId")
    @Mapping(source = "keyword", target = "keyword")
    KeywordInfo toKeywordInfo(Keyword keyword);

    List<KeywordInfo> toKeywordInfos(List<Keyword> keywords);

}