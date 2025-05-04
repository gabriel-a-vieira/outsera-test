package org.example.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.model.Producer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.ApplicationScoped;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class ProducerService {

    private SessionFactory sessionFactory;

    public ProducerService() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Producer.class)
                .buildSessionFactory();
    }

    public void loadCsvData() throws IOException, CsvException {

        Session session = sessionFactory.getCurrentSession();

        CSVReader reader = new CSVReader(new FileReader("src/main/resources/filmes.csv"));
        List<String[]> lines = reader.readAll();

        try {

            session.beginTransaction();

            for (String[] line : lines) {

                Producer producer = new Producer();

                producer.setProducer(line[0]);
                producer.setPreviousWin(Integer.parseInt(line[1]));
                producer.setFollowingWin(Integer.parseInt(line[2]));

                session.persist(producer);

            }

            session.getTransaction().commit();

        } finally {
            session.close();
        }

    }

}
