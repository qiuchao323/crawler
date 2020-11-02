package cn.yd.crawler.crawlerJob;

import cn.yd.crawler.PagesProcessors.GeneralCrawlerProcessor;
import cn.yd.crawler.PagesProcessors.crawlerProcess;
import cn.yd.crawler.SeleniumDownloader.SeleniumDownloader;
import cn.yd.crawler.YourPipeline.mysqlPipeline;
import cn.yd.crawler.model.TenderBooksEntiy;
import cn.yd.crawler.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
@Component
public class crawlerProcessJob {
    @Autowired
    private TenderService tenderService;
    @Autowired
    private mysqlPipeline Pipelines;
    @Autowired
    private crawlerProcess Process;

    @Scheduled(cron = "0 47 8-23 * * ?")
    public void mine(){
        System.out.println("任务开始执行》》》》");
        Spider.create(Process)
                .addUrl("http://www.baidu.com").addPipeline(Pipelines)
                .setDownloader(new SeleniumDownloader("C:\\chromedriver.exe").setSleepTime(3000).setTenderList(tenderService.findAllWy())).thread(10)
                .run();
    }

}
