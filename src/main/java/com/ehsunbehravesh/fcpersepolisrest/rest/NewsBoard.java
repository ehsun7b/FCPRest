package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsunbehravesh.persepolis.entity.News;
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
    news1.setTitle("جلسه 4 ساعته دايي با منتقمي و پنجعلي");
    news1.setDescription("علي دايي ظهر امروز به باشگاه پرسپوليس رفت و در جلسه‌اي 4 ساعته با منتقمي و پنجعلي نقطه‌نظرات خود را اعلام کرد.");
    news1.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.varzesh3.com/files/Picture/00946547.jpg");
    news1.setLink("http://www.varzesh3.com/news.do?itemid=1060864&title=%D8%AC%D9%84%D8%B3%D9%87_4_%D8%B3%D8%A7%D8%B9%D8%AA%D9%87_%D8%AF%D8%A7%D9%8A%D9%8A_%D8%A8%D8%A7_%D9%85%D9%86%D8%AA%D9%82%D9%85%D9%8A_%D9%88_%D9%BE%D9%86%D8%AC%D8%B9%D9%84%D9%8A");
    list.add(news1);

    News news2 = new News();
    news2.setTitle("آرسنال سردار آزمون را با 2 میلیون پوند می خواهد");
    news2.setDescription("به نقل از سایت «مترو انگلیس» آرسنال خواهان به خدمت گرفتن سردار آزمون ملقب به مسی ایرانی با مبلغ 2 میلیون پوند است");
    news2.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.arteshesorkh.com/file/image/post_tumb/azmoon-2-90498.jpg");
    news2.setLink("http://www.arteshesorkh.com/index/post_50801/%D8%A2%D8%B1%D8%B3%D9%86%D8%A7%D9%84-%D8%B3%D8%B1%D8%AF%D8%A7%D8%B1-%D8%A2%D8%B2%D9%85%D9%88%D9%86-%D8%B1%D8%A7-%D8%A8%D8%A7-2-%D9%85%DB%8C%D9%84%DB%8C%D9%88%D9%86-%D9%BE%D9%88%D9%86%D8%AF-%D9%85%DB%8C-%D8%AE%D9%88%D8%A7%D9%87%D8%AF/");
    list.add(news2);

    News news3 = new News();
    news3.setTitle("پیام صادقیان:مگر خوش تیپی جرم است؟/عاشق پرسپولیس نبودم اما حالا یک پرسپولیسی شش آتشه شده ام");
    news3.setDescription("سال 92 را با حاشیه های زیادی به اتمام رساند.یک روز به موهایش گیر دادند و روز دیگر به بازی فیزیکی اش در زمین.یک روز چون پیام صادق بود و واقعا از درآمد و خرجی که در ماه می کند حرف زد برایش حاشیه درست کردند و یک روز دیگر او را مقابل جواد نکونام قرار دادند...");
    news3.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://images.khabaronline.ir/images/2014/3/position50/13-12-21-185033sadeghian.jpg");
    news3.setLink("http://www.fcpersepolis.info/newstext?url=http%3A%2F%2Fwww.khabaronline.ir%2Fdetail%2F346837");
    list.add(news3);

    News news4 = new News();
    news4.setTitle("نوری: کاش مسئولان زودتر به پرسپولیس کمک می کردند/به نظر کادرفنی احترام می گذارم");
    news4.setDescription("محمد نوری در گفت وگویی درباره بازی با داماش اظهار داشت: همانطور که از قبل تصور می کردیم داماش چون چیزی برای از دست دادن نداشت و محتاج 3 امتیاز بود با تمام توانش مقابل ما ظاهر شد و برای باخت آن هم مقابل هوادارانشان به میدان نیامده بود.");
    news4.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.arteshesorkh.com/file/image/post_tumb/nouri-14-74638.jpg");
    news4.setLink("http://www.arteshesorkh.com/index/post_50811/%D9%86%D9%88%D8%B1%DB%8C-%DA%A9%D8%A7%D8%B4-%D9%85%D8%B3%D8%A6%D9%88%D9%84%D8%A7%D9%86-%D8%B2%D9%88%D8%AF%D8%AA%D8%B1-%D8%A8%D9%87-%D9%BE%D8%B1%D8%B3%D9%BE%D9%88%D9%84%DB%8C%D8%B3-%DA%A9%D9%85%DA%A9-%D9%85%DB%8C-%DA%A9%D8%B1%D8%AF%D9%86%D8%AF-%D8%A8%D9%87-%D9%86%D8%B8%D8%B1/");
    list.add(news4);
    
    News news5 = new News();
    news5.setTitle("حمله نیلسون به رکورد رحمتی در تاریخ لیگ برتر");
    news5.setDescription("در فصل جاری لیگ برتر تیم پرسپولیس که بهترین خط دفاع را دارد، در 17 بازی دروازه اش را بسته نگه داشته و در واقع 61 درصد بازی های خود را بدون گل خورده پشت سر گذاشته است.");
    news5.setImage("http://fcpersepolis.info/rest/image/thumbnail/200/200?url=http://www.arteshesorkh.com/file/image/post_tumb/nilson-74737.jpg");
    news5.setLink("http://www.arteshesorkh.com/index/post_50812/%D8%AD%D9%85%D9%84%D9%87-%D9%86%DB%8C%D9%84%D8%B3%D9%88%D9%86-%D8%A8%D9%87-%D8%B1%DA%A9%D9%88%D8%B1%D8%AF-%D8%B1%D8%AD%D9%85%D8%AA%DB%8C-%D8%AF%D8%B1-%D8%AA%D8%A7%D8%B1%DB%8C%D8%AE-%D9%84%DB%8C%DA%AF-%D8%A8%D8%B1%D8%AA%D8%B1/");
    list.add(news5);        

    return list;
  }
}
