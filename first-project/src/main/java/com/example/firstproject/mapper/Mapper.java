package com.example.firstproject.mapper;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destiny) {
        return modelMapper.map(origin, destiny);
    }

    public static <O, D> List<D> parseListObjects(List<O> originList, Class<D> destiny) {
        List<D> destinyList = new ArrayList<>();
        for (O o : originList) {
            destinyList.add(modelMapper.map(o, destiny));
        }
        return destinyList;
    }
}
