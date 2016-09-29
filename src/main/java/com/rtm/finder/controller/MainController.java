package com.rtm.finder.controller;

import com.rtm.finder.ajax_entities.Message;
import com.rtm.finder.ajax_entities.ResultTable;
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
        return "index";
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        City perm = new City("Perm");
        City saratov = new City("Saratov");
        cityDao.save(perm);
        cityDao.save(saratov);

        Car blue = new Car("Blue");
        Car orange = new Car("Orange");
        Car purple = new Car("Purple");

        carDao.save(blue);
        carDao.save(orange);
        carDao.save(purple);

        Set<Car> cars = new HashSet<Car>();
        cars.add(blue);
        cars.add(orange);

        User user = new User("fname", "sname", perm, cars);
        userDao.save(user);

        Set<Car> cars2 = new HashSet<Car>();
        cars.add(purple);

        User user2 = new User("fname", "ssss", saratov, cars2);
        userDao.save(user2);

        return "index";
    }

    @RequestMapping(value = "/api/getUsers", method = RequestMethod.POST)
    @ResponseBody
    public ResultTable getUsers(Message message) {
        return findUsers(message);
    }

    private ResultTable findUsers(Message message) {
        String fname = message.getFirstName();
        String sname = message.getSecondName();
        String city = message.getCity();
        String color = message.getColor();

        List<User> users = userDao.findAll()
                .stream()
                .filter(user -> {
                    if (StringUtils.isEmpty(fname) || fname.equals(user.getFirstName())) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .filter(user -> {
                    if (StringUtils.isEmpty(sname) || sname.equals(user.getSecondName())) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .filter(user -> {
                    if (StringUtils.isEmpty(city) || city.equals(user.getCity().getName())) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .filter(user -> {
                    if (StringUtils.isEmpty(color) || user.getCarColors().contains(color)) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .sorted()
                .collect(Collectors.toList());

        return new ResultTable(users);
    }

}
