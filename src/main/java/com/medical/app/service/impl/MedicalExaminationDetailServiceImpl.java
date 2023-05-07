package com.medical.app.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.app.dto.request.MedicalExaminationDetailsRequest;
import com.medical.app.dto.response.*;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.ImageUrl;
import com.medical.app.model.entity.MedicalExamination;
import com.medical.app.model.entity.MedicalExaminationDetails;
import com.medical.app.model.enums.StatusMedicalDetail;
import com.medical.app.repository.*;
import com.medical.app.service.MedicalExaminationDetailService;
import com.medical.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MedicalExaminationDetailServiceImpl implements MedicalExaminationDetailService {

    private final MedicalExaminationDetailRepository medicalExaminationDetailRepository;
    private final ServiceRepository serviceRepository;
    private final MedicalExaminationRepository medicalExaminationRepository;
    private final RoomRepository roomRepository;
    private final UserService userService;
    private final ImageUrlRepository imageUrlRepository;
    private final static ObjectMapper mapper = new ObjectMapper();

    @Override
    public MedicalExaminationDetailsResponse saveMedicalExaminationDetail(MedicalExaminationDetailsRequest medicalExaminationDetailsRequest) throws JsonProcessingException {
        MedicalExaminationDetails medicalExaminationDetails = new MedicalExaminationDetails();
        medicalExaminationDetails.setCreatedDate(new Date());
        medicalExaminationDetails.setMedicalExamination(medicalExaminationRepository.findById(medicalExaminationDetailsRequest.getMedicalExaminationId()).orElseThrow(()-> new UsernameNotFoundException("Medical Examination not exist!")));
        medicalExaminationDetails.setService(serviceRepository.findById(medicalExaminationDetailsRequest.getServiceId()).orElseThrow(()-> new UsernameNotFoundException("Service is not exists!")));
//        medicalExaminationDetails.setRoom(roomRepository.findById(medicalExaminationDetailsRequest.getRoomId()).orElseThrow(()-> new UsernameNotFoundException("Room is not exists!")));
       medicalExaminationDetails.setStatus(StatusMedicalDetail.valueOf(medicalExaminationDetailsRequest.getStatus()));
       if(medicalExaminationDetailsRequest.getResult() != null)
           medicalExaminationDetails.setResult(mapper.readTree(medicalExaminationDetailsRequest.getResult()));
       medicalExaminationDetails.setPaid(false);
       MedicalExaminationDetails medicalExaminationDetailsSaved = medicalExaminationDetailRepository.save(medicalExaminationDetails);
        return MapData.mapOne(medicalExaminationDetailsSaved, MedicalExaminationDetailsResponse.class);
    }

    @Override
    public MedicalExaminationDetailsResponse getMedicalExamination(Integer id) {
        return MapData.mapOne(medicalExaminationDetailRepository.findById(id).orElse(null), MedicalExaminationDetailsResponse.class);
    }

    @Override
    public List<MedicalExaminationDetailsResponse> getMedicalExaminations() {
        return MapData.mapList(medicalExaminationDetailRepository.findAll(),MedicalExaminationDetailsResponse.class);
    }

    @Override
    public Boolean deleteMedicalExaminationDetail(Integer medicalId, Integer serviceId) {

        try {
            MedicalExaminationDetails medicalExaminationDetails = medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationIdAndServiceId(medicalId,serviceId);
            medicalExaminationDetailRepository.delete(medicalExaminationDetails);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<MedicalExaminationDetailsResponse> getMedicalExaminationDetailByMedicalExamId(Integer id) {
        return MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")),MedicalExaminationDetailsResponse.class);
    }

    @Override
    public MedicalExaminationDetailsResponse updateMedicalExaminationDetail(Integer id, String status, List<MultipartFile> image, String result) throws JsonProcessingException {
        MedicalExaminationDetails medicalExaminationDetails = medicalExaminationDetailRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Not found"));
        medicalExaminationDetails.setStatus(StatusMedicalDetail.valueOf(status));
        List<String> images = new ArrayList<>();
        if(image != null){
            for(MultipartFile file : image){
                String url = userService.uploadAvatar(file);
                ImageUrl imageUrl = new ImageUrl();
                imageUrl.setUrl(url);
                imageUrl.setMedicalExaminationDetails(medicalExaminationDetails);
                imageUrl.setCreatedDate(new Date());
                imageUrlRepository.save(imageUrl);
            }
        }
        medicalExaminationDetails.setResult(mapper.readTree(result));
        MedicalExaminationDetails medicalExaminationDetailSaved = medicalExaminationDetailRepository.save(medicalExaminationDetails);
        MedicalExaminationDetailsResponse medicalExaminationDetailsResponse = MapData.mapOne(medicalExaminationDetailSaved, MedicalExaminationDetailsResponse.class);
        medicalExaminationDetailsResponse.setService(MapData.mapOne(medicalExaminationDetailSaved.getService(), ServiceResponse.class));
        imageUrlRepository.findByMedicalExaminationDetailsId(id).forEach(imageUrl -> images.add(imageUrl.getUrl()));
        medicalExaminationDetailsResponse.setImages(images);
        return medicalExaminationDetailsResponse;
    }

    @Override
    public List<DetailServiceResponse> getDetailExaminationByDate(Date date) {
        List<MedicalExamination> medicalExaminations = medicalExaminationRepository.findMedicalExaminationsByCreatedDate(date);
        List<DetailServiceResponse> detailServiceResponses = new ArrayList<>();
        for (MedicalExamination medicalExamination : medicalExaminations){
            DetailServiceResponse detailServiceResponse = new DetailServiceResponse();
            detailServiceResponse.setCreatedDate(medicalExamination.getCreatedDate());
            if(medicalExamination.getDoctor() != null){
                detailServiceResponse.setDoctor(MapData.mapOne(medicalExamination.getDoctor(),UserResponse.class));

            }else {
                detailServiceResponse.setDoctor(null);
            }
            detailServiceResponse.setUpdatedDate(medicalExamination.getUpdatedDate());
            detailServiceResponse.setPatient(MapData.mapOne(medicalExamination.getPatient(),PatientResponse.class));
            detailServiceResponse.setId(medicalExamination.getId());
            detailServiceResponse.setMedicalExaminationDetailsResponses(MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(medicalExamination.getId()).orElseThrow(() -> new UsernameNotFoundException("Medical Examination not found")),MedicalExaminationDetailsResponse.class));
            detailServiceResponses.add(detailServiceResponse);
        }
        return detailServiceResponses;
    }

    @Override
    public List<MedicalExaminationDetailsResponse> paidMedicalExaminationDetail(List<Integer> detailId) {
        List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses = new ArrayList<>();
        detailId.forEach(id ->{
            MedicalExaminationDetails details = medicalExaminationDetailRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Detail not found"));
            details.setPaid(true);
            MedicalExaminationDetails detailsUpdate = medicalExaminationDetailRepository.save(details);
            medicalExaminationDetailsResponses.add(MapData.mapOne(detailsUpdate,MedicalExaminationDetailsResponse.class));
        });
        return medicalExaminationDetailsResponses;
    }

}
