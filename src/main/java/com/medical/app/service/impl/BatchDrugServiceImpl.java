package com.medical.app.service.impl;

import com.medical.app.dto.request.BatchDrugRequest;
import com.medical.app.dto.request.DetailBatchDrugRequest;
import com.medical.app.dto.response.BatchDrugResponse;
import com.medical.app.dto.response.DetailBatchDrugResponse;
import com.medical.app.exceptions.ForbiddenException;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.*;
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
    private final DetailMedicineRepository detailMedicineRepository;
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
        Supplier supplier = supplierRepository.findById(batchDrugRequest.getSupplierId()).orElseThrow(() -> new UsernameNotFoundException("Supplier is not exist"));
        batchDrug.setSupplier(supplier);
        BatchDrug batchDrugSaved = batchDrugRepository.save(batchDrug);
        BatchDrugResponse batchDrugResponse = MapData.mapOne(batchDrugSaved,BatchDrugResponse.class);
        List<DetailBatchDrugResponse> detailBatchDrugResponses = new ArrayList<>();
        for(DetailBatchDrugRequest detailBatchDrugRequest : batchDrugRequest.getDetailBatchDrug()){
            detailBatchDrugRequest.setBatchDrugId(batchDrugSaved.getId());
            detailBatchDrugResponses.add(detailBatchDrugService.saveDetailBatchDrug(detailBatchDrugRequest));
            Drug drug = drugRepository.findById(detailBatchDrugRequest.getDrugId()).orElse(null);
            int total_quality = 0;
            if (drug.getQuantity() != null){
                total_quality= drug.getQuantity() + detailBatchDrugRequest.getQuality();

            }else {
                total_quality=detailBatchDrugRequest.getQuality();
            }
            drug.setQuantity(total_quality);
            drugRepository.save(drug);
        }
//        batchDrugResponse.se(MapData.mapOne(supplier, SupplierResponse.class));
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
    public Boolean exportDrug(Integer medicalExaminationId) {
        List<DetailMedicine>detailMedicines =detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExaminationId);
        for(DetailMedicine detailMedicine : detailMedicines){
            Drug drug = drugRepository.findById(detailMedicine.getDrug().getId()).orElseThrow(()-> new UsernameNotFoundException("Not found drug"));
            if(drug.getQuantity() < detailMedicine.getQuantity()){
                throw new ForbiddenException("Drug is not enough");
            }else {
                int quantityDrug = drug.getQuantity() - detailMedicine.getQuantity();
                drug.setQuantity(quantityDrug);
                drugRepository.save(drug);
            }
        }

        return true;
    }

}
