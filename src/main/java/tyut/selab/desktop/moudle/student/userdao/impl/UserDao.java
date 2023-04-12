package tyut.selab.desktop.moudle.student.userdao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import tyut.selab.desktop.moudle.student.domain.Role;
import tyut.selab.desktop.moudle.student.domain.User;
import tyut.selab.desktop.moudle.student.userdao.IUserDao;
import tyut.selab.desktop.utils.MysqlConnect;

import java.awt.desktop.QuitResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements IUserDao {
    private QueryRunner qr = new QueryRunner();
    @Override
    public List<User> queryUser() {
        Connection connection =null;
        try {
            String sql = "select * from user";
            connection = MysqlConnect.getConnection();
            return qr.query(connection,sql,new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MysqlConnect.close(null,null,connection);
        }
    }
    @Override
    public User queryUserByStudentNumber(Integer studentNumber) {
        Connection connection = null;
        try {
            connection  = MysqlConnect.getConnection();
            String sql  ="select * from user where student_number = ?";
            return qr.query(connection,sql,new BeanHandler<User>(User.class),studentNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public User queryUserByStudentName(String name) {
        Connection connection = null;
        try {
            connection  = MysqlConnect.getConnection();
            String sql  ="select * from user where name = ?";
            return qr.query(connection,sql,new BeanHandler<User>(User.class),name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int insertUser(User user) {
        Connection connection =null;
        try {
            connection = MysqlConnect.getConnection();
            String sql = "insert into user value(null,?,?,?,?,?,?,?,?,?,?)";
            int insert = qr.update(connection,sql,user.getAccountNumber(),
                    user.getPassword(),user.getName(),user.getStudentNumber(),
                    user.getGender(),user.getPhone(),
                    user.getPost(),user.getRegisterTime(),
                    user.getLoginStatus(),user.getRoleId());
            return (insert > 0? 1 : -1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MysqlConnect.close(null,null,connection);
        }
    }
    @Override
    public int updateUser(String password, User oldUser) {
        Connection connection =null;
        try {
            connection = MysqlConnect.getConnection();
            String sql = "update user set password =? where user_id =?";
            int update = qr.update(connection,sql,password,oldUser);
            return (update > 0? 1 : -1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MysqlConnect.close(null,null,connection);
        }
    }
    @Override
    public int deleteUser(int studentNumber) {
        Connection connection =null;
        try {
            connection = MysqlConnect.getConnection();
            String sql = "delete from user where student_number = ?";
            int delete = qr.update(connection,sql,studentNumber);
            return (delete > 0? 1 : -1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MysqlConnect.close(null,null,connection);
        }
    }
    @Override
    public List<Role> queryAllRole() {
        Connection connection =null;
        try {
            connection = MysqlConnect.getConnection();
            String sql = "select * from user_role";
            return qr.query(connection,sql,new BeanListHandler<Role>(Role.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MysqlConnect.close(null,null,connection);
        }
    }
    @Override
    public int insertRole(Role role) {
        Connection connection =null;
        try {
            connection = MysqlConnect.getConnection();
            String sql = "insert into user_role value(null,?)";
            int insert = qr.update(connection,sql,role.getDuty());
            return (insert > 0? 1 : -1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MysqlConnect.close(null,null,connection);
        }
    }

    @Override
    public int updateRole(Role newRole, Role oleRole) {
        Connection connection =null;
        try {
            connection = MysqlConnect.getConnection();
            String sql = "update user_role set duty =? where duty = ?";
            int update = qr.update(connection,sql,newRole,oleRole);
            return (update > 0? 1 : -1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MysqlConnect.close(null,null,connection);
        }
    }

    @Override
    public int deleteRole(Role role) {
        Connection connection =null;
        try {
            connection = MysqlConnect.getConnection();
            String sql = "delete from user_role where duty = ?";
            int update = qr.update(connection,sql,role);
            return (update > 0? 1 : -1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MysqlConnect.close(null,null,connection);
        }
    }
}
