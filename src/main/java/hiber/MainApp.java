package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car("bmw", 1);
      Car car2 = new Car("mercedes", 2);
      Car car3 = new Car("toyota", 3);
      Car car4 = new Car("honda", 4);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
         if (user.getCar() != null){
            System.out.println("Car model =" + user.getCar().getModel());
            System.out.println("Car series =" + user.getCar().getSeries());
         }
         else {
            System.out.println("нет заданной машины");
         }
         System.out.println();
      }
      User foundUser = userService.getUserByCar("bmw", 1);
      if (foundUser != null) {
         System.out.println("Look for user" + foundUser.getFirstName() + "with models of car" + foundUser.getCar().getModel());
      }
      else {
         System.out.println( "такой пользователь не найден");
      }

      context.close();
   }
}
