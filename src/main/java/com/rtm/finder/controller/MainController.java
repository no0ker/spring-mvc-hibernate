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
        City city = new City("Perm");
        cityDao.save(city);

        Car car = new Car("Orange");
        carDao.save(car);

        Set<Car> cars = new HashSet<Car>();
        cars.add(car);
        User user = new User("fname", "sname", city, cars);
        userDao.save(user);

        User user2 = new User("fname", "ssss", city, cars);
        userDao.save(user);
        
        return "index";
    }

    @RequestMapping(value = "/api/getUsers", method = RequestMethod.POST)
    @ResponseBody
    public ResultTable getUsers(Message message) {
        return findUsers(message);
    }

    private ResultTable findUsers(Message message) {
        String fname = message.getFirstName();

        List<User> users = userDao.findAll()
                .stream()
                .filter(user -> {
                    if (StringUtils.isEmpty(fname)) {
                        return true;
                    } else {
                        if (fname.equals(user.getFirstName())) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                })
                .collect(Collectors.toList());

        return new ResultTable(users);
    }

}
