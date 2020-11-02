package cn.yd.crawler.dao;

import cn.yd.crawler.model.TenderBooksEntiy;
import cn.yd.crawler.model.TenderEntiy;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TenderDao {
//    int saveTender();
    @Select("SELECT DISTINCT * FROM source_url t WHERE t.`status`=0 AND t.xmmc IS NOT NULL AND locate('http',T.WYLJDZ)  ")
    List<TenderBooksEntiy> findAllWy();//获取所有的网页
    @Select("SELECT DISTINCT * FROM source_url t WHERE t.`status`=0 AND t.xmmc IS NOT NULL AND t.BODY IS NOT NULL  and wyljdz=#{url} ")
    TenderBooksEntiy findGzByWyljdz(String url);
    //@Insert("insert into   #{tname} (xmmc,wyljdz,body,cjsj,status) values (#{xmmc},#{wyljdz},#{body},now(),1) ")
    @InsertProvider(type = LearnSqlBuilder.class, method = "insterTenderPro")
    int insertTender(TenderEntiy tenderEntiy);
    @Update(" UPDATE source_url SET STATUS=1 WHERE  wyljdz=#{wyljdz} ")
    void updateTender(TenderEntiy tenderEntiy);

    class LearnSqlBuilder {
        //动态构造语句
        public String insterTenderPro(TenderEntiy tenderEntiy){
            StringBuffer sql =new StringBuffer();
            sql.append("insert into datasource.");
            sql.append(tenderEntiy.getTname());
            sql.append(" (");
            sql.append("xmmc,wyljdz,body,cjsj,status");
            sql.append(" )");
            sql.append(" values (#{xmmc},#{wyljdz},#{body},now(),1)");
            System.out.println("插入sql=="+sql.toString());
            return sql.toString();
        }
    }
}
