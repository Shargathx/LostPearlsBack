package ee.lostpearls.persistence.hint;

import ee.lostpearls.controller.hint.dto.HintDto;
import ee.lostpearls.controller.hint.dto.HintInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HintMapper {


    @Mapping(source = "hint", target = "hint")
    Hint toHint(HintDto hintDto);

    @Mapping(source = "id", target = "hintId")
    @Mapping(source = "hint", target = "hint")
    HintInfo toHintInfo(Hint hint);

    List<HintInfo> toHintInfos(List<Hint> hint);

}