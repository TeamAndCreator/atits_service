package com.atits.service;

import com.atits.dao.StationDao;
import com.atits.entity.Station;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void deleteById(Integer id){
        stationDao.deleteById(id);
    }

    /**
     * 根据id数组批量删除station
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        for (int id:idList){
            stationDao.deleteStaId(id);
        }
        stationDao.deleteByIds(idList);
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

    /**
     * 查找所有的lab(id,labName,company,system.systemName,time,date,state)及主任
     */
    public List findAll1(int systemId) {
        List<Map> stations = new ArrayList<>();
        List<Station> stationList;
        if (systemId == 1) {
            stationList = stationDao.findAll2();
        } else {
            stationList = stationDao.findAllInSystem2(systemId);
        }
        for (Station station : stationList) {
            List<String> zr = stationDao.findUserInRole(station.getId(), 7);
            Map temp = new HashMap();
            temp.put("station", station);
            temp.put("zr", zr);
            stations.add(temp);
        }
        return stations;
    }

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        return stationDao.findPage(startRow,pageSize);
    }

    /**
     * 获取某个体系的所有实验站的id,name
     */
    public List findAllInSystem(int systemId){
        return stationDao.findAllInSystem(systemId);
    }

    /**
     * 获取某个实验站具有某个权限的所有user
     */
    public List findUserInRole(int stationId,int roleId){
        return stationDao.findUserInRole(stationId,roleId);
    }

    /**
     * 获取个数
     */
    public int getCount(){
        return stationDao.getCount();
    }

    /**
     * 更新状态（激活）
     */
    public void updateState(int staId){
        stationDao.updateState(staId);
    }
}
