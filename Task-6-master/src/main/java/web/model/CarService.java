package web.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarService {

    private List<Car> carList;

    public CarService() {
        carList = new ArrayList<Car>();
        carList.add(new Car("Mustang_GT", "red", 2001));
        carList.add(new Car("Mustang", "yellow", 2002));
        carList.add(new Car("Camaro", "green", 2003));
        carList.add(new Car("Ferrari", "blue", 2004));
        carList.add(new Car("Mercedes", "yellow", 2005));
    }


    public List<Car> getAllCar() {
        return carList;
    }
}
