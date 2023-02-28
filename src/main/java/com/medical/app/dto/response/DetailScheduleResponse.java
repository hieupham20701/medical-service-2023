package com.medical.app.dto.response;

import com.medical.app.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailScheduleResponse {
    private Integer id;
    private Date createdDate;
    private Date updateDate;
    private ScheduleResponse schedule;
    private User user;
}
