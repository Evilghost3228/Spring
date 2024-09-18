package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      // Очистка всех таблиц перед добавлением новых данных
      userService.cleanAllTables();

      // Создание автомобилей
      Car car1 = new Car("BMW", 2024);
      Car car2 = new Car("Lada", 2000);

      // Создание пользователей с автомобилями
      User user1 = new User("Egor", "Lastname1", "user1@mail.ru", car1);
      User user2 = new User("Artem", "Lastname2", "user2@mail.ru", car2);

      // Добавление пользователей
      userService.add(user1);
      userService.add(user2);

      // Вывод всех пользователей
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car Model = " + user.getCar().getModel());
            System.out.println("Car Series = " + user.getCar().getSeries());
         } else {
            System.out.println("No car associated");
         }
         System.out.println();
      }

      // Поиск пользователя по модели и серии автомобиля
      User foundUser = userService.findUserByCarModelAndSeries("BMW", 2024);  // Замените на правильные параметры поиска
      if (foundUser != null) {
         System.out.println("Found User:");
         System.out.println("Id = " + foundUser.getId());
         System.out.println("First Name = " + foundUser.getFirstName());
         System.out.println("Last Name = " + foundUser.getLastName());
         System.out.println("Email = " + foundUser.getEmail());
         if (foundUser.getCar() != null) {
            System.out.println("Car Model = " + foundUser.getCar().getModel());
            System.out.println("Car Series = " + foundUser.getCar().getSeries());
         } else {
            System.out.println("No car associated");
         }
      } else {
         System.out.println("User not found");
      }

      context.close();
   }
}
