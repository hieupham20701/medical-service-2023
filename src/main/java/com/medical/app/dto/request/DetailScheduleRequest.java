package com.medical.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailScheduleRequest {

    private Integer id;
    private Date createdDate;
    private Date updateDate;
    private Integer schedule_id;
    private Integer user_id;
}
