package io.delivery.service.impl;

import io.delivery.dao.BasicDao;
import io.delivery.dao.DocumentDao;
import io.delivery.entity.Document;
import io.delivery.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NortT on 15.04.2017.
 */
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    DocumentDao documentDao;

    @Override
    public List<Document> getDocumentList() {
        return documentDao.getList();
    }

    @Override
    public List<Document> findByName(String name) {
        return documentDao.findByName(name);
    }

    @Override
    public Document create(Document document) {
        return documentDao.create(document);
    }

    @Override
    public Document update(Document document) {
        return documentDao.update(document);
    }

    @Override
    public Document delete(long id) {
        return documentDao.delete(findById(id));
    }

    @Override
    public Document findById(long id) {
        return documentDao.findById(id);
    }


}
