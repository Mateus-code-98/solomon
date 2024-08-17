package com.solomon.solomon.modules.users.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.solomon.solomon.modules.users.model.User;
import com.solomon.solomon.shared.dtos.ListInputDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CustomUserRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<User> list(ListInputDTO data) {
        Integer limit = data.limit();
        Integer page = data.page();
        Integer offset = (page - 1) * limit;

        String sql = """
                    SELECT
                        u.*
                    FROM
                        users u
                    WHERE
                        UNACCENT(name) ILIKE '%' || UNACCENT(:search) || '%'
                        OR UNACCENT(email) ILIKE '%' || UNACCENT(:search) || '%'
                        OR UNACCENT(phone) ILIKE '%' || UNACCENT(:search) || '%'
                    ORDER BY
                        u.created_at DESC
                    LIMIT
                        :limit
                    OFFSET
                        :offset
                """;

        Query query = em.createNativeQuery(sql, User.class);
        query.setParameter("search", data.search());
        query.setParameter("limit", limit);
        query.setParameter("offset", offset);

        return query.getResultList();
    }

    public Integer count(ListInputDTO data) {
        String sql = """
                    SELECT
                        COUNT(DISTINCT u.id)
                    FROM
                        users u
                    WHERE
                        UNACCENT(name) ILIKE '%' || UNACCENT(:search) || '%'
                        OR UNACCENT(email) ILIKE '%' || UNACCENT(:search) || '%'
                        OR UNACCENT(phone) ILIKE '%' || UNACCENT(:search) || '%'
                """;

        Query query = em.createNativeQuery(sql, Integer.class);

        query.setParameter("search", data.search());

        return (Integer) query.getSingleResult();
    }
}
