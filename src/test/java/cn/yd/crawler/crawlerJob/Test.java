package cn.yd.crawler.crawlerJob;

import cn.yd.crawler.Application;
import cn.yd.crawler.PagesProcessors.crawlerProcess;
import cn.yd.crawler.SeleniumDownloader.SeleniumDownloader;
import cn.yd.crawler.YourPipeline.mysqlPipeline;
import cn.yd.crawler.service.TenderService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})// 指定启动类
public class Test {
    @Autowired
    private TenderService tenderService;
    @Autowired
    private mysqlPipeline Pipelines;
    @Autowired
    private crawlerProcess Process;
    @org.junit.Test
    public void mine() {
        System.out.println("任务开始执行》》》》");
        Spider.create(Process).addPipeline(new ConsolePipeline())
                .addUrl("http://www.bankofluoyang.com.cn/shownews.jsp?id=3734").addPipeline(Pipelines)
                .setDownloader(new SeleniumDownloader("C:\\chromedriver.exe").setSleepTime(3000)).thread(5)
                .run();
    }
}
