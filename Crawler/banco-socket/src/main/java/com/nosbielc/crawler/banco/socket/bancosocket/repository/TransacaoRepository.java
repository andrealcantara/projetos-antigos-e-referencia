package com.nosbielc.crawler.banco.socket.bancosocket.repository;

import com.nosbielc.crawler.banco.socket.bancosocket.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
