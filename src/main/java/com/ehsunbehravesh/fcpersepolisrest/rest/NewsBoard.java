package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsFetchBean;
import com.ehsunbehravesh.persepolis.entity.News;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ehsun7b
 */
@Stateless
@Path("hotnews")
public class NewsBoard {

  @Inject
  private NewsBean newsBean;    
  
  @GET
  @Path("json")
  @Produces("application/json; charset=UTF-8")
  public List<News> json() {
    List<News> result;

    //result = newsBean.currentHotNews();
    
    result = newsBean.readTop(NewsFetchBean.OFFICIAL_WEBSITE, 10);

    return result;
  }

  private List<News> getNews() {
    ArrayList<News> list = new ArrayList<>();

    News news1 = new News();
    news1.setTitle("جلسهآقازمانی: ابراهیم زاده اطلاعات پرسپولیس را از من می خواست/همه با او مشکل داشتند");
    news1.setDescription("سامان آقازمانی پس از تساوی دو بر دو راه آهن مقابل پرسپولیس گفت: متأسفانه با وجود اینکه در نیم فصل پیشنهادات خوبی از تیم های دیگر داشتم اما با درخواست ابراهیم زاده در راه آهن ماندم.");
    news1.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.arteshesorkh.com/file/image/post_tumb/agha-zamani1-99071.jpg");
    news1.setLink("http://www.arteshesorkh.com/index/post_50978/%D8%A2%D9%82%D8%A7%D8%B2%D9%85%D8%A7%D9%86%DB%8C-%D8%A7%D8%A8%D8%B1%D8%A7%D9%87%DB%8C%D9%85-%D8%B2%D8%A7%D8%AF%D9%87-%D8%A7%D8%B7%D9%84%D8%A7%D8%B9%D8%A7%D8%AA-%D9%BE%D8%B1%D8%B3%D9%BE%D9%88%D9%84%DB%8C%D8%B3-%D8%B1%D8%A7-%D8%A7%D8%B2-%D9%85%D9%86-%D9%85%DB%8C-%D8%AE%D9%88%D8%A7%D8%B3/");
    list.add(news1);

    News news2 = new News();
    news2.setTitle("حقیقی: به آنهایی که دوست داشتند پرسپولیس قهرمان نشود، تبریک می گویم");
    news2.setDescription("رضا حقیقی بعد از تساوی پرسپولیس مقابل راه آهن گفت: هر چه داشتیم گذاشتیم اما در دو دقیقه از عرش به زیر فرش رفتیم اگر می بردیم باغیرت می شدیم. من به همه آنهایی که دوست نداشتند پرسپولیس قهرمان نشود، تبریک می گویم.");
    news2.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.arteshesorkh.com/file/image/post_tumb/reza-haghighi-7-98933.jpg");
    news2.setLink("http://www.arteshesorkh.com/index/post_50979");
    list.add(news2);

    News news3 = new News();
    news3.setTitle("پرسپولیس 2-2 راه آهن / سرخپوشان دقیقه  آخر صدر را از دست دادند");
    news3.setDescription("تیم فوتبال پرسپولیس در هفته بیست و نهم رقابت های لیگ برتر باشگاه های کشور برابر راه آهن متوقف شد و شانس رسیدن به صدر جدول را از دست داد. ");
    news3.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.fc-perspolis.com/image/getthumbnail/id/16904/size/middle");
    news3.setLink("http://www.fc-perspolis.com/news_content/main/newsId:21060");
    list.add(news3);
/*
    News news4 = new News();
    news4.setTitle("نوری: کاش مسئولان زودتر به پرسپولیس کمک می کردند/به نظر کادرفنی احترام می گذارم");
    news4.setDescription("محمد نوری در گفت وگویی درباره بازی با داماش اظهار داشت: همانطور که از قبل تصور می کردیم داماش چون چیزی برای از دست دادن نداشت و محتاج 3 امتیاز بود با تمام توانش مقابل ما ظاهر شد و برای باخت آن هم مقابل هوادارانشان به میدان نیامده بود.");
    news4.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.arteshesorkh.com/file/image/post_tumb/nouri-14-74638.jpg");
    news4.setLink("http://www.arteshesorkh.com/index/post_50811/%D9%86%D9%88%D8%B1%DB%8C-%DA%A9%D8%A7%D8%B4-%D9%85%D8%B3%D8%A6%D9%88%D9%84%D8%A7%D9%86-%D8%B2%D9%88%D8%AF%D8%AA%D8%B1-%D8%A8%D9%87-%D9%BE%D8%B1%D8%B3%D9%BE%D9%88%D9%84%DB%8C%D8%B3-%DA%A9%D9%85%DA%A9-%D9%85%DB%8C-%DA%A9%D8%B1%D8%AF%D9%86%D8%AF-%D8%A8%D9%87-%D9%86%D8%B8%D8%B1/");
    list.add(news4);
  */  
    News news5 = new News();
    news5.setTitle("حمله نیلسون به رکورد رحمتی در تاریخ لیگ برتر");
    news5.setDescription("در فصل جاری لیگ برتر تیم پرسپولیس که بهترین خط دفاع را دارد، در 17 بازی دروازه اش را بسته نگه داشته و در واقع 61 درصد بازی های خود را بدون گل خورده پشت سر گذاشته است.");
    news5.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.arteshesorkh.com/file/image/post_tumb/nilson-74737.jpg");
    news5.setLink("http://www.arteshesorkh.com/index/post_50812/%D8%AD%D9%85%D9%84%D9%87-%D9%86%DB%8C%D9%84%D8%B3%D9%88%D9%86-%D8%A8%D9%87-%D8%B1%DA%A9%D9%88%D8%B1%D8%AF-%D8%B1%D8%AD%D9%85%D8%AA%DB%8C-%D8%AF%D8%B1-%D8%AA%D8%A7%D8%B1%DB%8C%D8%AE-%D9%84%DB%8C%DA%AF-%D8%A8%D8%B1%D8%AA%D8%B1/");
    list.add(news5);        

    return list;
  }
}
