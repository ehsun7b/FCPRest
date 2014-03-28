package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsuhnbehravesh.persepolis.news.News;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ehsun7b
 */
@Path("newsboard")
public class NewsBoard {
  
  @GET
  @Path("json")
  @Produces("application/json; charset=UTF-8")
  public List<News> json() {
    List<News> result;
    
    result = getNews();
    
    return result;
  }

  private List<News> getNews() {
    ArrayList<News> list = new ArrayList<>();
    
    News news1 = new News();
    news1.setTitle("محسن بنگر بهترین بازیکن مسابقه پرسپولیس برابر داماش شد");
    news1.setDescription("مدافع تیم فوتبال پرسپولیس به عنوان بهترین بازیکن مسابقه پرسپولیس و داماش معرفی شد");
    news1.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.fc-perspolis.com/image/getthumbnail/id/15346/size/middle");
    news1.setLink("http://www.fc-perspolis.com/news_content/main/newsId:20823");    
    list.add(news1);
    
    News news2 = new News();
    news2.setTitle("آرسنال سردار آزمون را با 2 میلیون پوند می خواهد");
    news2.setDescription("به نقل از سایت «مترو انگلیس» آرسنال خواهان به خدمت گرفتن سردار آزمون ملقب به مسی ایرانی با مبلغ 2 میلیون پوند است");
    news2.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.arteshesorkh.com/file/image/post_tumb/azmoon-2-90498.jpg");
    news2.setLink("http://www.arteshesorkh.com/index/post_50801/%D8%A2%D8%B1%D8%B3%D9%86%D8%A7%D9%84-%D8%B3%D8%B1%D8%AF%D8%A7%D8%B1-%D8%A2%D8%B2%D9%85%D9%88%D9%86-%D8%B1%D8%A7-%D8%A8%D8%A7-2-%D9%85%DB%8C%D9%84%DB%8C%D9%88%D9%86-%D9%BE%D9%88%D9%86%D8%AF-%D9%85%DB%8C-%D8%AE%D9%88%D8%A7%D9%87%D8%AF/");    
    list.add(news2);
    
    return list;
  }
}
