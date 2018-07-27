package com.atits.dao;

import com.atits.entity.TestStart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestStartDao {

    @Autowired
    private SessionFactory sessionFactory;
    private Session getSession(){return sessionFactory.getCurrentSession();}

    public List findAll(){
        String hql = "from TestStart";
        return getSession().createQuery(hql).list();
    }

    public TestStart findById(Integer id){
        String hql = "from TestStart Where id=:id";
        return (TestStart)getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    public void save(TestStart testStart){
        getSession().save(testStart);
    }

    public List<TestStart> findByState(int state){
        String hql = "from TestStart where state=:state";
        return getSession().createQuery(hql).setParameter("state",state).list();
    }

    /* 删除方法：deletes---批量删除，避免页面删除不了 */

    public void deleteByIds(List<Integer> idList) {// idList：id列表参数
        String hql = "";// 初始化为空
        for (int i = 0; i < idList.size(); i++) {
            if (i == 0) {
                hql = "id=" + idList.get(i);
            } else {
                hql = hql + " or id=" + idList.get(i);
            }
        }
        // 执行为删除方法
        getSession().createQuery("delete from  TestStart where " + hql).executeUpdate();
    }

    public void deleteById(Integer id){
        TestStart testStart = findById(id);
        getSession().delete(testStart);
    }

    public void update(TestStart testStart){getSession().update(testStart);}

    /**
     * 更新状态
     */
    public void updateState(int id,int state){
        String hql="update TestStart as ts set ts.state=:state where ts.id=:id";
        getSession().createQuery(hql).setParameter("state",state).setParameter("id",id).executeUpdate();
    }
}
