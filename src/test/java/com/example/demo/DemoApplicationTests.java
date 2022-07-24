package com.example.demo;

import com.example.demo.domain.SampleTable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

	@PersistenceContext EntityManager em;

	@BeforeEach
	void createSchema() {
		em.createNativeQuery("create table sample_table (id1 bigint, id2 bigint, message varchar(255))").executeUpdate();
	}

	@Test
	@Transactional
	void contextLoads() {
		em.createNativeQuery("insert into sample_table values (1,1,'a')").executeUpdate();
		em.createNativeQuery("insert into sample_table values (1,2,'b')").executeUpdate();
		em.createNativeQuery("insert into sample_table values (2,3,'c')").executeUpdate();
		em.createNativeQuery("insert into sample_table values (3,4,'d')").executeUpdate();

		var query = em.createQuery("select s from SampleTable s", SampleTable.class);
		var elements = query.getResultList();

		var missingElement = new SampleTable();
		missingElement.setId1(1L);
		missingElement.setId2(2L);
		missingElement.setMessage("b");

		log.info("fetched following elements: {}", elements);

		assertThat(elements, hasItems(missingElement));
	}

}
