package cn.yd.crawler.model;

import lombok.Data;

import java.util.Date;

@Data
public class TenderBooksEntiy  {
    private String wyljdz;
    private int status;
    private Date cjsj;
    private String sjtype;
    private String xmmc;
    private String body;
    private String ggsj;
}
