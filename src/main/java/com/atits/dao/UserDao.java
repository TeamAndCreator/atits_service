package com.atits.dao;

import com.atits.entity.Role;
import com.atits.entity.User;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Set;

/**
 * @author zys
 */
@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {//获取session
        return sessionFactory.getCurrentSession();
    }

    /**
     * 查找所有user
     */
    public List<User> findAll() {
        String hql = "from User ";
        return getSession().createQuery(hql).list();
    }

    public User findByUserName(String username) {
        String hql = "from User where userName=:username";
        return (User) getSession().createQuery(hql).setParameter("username", username).uniqueResult();
    }

    public User findById(int id) {
        String hql = "from User where id=:id";
        return (User) getSession().createQuery(hql).setParameter("id", id).uniqueResult();
    }


    //根据体系id和角色id来获取该体系与该角色的所有用户--启动表中使用  用于首席
    public List findTestPer(int sysId) {
        String hql = "select new User(u.id,u.profile.name) from User as u where u.system.id =:sysId";
        return getSession().createQuery(hql).setParameter("sysId", sysId).list();
    }

    //根据体系id和角色id来获取该体系与该角色的所有用户--启动表中使用  用于体系办
    public List findTestPer2() {
        String hql = "select new User(u.id,u.profile.name) from User as u  join u.roles as r where r.id=3 or r.id=4";
        return getSession().createQuery(hql).list();
    }

    public List findTestPer3(Integer systemId) {
        String hql = "select new User(u.id,u.profile.name) from User as u  join u.roles as r where (r.id=3 or r.id=4) and u.system.id=:systemId";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取外聘人员
     * @return
     */
    public List findExternal() {
        String hql = "select new User(u.id,u.profile.id,u.profile.name,state)from User as u where u.system.id=null ";
        return getSession().createQuery(hql).list();
    }

    //shiro认证中使用--MyRealm.java
    public List findRoleById(String userName) {
        String hql = "select u.roles from User as u where u.userName=:userName";
        return getSession().createQuery(hql).setParameter("userName", userName).list();
    }

    public List findRoleById2(int userId) {
        String hql = "select u.roles from User as u where u.id=:userId";
        return getSession().createQuery(hql).setParameter("userId", userId).list();
    }

    public List findUsersBySystemId(int systemId){
        String hql="select new User (id,profile.name,system.systemName,state)from User where system.id=:systemId";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     *查找首席和副首席的 (id,profile.name,system.systemName,state)
     * @return
     */
    public List findUsersBySystemId2() {
        String hql = "select new User (u.id,u.profile.name,u.system.systemName,u.state) from User as u  join u.roles as r where r.id=3 or r.id=4";
        return getSession().createQuery(hql).list();
    }

    public void updateState(int userId){
        String hql="update User as u set u.state=1 where u.id=:userId";
        getSession().createQuery(hql).setParameter("userId",userId).executeUpdate();
    }

    /**
     * 添加一个user
     */
    public void save(User user) {
        getSession().save(user);
    }

    /**
     * 修改密码
     * @param userId
     * @param password
     */
    public void changePassword(int userId,String password){
        String hql="update User set password=:password where id=:userId";
        getSession().createQuery(hql).setParameter("password",password).setParameter("userId",userId).executeUpdate();
    }

    public String findPassword(int userId){
        String hql="select password from User where id=:userId";
        return (String)getSession().createQuery(hql).setParameter("userId",userId).uniqueResult();
    }

    public void update(User user) {
        getSession().update(user);
    }

    public void deleteById(Integer userid) {
        User user = findById(userid);
        getSession().delete(user);
    }

    public void deleteByIds(List<Integer> userIds) {
        for (int i = 0; i < userIds.size(); i++) {
            User user = findById(userIds.get(i));
            getSession().delete(user);
        }
    }

    /**
     * 删除某个体系中的所有人，用于体系删除
     */
    public void deleteBySystemId(int systemId){
        String hql="delete from User where system.id=:systemId";
        getSession().createQuery(hql).setParameter("systemId",systemId).executeUpdate();
    }


}
