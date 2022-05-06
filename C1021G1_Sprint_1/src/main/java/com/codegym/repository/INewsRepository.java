package com.codegym.repository;
import com.codegym.dto.NewsDto;
import com.codegym.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import com.codegym.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;






@Transactional
@Repository
public interface INewsRepository extends JpaRepository<News, Long> {
    @Modifying
    @Query(value = "insert into news(code_news, date_news, " +
            "description_news, image_news,name_news," +
            "title_news,id_category, del_flag_news )" +
            "values (?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
    void createNews(String codeNews, String dateNews,String descriptionNews, String imageNews,
              String nameNews, String titleNews, Long category, Boolean delFlagNews);

    @Query(value = "select id, code_news, date_news,  description_news, image_news," +
            "name_news,title_news,id_category,del_flag_news from news where id = ?", nativeQuery = true)
    News findNewsById(Long id);

    @Modifying
    @Query(value = "update news as n set n.code_news = ?1, n.date_news = ?2, n.description_news = ?3, n.image_news = ?4, " +
            "n.name_news = ?5, n.title_news = ?6, n.id_category = ?7 , n.del_flag_news = ?8  where n.id =?9",nativeQuery = true)
    void editNews(String codeNews, String dateNews,String descriptionNews, String imageNews,
                  String nameNews, String titleNews, Long category, Boolean delFlagNews, Long id);


    @Query(value ="select id, code_news , date_news , del_flag_news , description_news , image_news ,name_news ,title_news ,id_category \n" +
            "from news \n" +
            "where del_flag_news = 1",
            nativeQuery = true,
            countQuery = "select count(*) from news where del_flag_news = 1")
    Page<News> findAllNews(Pageable pageable);







//    @Query(value = "select id," +
//            " code_news, " +
//            "date_news," +
//            " del_flag_news, " +
//            "description_news, " +
//            "image_news," +
//            "name_news, " +
//            "title_news, " +
//            "id_category " +
//            "from news " +
//            "where id = ?", nativeQuery = true)
//    Optional<News> findNewsById(Long id);

    @Query(value = "select id" +
            ", code_news" +
            ", date_news" +
            ", description_news" +
            ", image_news" +    
            ", name_news" +
            ", title_news" +
            ", id_category" +
            ", del_flag_news" +
            " from news where id = ?", nativeQuery = true)
    News findByIdNews(Long id);



    @Query(value = "select id, name_customer, gender_customer, birthday_customer, email_customer, phone_customer, del_flag_customer, " +
            "address_customer, point_customer, id_country ,id_customer_type, id_card_customer, image_customer from `customer` where del_flag_customer = '1'", nativeQuery = true
            ,countQuery = "select count(*) from news where del_flag_news = 1")
    List<News> getAllNewsNotPagination();


}




