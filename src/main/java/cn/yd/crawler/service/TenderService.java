package cn.yd.crawler.service;

import cn.yd.crawler.model.TenderBooksEntiy;
import cn.yd.crawler.model.TenderEntiy;

import java.util.List;

public interface TenderService {
    List<TenderBooksEntiy> findAllWy();

    TenderBooksEntiy findGzByWyljdz(String url);

    int insertTender(TenderEntiy tenderEntiy);

    void updateTender(TenderEntiy tenderEntiy);
}
