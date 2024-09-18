package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.model.CarService;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping(value = "/cars")
    public String listCars(@RequestParam(value = "count", required = false) Integer count, Model model) {
        List<Car> cars = carService.getAllCar();

        if (count != null && count > 0 && count < cars.size()) {
            cars = cars.subList(0, count);
        }

        model.addAttribute("cars", cars);
        return "cars";
    }

}
