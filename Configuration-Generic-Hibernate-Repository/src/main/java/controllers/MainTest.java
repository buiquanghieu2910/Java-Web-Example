/**
 * BUI_QUANG_HIEU, 2023
 * MainTest.java
 */
package controllers;

import domain_models.User;
import repositories.Repository;
import repositories.impl.RepositoryImpl;

import java.util.List;

/**
 * @author BUI_QUANG_HIEU
 */
public class MainTest {

    public static void main(String[] args) {
        try {
            //Khởi tạo repository có đối tượng là User và kiểu dữ liệu khóa chính là String
            Repository<User, String> userRepository = new RepositoryImpl<>(User.class);

            //Tạo mới user
            //Ở đây mình tạo 2 user
            User userCreate1 = new User();
            userCreate1.setUsername("buiquanghieu");
            userCreate1.setPassword("12345678");

            User userCreate2 = new User();
            userCreate2.setUsername("tiennh21");
            userCreate2.setPassword("12345678");
            //Thêm vào db
            User userStored1 = userRepository.save(userCreate1);
            User userStored2 = userRepository.save(userCreate2);
            System.out.println("userStored1= " + userStored1.toString());
            System.out.println("userStored2= " + userStored2.toString());

            //Lấy tất cả User trong db
            List<User> users = userRepository.findAll();
            if (users.size() > 0) {
                users.forEach(u -> {
                    System.out.println(u.toString());
                });
            }

            //Lấy User theo Id
            User userById = userRepository.findById("402880e9875ce48001875ce484d20000"); //id ở đây mình lấy 1 id có sẵn trong db
            System.out.println("userById= " + userById.toString());

            //Edit User vừa tìm được
            userById.setUsername("TienNH21");
            //Cập nhật vào db
            User userUpdated = userRepository.update(userById);
            System.out.println("userUpdated= " + userUpdated);

            //Tìm kiếm theo điều kiện
            String hql = "SELECT u FROM User u WHERE u.username = 'TienNH21'";
            List<User> userSelect = userRepository.where(hql);
            userSelect.forEach(u -> {
                System.out.println(u.toString());
            });

//            Xóa User
            User userDeleted = userRepository.delete(userById);
            System.out.println("userDeleted= " + userDeleted.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
