package com.rtm.finder.ajax_entities;

import com.rtm.finder.entity.Car;
import com.rtm.finder.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResultTable {
    private Set<Row> rows = new HashSet<>();

    public ResultTable(List<User> users) {
        for(User user : users){
            for(Car car: user.getCar()){
                rows.add(new Row(user.getFirstName(),
                        user.getSecondName(),
                        user.getCity().getName(),
                        car.getColor()));
            }
        }
    }

    public Set<Row> getRows() {
        return rows;
    }

    public void setRows(Set<Row> rows) {
        this.rows = rows;
    }
}
