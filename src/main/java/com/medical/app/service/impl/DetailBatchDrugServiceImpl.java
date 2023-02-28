package com.medical.app.service.impl;

import com.medical.app.dto.request.DetailBatchDrugRequest;
import com.medical.app.dto.response.DetailBatchDrugResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.DetailBatchDrug;
import com.medical.app.repository.BatchDrugRepository;
import com.medical.app.repository.DetailBatchDrugRepository;
import com.medical.app.repository.DrugRepository;
import com.medical.app.service.DetailBatchDrugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class DetailBatchDrugServiceImpl implements DetailBatchDrugService {

    private final BatchDrugRepository batchDrugRepository;
    private final DrugRepository drugRepository;
    private final DetailBatchDrugRepository detailBatchDrugRepository;
    @Override
    public DetailBatchDrugResponse saveDetailBatchDrug(DetailBatchDrugRequest detailBatchDrugRequest) {
        DetailBatchDrug detailBatchDrug = MapData.mapOne(detailBatchDrugRequest,DetailBatchDrug.class);
        detailBatchDrug.setManufactureDate(detailBatchDrugRequest.getManufacture_date());
        detailBatchDrug.setExpiredDate(detailBatchDrug.getExpiredDate());
        detailBatchDrug.setBatchDrug(batchDrugRepository.findById(detailBatchDrugRequest.getBatch_drug_id()).orElseThrow(()-> new UsernameNotFoundException("Batch Drug is not exist!")));
        detailBatchDrug.setDrug(drugRepository.findById(detailBatchDrugRequest.getDrug_id()).orElseThrow(() -> new UsernameNotFoundException("Drug is not exists!")));

        return MapData.mapOne(detailBatchDrugRepository.save(detailBatchDrug), DetailBatchDrugResponse.class);
    }

    @Override
    public List<DetailBatchDrugResponse> getDetailByBatchDrugId(Integer id) {
        return MapData.mapList(Objects.requireNonNull(detailBatchDrugRepository.findDetailBatchDrugsByBatchDrugId(id).orElse(null)), DetailBatchDrugResponse.class);
    }

    @Override
    public DetailBatchDrugResponse updateQualityDetailBatchDrug(Integer id, Integer quality) {
        DetailBatchDrug detailBatchDrug = detailBatchDrugRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Detail Batch Drug is not found"));
        if(quality != null){
            detailBatchDrug.setQuality(quality);
            return MapData.mapOne(detailBatchDrugRepository.save(detailBatchDrug),DetailBatchDrugResponse.class);
        }
        return null;
    }


}
