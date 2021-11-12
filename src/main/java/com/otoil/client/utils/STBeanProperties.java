package com.otoil.client.utils;


import java.util.Date;

import com.otoil.client.dto.DocCardKindStructureBean;
import com.otoil.client.dto.ResponseDocumentCardBean;

import ru.ot.gwt.utils.client.BeanProperty;


public class STBeanProperties
{
    public static final BeanProperty<DocCardKindStructureBean, String> stId = BeanProperty
        .create(DocCardKindStructureBean::getId,
            DocCardKindStructureBean::setId);

    public static final BeanProperty<DocCardKindStructureBean, String> stName = BeanProperty
        .create(DocCardKindStructureBean::getName,
            DocCardKindStructureBean::setName);

    public static final BeanProperty<DocCardKindStructureBean, String> stParent = BeanProperty
        .create(DocCardKindStructureBean::getParentId,
            DocCardKindStructureBean::setParentId);

    public static final BeanProperty<DocCardKindStructureBean, String> stPath = BeanProperty
        .create(DocCardKindStructureBean::getCatDccrdkndstIdPath,
            DocCardKindStructureBean::setCatDccrdkndstIdPath);

    public static final BeanProperty<ResponseDocumentCardBean, String> dcName = BeanProperty
        .create(ResponseDocumentCardBean::getName,
            ResponseDocumentCardBean::setName);

    public static final BeanProperty<ResponseDocumentCardBean, String> dcOrderedNumber = BeanProperty
        .create(ResponseDocumentCardBean::getOrderNumber,
            ResponseDocumentCardBean::setOrderNumber);

    public static final BeanProperty<ResponseDocumentCardBean, Date> dcChangeDate = BeanProperty
        .create(ResponseDocumentCardBean::getChangeDate,
            ResponseDocumentCardBean::setChangeDate);

    public static final BeanProperty<ResponseDocumentCardBean, String> dcImage = BeanProperty
        .create(ResponseDocumentCardBean::getBinaryData,
            ResponseDocumentCardBean::setBinaryData);
}
