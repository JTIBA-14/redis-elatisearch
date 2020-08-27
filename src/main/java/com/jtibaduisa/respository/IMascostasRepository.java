package com.jtibaduisa.respository;

import com.jtibaduisa.entity.Mascotas;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMascostasRepository extends ElasticsearchRepository<Mascotas, String> {
}
