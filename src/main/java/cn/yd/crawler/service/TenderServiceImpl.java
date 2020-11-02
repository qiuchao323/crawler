package cn.yd.crawler.service;

import cn.yd.crawler.dao.TenderDao;
import cn.yd.crawler.model.TenderBooksEntiy;
import cn.yd.crawler.model.TenderEntiy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TenderServiceImpl implements TenderService {
   @Autowired
    private TenderDao tenderDao;
    @Override
    public List<TenderBooksEntiy> findAllWy() {
        return tenderDao.findAllWy();
    }

    @Override
    public TenderBooksEntiy findGzByWyljdz(String url) {
        return tenderDao.findGzByWyljdz(url);
    }

    @Override
    public int insertTender(TenderEntiy tenderEntiy) {
        return tenderDao.insertTender(tenderEntiy);
    }

    @Override
    public void updateTender(TenderEntiy tenderEntiy) {
         tenderDao.updateTender(tenderEntiy);
    }
}
