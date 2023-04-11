package com.medical.app.service.impl;

import com.medical.app.dto.request.BatchDrugRequest;
import com.medical.app.dto.request.DetailBatchDrugRequest;
import com.medical.app.dto.response.BatchDrugResponse;
import com.medical.app.dto.response.DetailBatchDrugResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.BatchDrug;
import com.medical.app.model.entity.DetailBatchDrug;
import com.medical.app.model.entity.Drug;
import com.medical.app.model.enums.Unit;
import com.medical.app.repository.*;
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

    private final BatchDrugRepository batchDrugRepository;
    private final AuthRepository authRepository;
    private final DetailBatchDrugService detailBatchDrugService;
    private final DetailBatchDrugRepository detailBatchDrugRepository;

    private final SupplierRepository supplierRepository;
    private final DrugRepository drugRepository;
    @Override
    @Transactional
    public BatchDrugResponse saveBatchDrug(BatchDrugRequest batchDrugRequest) {
        BatchDrug batchDrug = new BatchDrug();
        batchDrug.setDebt(batchDrugRequest.getDebt());
        batchDrug.setName(batchDrugRequest.getName());
        batchDrug.setDescription(batchDrugRequest.getDescription());
        batchDrug.setPaidPrice(batchDrugRequest.getPaidPrice());
        batchDrug.setReceiptDate(batchDrugRequest.getReceiptDate());
        batchDrug.setSettlement(batchDrugRequest.getSettlement());
        batchDrug.setTotalPrice(batchDrugRequest.getTotalPrice());

        batchDrug.setCreatedDate(new Date());
        batchDrug.setUser(authRepository.findById(batchDrugRequest.getUserId()).orElseThrow(()-> new UsernameNotFoundException("User not exists!")));
        batchDrug.setSupplier(supplierRepository.findById(batchDrugRequest.getSupplierId()).orElseThrow(() -> new UsernameNotFoundException("Supplier is not exist")));
        BatchDrug batchDrugSaved = batchDrugRepository.save(batchDrug);
        BatchDrugResponse batchDrugResponse = MapData.mapOne(batchDrugSaved,BatchDrugResponse.class);
        List<DetailBatchDrugResponse> detailBatchDrugResponses = new ArrayList<>();
        for(DetailBatchDrugRequest detailBatchDrugRequest : batchDrugRequest.getDetailBatchDrug()){
            detailBatchDrugRequest.setBatchDrugId(batchDrugSaved.getId());
            detailBatchDrugResponses.add(detailBatchDrugService.saveDetailBatchDrug(detailBatchDrugRequest));
        }
        batchDrugResponse.setDetailBatchDrugResponses(detailBatchDrugResponses);
        return batchDrugResponse;
    }

    @Override
    public BatchDrugResponse getBatchDrugById(Integer id) {
        BatchDrugResponse batchDrugResponse = MapData.mapOne(batchDrugRepository.findById(id).orElse(null), BatchDrugResponse.class);
        batchDrugResponse.setDetailBatchDrugResponses(detailBatchDrugService.getDetailByBatchDrugId(id));
        return batchDrugResponse;
    }

    @Override
    public List<BatchDrugResponse> getAllBatchDrug() {
        List<BatchDrugResponse> batchDrugResponses = MapData.mapList(batchDrugRepository.findAll(), BatchDrugResponse.class);
        for(BatchDrugResponse batchDrugResponse : batchDrugResponses){
            batchDrugResponse.setDetailBatchDrugResponses(detailBatchDrugService.getDetailByBatchDrugId(batchDrugResponse.getId()));
        }
        return batchDrugResponses;
    }

    @Override
    public BatchDrugResponse exportDrug(Integer drugId, Integer quality, String type) {
        Drug drug = drugRepository.findById(drugId).orElseThrow(()-> new UsernameNotFoundException("Drug not found!"));
        List<DetailBatchDrug> detailBatchDrugs = detailBatchDrugRepository.findDetailBatchDrugsByDrugId(drugId);
        int total_quality = 0;
        for(DetailBatchDrug detailBatchDrug : detailBatchDrugs){
            if(detailBatchDrug.getUnit().equals(Unit.HOP)){
                total_quality +=detailBatchDrug.getQuality()*drug.getHopThung()*drug.getViHop()*drug.getVienVi();
            }
        }

        return null;
    }

}
