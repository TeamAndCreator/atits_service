package com.atits.dao;

import com.atits.entity.TestScore;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestScoreDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {//获取session
        return sessionFactory.getCurrentSession();
    }

    /*以下：是系统动态显示：相关方法*/
    /*
     * 查询所有的动态：方法
	 */
    public List<TestScore> findAll(){
        String hql = "from TestScore";
        return getSession().createQuery(hql).list();
    }

    /*根据ID查询*/
    public TestScore findById(Integer id) {
        String hql = "from TestScore where id =:id";
        return (TestScore) getSession().createQuery(hql).setParameter("id", id).uniqueResult();
    }

    /*保存和更新方法：id=0 是保存，id≠0是更新*/
    public void save(TestScore testScore) {getSession().save(testScore); }
    public void update(TestScore testScore){getSession().update(testScore);}

    /*删除方法：deletes---批量删除，避免页面删除不了*/
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
        getSession().createQuery("delete from  TestScore where " + hql).executeUpdate();
    }

//    public void deletes(List<Integer> idList) {//idList：id列表参数
//        for (int i = 0; i < idList.size(); i++) {
//            TestScore testScore = findById(idList.get(i));
//            getSession().delete(testScore);
//        }
//    }

    public void deleteById(Integer id){
        TestScore testScore = findById(id);
        getSession().delete(testScore);
    }



}
