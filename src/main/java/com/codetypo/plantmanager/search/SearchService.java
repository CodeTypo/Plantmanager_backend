package com.codetypo.plantmanager.search;

import com.codetypo.plantmanager.entity.Plant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SearchService {
    private final EntityManager entityManager;

    public List<Plant> getPlantsBasedOnWord(String word) {
        SearchSession searchSession = Search.session(entityManager);

        SearchResult<Plant> result = searchSession.search( Plant.class )
                    .where(f -> f.wildcard()
                    .field("name")
                    .matching(word+"*"))
                    .fetch(6);
//                .where( f -> f.match()
//                .field("name")
//                .matching(word+"*"))
//                .fetch(6);

        long totalHitCount = result.total().hitCount();
        List<Plant> hits = result.hits();

        return hits;
    }
}
