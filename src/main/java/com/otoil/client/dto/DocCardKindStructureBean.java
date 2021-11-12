package com.otoil.client.dto;


import com.otoil.shared.DefaultTreeNamedBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocCardKindStructureBean extends DefaultTreeNamedBean
{
    private String id; // private String catDccrdkndstId;
    private String name;
    private String parentId; // private String catParentId;

    private String catDccrdkndstIdPath;
    private String catRoot;

    private String catName;
    private String catIdentifier;
    private String catDescription;

    private String catOrderNumber;
    private String catKindId;

    private String catBaseId;
    private String catMaximumInstances;
    private String catGroupsInd;

    private String catGroupsId;
    private String catGroupsName;
    private String catKindsInd;

    private String catKindsId;
    private String catKindsName;
}
