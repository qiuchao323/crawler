package cn.yd.crawler.PagesProcessors;


import cn.yd.crawler.SeleniumDownloader.SeleniumDownloader;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 通用爬虫
 */
@Component
public class GeneralCrawlerProcessor implements PageProcessor {
    private Site site;
    @Override
    public void process(Page page) {
        page.putField("标题：",page.getHtml().xpath("//*[@id=\"onprint-title\"]/h4/text()"));
        page.putField("正文：",page.getHtml().xpath("//*[@id=\"printArea\"]").get());
        page.putField("时间：",page.getHtml().xpath("//*[@id=\"print-content\"]/div/h2/div/span[2]/span/time/b/text()"));
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

    public static void main(String[] args) {
      //  System.getProperties().setProperty("selenuim_config","C:\\chromedriver.exe");
        Spider
                .create(new GeneralCrawlerProcessor())
                .addUrl("http://www.baidu.com")
                .setDownloader(new SeleniumDownloader("C:\\chromedriver.exe").setSleepTime(3000)).thread(5)
                .run();
    }
}
