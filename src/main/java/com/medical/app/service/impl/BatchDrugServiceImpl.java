package com.medical.app.service.impl;

import com.medical.app.dto.request.BatchDrugRequest;
import com.medical.app.dto.request.DetailBatchDrugRequest;
import com.medical.app.dto.response.BatchDrugResponse;
import com.medical.app.dto.response.DetailBatchDrugResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.BatchDrug;
import com.medical.app.model.entity.DetailBatchDrug;
import com.medical.app.repository.AuthRepository;
import com.medical.app.repository.BatchDrugRepository;
import com.medical.app.repository.DetailBatchDrugRepository;
import com.medical.app.repository.SupplierRepository;
import com.medical.app.service.BatchDrugService;
import com.medical.app.service.DetailBatchDrugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BatchDrugServiceImpl implements BatchDrugService {

    private final BatchDrugRepository drugRepository;
    private final AuthRepository authRepository;
    private final DetailBatchDrugService detailBatchDrugService;
    private final SupplierRepository supplierRepository;
    @Override
    @Transactional
    public BatchDrugResponse saveBatchDrug(BatchDrugRequest batchDrugRequest) {
        BatchDrug batchDrug = MapData.mapOne(batchDrugRequest, BatchDrug.class);
        batchDrug.setCreatedDate(new Date());
        batchDrug.setUser(authRepository.findById(batchDrugRequest.getUserId()).orElseThrow(()-> new UsernameNotFoundException("User not exists!")));
        batchDrug.setSupplier(supplierRepository.findById(batchDrugRequest.getSupplierId()).orElseThrow(() -> new UsernameNotFoundException("Supplier is not exist")));
        BatchDrug batchDrugSaved = drugRepository.save(batchDrug);
        BatchDrugResponse batchDrugResponse = MapData.mapOne(batchDrugSaved,BatchDrugResponse.class);
        List<DetailBatchDrugResponse> detailBatchDrugResponses = new ArrayList<>();
        for(DetailBatchDrugRequest detailBatchDrugRequest : batchDrugRequest.getDetailBatchDrug()){
            detailBatchDrugRequest.setBatch_drug_id(batchDrugSaved.getId());
            detailBatchDrugResponses.add(detailBatchDrugService.saveDetailBatchDrug(detailBatchDrugRequest));
        }
        batchDrugResponse.setDetailBatchDrugResponses(detailBatchDrugResponses);
        return batchDrugResponse;
    }

    @Override
    public BatchDrugResponse getBatchDrugById(Integer id) {
        BatchDrugResponse batchDrugResponse = MapData.mapOne(drugRepository.findById(id).orElse(null), BatchDrugResponse.class);
        batchDrugResponse.setDetailBatchDrugResponses(detailBatchDrugService.getDetailByBatchDrugId(id));
        return batchDrugResponse;
    }

    @Override
    public List<BatchDrugResponse> getAllBatchDrug() {
        List<BatchDrugResponse> batchDrugResponses = MapData.mapList(drugRepository.findAll(), BatchDrugResponse.class);
        for(BatchDrugResponse batchDrugResponse : batchDrugResponses){
            batchDrugResponse.setDetailBatchDrugResponses(detailBatchDrugService.getDetailByBatchDrugId(batchDrugResponse.getId()));
        }
        return batchDrugResponses;
    }
}
