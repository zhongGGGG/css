package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.ConstRepository;
import com.t248.zjl.thymeleaf.entity.Constitute;
import com.t248.zjl.thymeleaf.service.ConstService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConstServiceImpl implements ConstService {

    @Resource
    private ConstRepository constRepository;


    @Override
    public List<Constitute> listConstitute() {
        List<Object[]> list=constRepository.listConstitute();

        List<Constitute> constitutes=new ArrayList<>();

        for (Object[] objects: list){
            Constitute constitute=new Constitute();
            constitute.setValu((objects[0].toString()));
            constitute.setItems(objects[1].toString());
            constitute.setCount(objects[2].toString());
            constitutes.add(constitute);
        }
        return constitutes;
    }

    @Override
    public List<Constitute> listquanbu() {
        List<Object[]> list=constRepository.listquanbu();
        List<Constitute> constituteList=new ArrayList<>();
        for (Object[] obj:list
             ) {
            Constitute constitute=new Constitute();
            constitute.setValu(obj[0].toString());
            constitute.setCount(obj[1].toString());
            constitute.setItems(obj[2].toString());
            constituteList.add(constitute);
        }
        return constituteList;
    }
}
