package com.example.electronicstore.mapper;

import com.example.electronicstore.entity.Deal;
import com.example.electronicstore.dto.DealDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DealMapper {
    DealMapper INSTANCE = Mappers.getMapper(DealMapper.class);

    DealDTO toDTO(Deal deal);
    Deal toEntity(DealDTO dealDTO);

    List<DealDTO> toDTOList(List<Deal> deals);
}
