package com.medical.app.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedicalExaminationDetailsResponse {

    private Integer id;
    private Date createdDate;
    private Date updatedDate;
    private ServiceResponse service;
    private RoomResponse room;
    private String status;
    private List<String> images;
    private JsonNode result;
    private Boolean paid;
    private String conclusions;
}
