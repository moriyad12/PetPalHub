package com.example.PetPalHub.Dto;

import com.example.PetPalHub.Filters.Enums.FilterTypes;
import com.example.PetPalHub.Filters.FilterRelationList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FilterDto {
    List<FilterRelationList<FilterTypes, Object>> filters;
}
