package org.tuxdevelop.spring.batch.lightmin.server.sample.application.client.domain;


import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tuxdevelop.spring.batch.lightmin.server.sample.application.client.persistence.dao.BatchTaskAddressDao;

@Component
public class BatchTaskAddressPopulator {

    @Autowired
    private BatchTaskAddressDao batchTaskAddressDao;

    private DataFactory dataFactory;

    public BatchTaskAddressPopulator() {
        this.dataFactory = new DataFactory();
    }

    @Scheduled(fixedDelay = 300000L)
    public void populateData() {
        for (int i = 0; i < 1000; i++) {
            final BatchTaskAddress batchTaskAddress = createBatchTaskAddress();
            batchTaskAddressDao.add(batchTaskAddress);
        }
    }

    private BatchTaskAddress createBatchTaskAddress() {
        final BatchTaskAddress batchTaskAddress = new BatchTaskAddress();
        final String street = dataFactory.getStreetName();
        final String houseNumber = dataFactory.getNumberText(4);
        final String zipCode = dataFactory.getNumberText(5);
        final String city = dataFactory.getCity();
        batchTaskAddress.setStreet(street);
        batchTaskAddress.setHouseNumber(houseNumber);
        batchTaskAddress.setProcessingState(ProcessingState.INIT);
        batchTaskAddress.setZipCode(zipCode);
        batchTaskAddress.setCity(city);
        return batchTaskAddress;
    }

}
