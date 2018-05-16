package com.atits.dao;

import com.atits.entity.TestManage;
import com.atits.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestManageDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session getSession(){return sessionFactory.getCurrentSession();}

    public List findAll(){
        String hql = "from TestManage";
        return getSession().createQuery(hql).list();
    }

//    #找出testStart中的被考评人（考评中，本体系人员）
//    SELECT * FROM  t_test_start tts ,t_test_start_t_user ttstu,t_user tu WHERE tts.id = ttstu.TestStart_id AND ttstu.users_id = tu.id AND tts.state=2 AND tu.system_id = 14;
//#找出testStart中的考评人（考评中，省体系办、外聘专家、本体系人员）
//    SELECT * FROM  t_test_start tts ,t_test_start_t_user ttstu,t_user tu,t_user_t_role tutr WHERE tts.id = ttstu.TestStart_id AND ttstu.users_id = tu.id AND tu.id = tutr.User_id AND tts.state=2 AND (tu.system_id = 14 OR tutr.roles_id = 1);
//#考评人中包含被考评人


    public void save(TestManage testManage){
        getSession().save(testManage);
    }

}
