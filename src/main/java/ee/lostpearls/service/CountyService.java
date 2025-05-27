package ee.lostpearls.service;

import ee.lostpearls.controller.county.dto.CountyInfo;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.county.County;
import ee.lostpearls.persistence.county.CountyMapper;
import ee.lostpearls.persistence.county.CountyRepository;
import ee.lostpearls.persistence.location.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountyService {


    private final CountyRepository countyRepository;
    private final CountyMapper countyMapper;
    private final LocationRepository locationRepository;

    public List<CountyInfo> getAllCounties() {
        List<County> counties = countyRepository.findAll();
        List<CountyInfo> countyInfos = countyMapper.toCountyInfos(counties);
        return countyInfos;
    }

    public County getCounty(Integer id) {
        County county = countyRepository.findCountyById(id).orElseThrow(() -> new PrimaryKeyNotFoundException("id", id));
        return county;
    }

    public CountyInfo findCountyByLocationId(Integer locationId) {
        County county = locationRepository.findById(locationId).get().getCounty();
        CountyInfo countyInfo = countyMapper.toCountyInfo(county);
        return countyInfo;
    }
}
