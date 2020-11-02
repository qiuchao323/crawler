package cn.yd.crawler.YourPipeline;

import cn.yd.crawler.model.TenderEntiy;
import cn.yd.crawler.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
@Service
public class mysqlPipeline implements Pipeline {
    @Autowired
    private TenderService tenderService;
    @Override
    public void process(ResultItems resultItems, Task task) {
        TenderEntiy tenderEntiy=new TenderEntiy();
        tenderEntiy.setBody(resultItems.get("wynr").toString());
        tenderEntiy.setStatus(1);
        tenderEntiy.setWyljdz(resultItems.get("wyljdz").toString());
        tenderEntiy.setXmmc(resultItems.get("bt")+"");
        tenderEntiy.setTname(resultItems.get("tname").toString());
        if(tenderService.insertTender(tenderEntiy)>0){
            tenderService.updateTender(tenderEntiy);
        }
    }
}
