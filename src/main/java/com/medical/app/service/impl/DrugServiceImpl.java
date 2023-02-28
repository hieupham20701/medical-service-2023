package com.medical.app.service.impl;

import com.medical.app.dto.request.DrugRequest;
import com.medical.app.dto.response.DrugResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.DetailBatchDrug;
import com.medical.app.model.entity.Drug;
import com.medical.app.repository.CategoryDrugRepository;
import com.medical.app.repository.DetailBatchDrugRepository;
import com.medical.app.repository.DrugRepository;
import com.medical.app.service.DrugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;
    private final CategoryDrugRepository categoryDrugRepository;

    private final DetailBatchDrugRepository detailBatchDrugRepository;
    @Override
    public DrugResponse saveDrug(DrugRequest drugRequest) {
        Drug drug = MapData.mapOne(drugRequest, Drug.class);
        drug.setCreatedDate(new Date());
        drug.setCategoryDrug(categoryDrugRepository.findById(drugRequest.getCategory_drug_id()).orElse(null));
        return MapData.mapOne(drugRepository.save(drug),DrugResponse.class);
    }

    @Override
    public List<DrugResponse> getAllDrugs() {
        List<DrugResponse> drugResponses = MapData.mapList(drugRepository.findAll(), DrugResponse.class);
        for(DrugResponse drugResponse : drugResponses){
            drugResponse.setQuality(getQualityDrug(drugResponse.getId()));
        }
        return drugResponses;
    }

    @Override
    public DrugResponse getDrugById(Integer id) {
        Drug drug = drugRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Drug is not exist"));

        DrugResponse drugResponse = MapData.mapOne(drug,DrugResponse.class);
        drugResponse.setQuality(this.getQualityDrug(id));
        return drugResponse;
    }

    @Override
    public Integer getQualityDrug(Integer id){
        List<DetailBatchDrug> detailBatchDrugs = detailBatchDrugRepository.findDetailBatchDrugsByDrugId(id);
        int quality = 0;
        for(DetailBatchDrug detailBatchDrug : detailBatchDrugs){
            quality+= detailBatchDrug.getQuality();
        }
        return quality;
    }

}
