package com.atits.dao;

import com.atits.entity.TestStart;
import com.atits.entity.TestWeight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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
        String hql = "select new TestStart (id,system.id) from TestStart Where id=:id";
        return (TestStart)getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    public TestStart findById2(Integer id){
        String hql = "from TestStart Where id=:id";
        return (TestStart)getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    public List findUsers(Integer id){
        String hql="select t.users from TestStart as t where t.id=:id";
        return getSession().createQuery(hql).setParameter("id",id).list();
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
        for (Integer id:idList){
            deleteById(id);
        }
    }

    public void deleteById(Integer id){
        TestStart testStart = findById2(id);
        getSession().delete(testStart);
        deleteTestWeight(testStart.getTestWeight().getId());
    }

    public void deleteTestWeight(Integer id){
        String hql="delete from TestWeight where id=:id";
        getSession().createQuery(hql).setParameter("id",id).executeUpdate();
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
