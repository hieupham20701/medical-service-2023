package com.medical.app.service.impl;

import com.medical.app.dto.request.DrugRequest;
import com.medical.app.dto.response.DrugResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.DetailBatchDrug;
import com.medical.app.model.entity.Drug;
import com.medical.app.model.enums.Unit;
import com.medical.app.repository.CategoryDrugRepository;
import com.medical.app.repository.DetailBatchDrugRepository;
import com.medical.app.repository.DrugRepository;
import com.medical.app.service.DrugService;
import com.medical.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;
    private final CategoryDrugRepository categoryDrugRepository;
    private final UserService userService;
    private final DetailBatchDrugRepository detailBatchDrugRepository;
    @Override
    public DrugResponse saveDrug(DrugRequest drugRequest) {
        Drug drug = MapData.mapOne(drugRequest, Drug.class);
        drug.setCreatedDate(new Date());
        drug.setCategoryDrug(categoryDrugRepository.findById(drugRequest.getCategoryDrugId()).orElse(null));
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

    @Override
    public DrugResponse updateDrugResponse(Integer id, DrugRequest drugRequest) {
        Drug drug = drugRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Not found"));
        if (drugRequest.getCategoryDrugId() != null){
            drug.setCategoryDrug(categoryDrugRepository.findById(drugRequest.getCategoryDrugId()).orElse(null));
        }
        if (drugRequest.getBenefit() != null)
            drug.setBenefit(drugRequest.getBenefit());
        if(drugRequest.getName() != null)
            drug.setName(drugRequest.getName());
        if(drugRequest.getNote() != null){
            drug.setNote(drugRequest.getNote());
        }
        if(drugRequest.getPrice() != null){
            drug.setPrice(drugRequest.getPrice());
        }
        if(drugRequest.getDescription() != null){
            drug.setDescription(drugRequest.getDescription());
        }
        if(drugRequest.getHopThung()!= null){
            drug.setHopThung(drugRequest.getHopThung());
        }
        if(drugRequest.getImage() != null){
            drug.setImage(drugRequest.getImage());
        }
        if(drugRequest.getUnit() != null){
            drug.setUnit(Unit.valueOf(drugRequest.getUnit()));
        }
        if(drugRequest.getVienVi() != null){
            drug.setVienVi(drugRequest.getVienVi());
        }
        if(drugRequest.getViHop() != null){
            drug.setViHop(drugRequest.getViHop());
        }
        drug.setUpdatedDate(new Date());

        Drug drugSaved = drugRepository.save(drug);

        return MapData.mapOne(drugSaved, DrugResponse.class);
    }

    @Override
    public String uploadImgDrug(MultipartFile file) {
        return userService.uploadAvatar(file);
    }

}
