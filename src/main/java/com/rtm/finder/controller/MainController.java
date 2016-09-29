package com.rtm.finder.controller;

import com.rtm.finder.ajax_entities.Message;
import com.rtm.finder.ajax_entities.ResultTable;
import com.rtm.finder.ajax_entities.Row;
import com.rtm.finder.dao.CarDao;
import com.rtm.finder.dao.CityDao;
import com.rtm.finder.dao.UserDao;
import com.rtm.finder.entity.Car;
import com.rtm.finder.entity.City;
import com.rtm.finder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    UserDao userDao;

    @Autowired
    CityDao cityDao;

    @Autowired
    CarDao carDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {


        String fname = "fname";
        List<User> users = userDao.findAll()
                .stream()
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) {
                        if(StringUtils.isEmpty(fname)){
                            return true;
                        } else {
                            if(fname.equals(user.getFirstName())){
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                })
                .collect(Collectors.toList());
        modelMap.addAttribute("attr",users);
        return "index";
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        City city = new City("Perm");
        cityDao.save(city);

        Car car = new Car("Orange");
        carDao.save(car);

        Set<Car> cars = new HashSet<Car>();
        cars.add(car);
        User user = new User("fname", "sname", city, cars);

        userDao.save(user);

        return "index";
    }


//    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
//    public String getUsers(ModelMap modelMap) {
//        // 查询user表中所有记录
//        List<UserEntity> userList = userRepository.findAll();
//
//        // 将所有记录传递给要返回的jsp页面，放在userList当中
//        modelMap.addAttribute("userList", userList);
//
//        // 返回pages目录下的admin/users.jsp页面
//        return "admin/users";
//    }
//
//    // get请求，访问添加用户 页面
//    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
//    public String addUser() {
//        // 返回 admin/addUser.jsp页面
//        return "admin/addUser";
//    }
//
//    // post请求，处理添加用户请求，并重定向到用户管理页面
//    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
//    public String addUserPost(@ModelAttribute("user") UserEntity userEntity) {
//        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
//        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象
//
//        // 数据库中添加一个用户，该步暂时不会刷新缓存
//        //userRepository.save(userEntity);
//        System.out.println(userEntity.getFirstName());
//        System.out.println(userEntity.getLastName());
//
//        // 数据库中添加一个用户，并立即刷新缓存
//        userRepository.saveAndFlush(userEntity);
//
//        // 重定向到用户管理页面，方法为 redirect:url
//        return "redirect:/admin/users";
//    }
//
//    // 查看用户详情
//    // @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
//    // 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
//    @RequestMapping(value = "/admin/users/show/{id}", method = RequestMethod.GET)
//    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap) {
//
//        // 找到userId所表示的用户
//        UserEntity userEntity = userRepository.findOne(userId);
//
//        // 传递给请求页面
//        modelMap.addAttribute("user", userEntity);
//        return "admin/userDetail";
//    }
//
//    // 更新用户信息 页面
//    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)
//    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {
//
//         传递给请求页面
//        modelMap.addAttribute("user", userEntity);
//        return "admin/updateUser";
//    }
//

    @RequestMapping(value = "/api/getUsers", method = RequestMethod.POST)
    @ResponseBody
    public ResultTable getUsers(Message message){
        Set<Row> rows = new HashSet<Row>();
        rows.add(new Row("a","b","c","d"));
        rows.add(new Row("a2","b2","c2","d2"));

        ResultTable resultTable = new ResultTable();
        resultTable.setRows(rows);
        return resultTable;
    }


//    // 更新用户信息 操作
//    @RequestMapping(value = "/admin/users/updateP", method = RequestMethod.POST)
//    public String updateUserPost(@ModelAttribute("userP") UserEntity user) {
//
//        // 更新用户信息
//        userRepository.updateUser(user.getNickname(), user.getFirstName(),
//                user.getLastName(), user.getPassword(), user.getId());
//        userRepository.flush(); // 刷新缓冲区
//        return "redirect:/admin/users";
//    }
//
//    // 删除用户
//    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
//    public String deleteUser(@PathVariable("id") Integer userId) {
//
//        // 删除id为userId的用户
//        userRepository.delete(userId);
//        // 立即刷新
//        userRepository.flush();
//        return "redirect:/admin/users";
//    }
}
