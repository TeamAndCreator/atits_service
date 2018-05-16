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

    public List findAllPart(){
        String hql = "select TestManage (id,examiner.id,examiner.userName,examiner.system.systemName,examiner.roles" +
                ",examedner.id,examedner.userName,examedner.system.systemName,examedner.roles)from TestManage ";
        return getSession().createQuery(hql).list();
    }
/*
 testStart:state=2表示考评已经开始，此时TestManage.state=0表示未考评
 */
    public List insertAuto(){//还需要插入考评人员
        String hql = "insert into TestManage(year)  select t.year from TestStart t where t.state = 2";
        return getSession().createQuery(hql).list();
    }

//    #找出testStart中的被考评人（考评中，本体系人员）
//    SELECT * FROM  t_test_start tts ,t_test_start_t_user ttstu,t_user tu WHERE tts.id = ttstu.TestStart_id AND ttstu.users_id = tu.id AND tts.state=2 AND tu.system_id = 14;
//#找出testStart中的考评人（考评中，省体系办、外聘专家、本体系人员）
//    SELECT * FROM  t_test_start tts ,t_test_start_t_user ttstu,t_user tu,t_user_t_role tutr WHERE tts.id = ttstu.TestStart_id AND ttstu.users_id = tu.id AND tu.id = tutr.User_id AND tts.state=2 AND (tu.system_id = 14 OR tutr.roles_id = 1);
//#考评人中包含被考评人

    //插入被考评人员
    public List<TestManage> insertExamedner(){
        String hql = "insert into TestManage(examedner) select tsu.id from TestStart ts inner join TestStart.users tsu where ts.state=2 and tsu.system.id=14";
                return getSession().createQuery(hql).list();
    }

    public void save(TestManage testManage){
        getSession().save(testManage);
    }

}
