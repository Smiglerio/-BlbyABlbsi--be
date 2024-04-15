package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class pokrokDTO {
    private Long cvicenieid;
    private Date datum;
    private Long userid;

    public void setDatum(String datum) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.datum = dateFormat.parse(datum);
        } catch (ParseException e) {

            e.printStackTrace();
        }
    }
}
