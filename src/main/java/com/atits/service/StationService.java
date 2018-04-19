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
     * 添加一个station
     * @param station
     */
    public void save(Station station){
        stationDao.save(station);
    }


    /**
     * 通过id删除一个station
     * @param id
     */
    public void deletById(Integer id){
        stationDao.deletById(id);
    }

    /**
     * 根据id数组批量删除station
     * @param idList
     */
    public void deletByIds(List<Integer> idList){
        stationDao.deletByIds(idList);
    }

    /**
     * 更新一个station
     * @param station
     */
    public void update(Station station){
        stationDao.update(station);
    }


    /**
     * 根据id查找一个station
     * @param id
     */
    public Station findById(Integer id){
        return stationDao.findById(id);
    }

    /**
     *查找所有station
     */
    public List<Station> findAll(){
        return stationDao.findAll();
    }

}
