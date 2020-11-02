package cn.yd.crawler.PagesProcessors;

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
public class crawlerProcess implements PageProcessor {
    @Autowired
    private TenderService tenderService;
    private Site site;
    @Override
    public void process(Page page) {
        TenderBooksEntiy tb=tenderService.findGzByWyljdz(page.getUrl()+"");
        if(tb!=null){
            page.putField("bt",tb.getXmmc());
            page.putField("wynr",page.getHtml().xpath(tb.getBody()).get());
            page.putField("wyljdz",page.getUrl());
            page.putField("tname",tb.getSjtype());
        }

    }

    @Override
    public Site getSite() {
        site = Site
                .me().setCharset("UTF-8")
                .setCycleRetryTimes(3)
                .setSleepTime(3* 1000)
                .addHeader("Connection", "keep-alive")
                .addHeader("Cookie", "JSESSIONID=JN9XccTL4vGc6p6Tk3pKThkpyLvQrsxLVdXWLGvnMQLnMq1ThTWY!-309478555")
                .addHeader("Cache-Control", "max-age=0")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        return site;
    }


}
