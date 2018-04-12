package com.atits.service;

import com.atits.dao.StationDao;
import com.atits.entity.Station;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class StationService {

    @Resource
    private StationDao stationDao;

    /**
     *添加一个laboratory
     */
    public void save(Station station){
        stationDao.save(station);
    }

    /**
     *查找所有laboratory
     */
    public List<Station> findAll(){
        return stationDao.findAll();
    }


}
