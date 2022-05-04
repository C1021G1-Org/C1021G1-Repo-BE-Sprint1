package com.codegym.repository;

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

@Repository
@Transactional
public interface INewsRepository extends JpaRepository<News, Long> {
    @Query(value ="select id, code_news , date_news , del_flag_news , description_news , image_news ,name_news ,title_news ,id_category \n" +
            "from news \n" +
            "where del_flag_news = 1",
            nativeQuery = true,
            countQuery = "select count(*) from news where del_flag_news = 1")
    Page<News> findAllNews(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE news" +
            " SET del_flag_news = 0 " +
            "where id = ?", nativeQuery = true)
    void removeNewsById(Long id);


    @Query(value = "select id," +
            " code_news, " +
            "date_news," +
            " del_flag_news, " +
            "description_news, " +
            "image_news," +
            "name_news, " +
            "title_news, " +
            "id_category " +
            "from news " +
            "where id = ?", nativeQuery = true)
    Optional<News> findNewsById(Long id);

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
