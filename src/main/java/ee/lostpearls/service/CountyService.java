package ee.lostpearls.service;

import ee.lostpearls.controller.county.dto.CountyInfo;
import ee.lostpearls.persistence.county.County;
import ee.lostpearls.persistence.county.CountyMapper;
import ee.lostpearls.persistence.county.CountyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountyService {


    private final CountyRepository countyRepository;
    private final CountyMapper countyMapper;

    public List<CountyInfo> getAllCounties() {
        List<County> counties = countyRepository.findAll();
        List<CountyInfo> countyInfos = countyMapper.toCountyInfos(counties);
        return countyInfos;
    }
}
