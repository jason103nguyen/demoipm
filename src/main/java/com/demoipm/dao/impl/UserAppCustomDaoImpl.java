package com.demoipm.dao.impl;

import com.demoipm.dao.UserAppCustomDao;
import com.demoipm.entities.UserApp;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserAppCustomDaoImpl implements UserAppCustomDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<UserApp> fullTextSearchByCondition(String searchWord, Pageable pageable) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(UserApp.class)
                .get();

        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .wildcard()
                .onFields("fullName", "phone", "email", "username")
                .matching("*" + searchWord + "*")
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery jpaQuery
                = fullTextEntityManager.createFullTextQuery(query, UserApp.class);

        jpaQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        jpaQuery.setMaxResults(pageable.getPageSize());

        org.apache.lucene.search.Sort luceneSorts = new org.apache.lucene.search.Sort();
        List<SortField> sortFields = new ArrayList<>();
        Iterator<org.springframework.data.domain.Sort.Order> sortIterator = pageable.getSort().iterator();
        while (sortIterator.hasNext()) {
            Sort.Order order = sortIterator.next();
            boolean isReversed = false;
            if (order.isDescending()) {
                isReversed = true;
            }
            sortFields.add(new SortField(order.getProperty(), SortField.Type.STRING, isReversed));
        }
        luceneSorts.setSort(sortFields.toArray(new SortField[sortFields.size()]));

        jpaQuery.setSort(luceneSorts);

        Page<UserApp> userAppPage = new PageImpl<UserApp>(jpaQuery.getResultList(), pageable, jpaQuery.getResultSize());
        return userAppPage;
    }
}
