import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice {

    public static void main(String[] args)  {



        List<String> strings = List.of("1","2 ","3"," ");
        // stream to convert string to int
        Function<? super String, ? extends Stream<?>> convertor =
                s -> {
            try {
                return Stream.of(Integer.parseInt(s));
            } catch(NumberFormatException nfe){

                }
            return Stream.empty();
                };
/*
        strings.stream()
                .flatMap(convertor)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        strings.stream()
                .<Integer>mapMulti((s,consumer) ->{
                    try {
                        consumer.accept(Integer.parseInt(s));
                    }catch(NumberFormatException nfe) {
                    }
                })
                .collect(Collectors.toList())
                .forEach(System.out::println);*/

        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));


        // How many male and female employees are there in the organization?
        System.out.println(employeeList.stream()
        .collect(Collectors.groupingBy(Employee::getGender,Collectors.counting())));

        System.out.println(employeeList.stream()
        .collect(Collectors.partitioningBy(e -> e.getGender().equals("Male"))));

        //Print the name of all departments in the organization?
employeeList.stream()
        .map(Employee::getDepartment)
        .distinct()
        .forEach(System.out::println);
        // What is the average age of male and female employees?

        System.out.println(employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingLong(Employee::getAge))));

        //Get the details of highest paid employee in the organization?

        System.out.println(employeeList.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)))
                .get().getName());

        //Get the names of all employees who have joined after 2015?

        employeeList.stream()
        .filter(e -> e.getYearOfJoining() > 2015)
        .collect(Collectors.toList()).forEach( e->{
                    System.out.println(e.getName());
                });
        //Count the number of employees in each department?
        System.out.println(employeeList.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.counting())));

        //What is the average salary of each department?

        System.out.println(employeeList.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary))));

        //Get the details of youngest male employee in the product development department?
        System.out.println(employeeList.stream()
        .filter(e -> e.getDepartment().equals("Product Development") && e.getGender().equals("Male"))
        .min(Comparator.comparingDouble(Employee::getAge)).get().getName());


        //Who has the most working experience in the organization?
        System.out.println(employeeList.stream()
        .sorted(Comparator.comparingDouble(Employee::getYearOfJoining)).findFirst().get().getName());

        //How many male and female employees are there in the sales and marketing team?
        System.out.println(employeeList.stream()
        .filter(e -> e.getDepartment().equals("Sales And Marketing"))
        .collect(Collectors.groupingBy(Employee::getGender,Collectors.counting())));

        //What is the average salary of male and female employees?
        System.out.println(employeeList.stream()
        .collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary))));

        //List down the names of all employees in each department?
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .entrySet().stream()
                .forEach(e -> {
                    System.out.println(e.getKey()+ e.getValue().get(0).getName());
                });

        //What is the average salary and total salary of the whole organization?
        System.out.println(employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getSalary, Collectors.summarizingDouble(Employee::getSalary)))
        );



        //Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        System.out.println(employeeList.stream()
        .collect(Collectors.partitioningBy(e-> e.getAge()>25)));

        //Who is the oldest employee in the organization? What is his age and which department he belongs to?
Employee e = employeeList.stream()
        .max(Comparator.comparingDouble(Employee::getAge)).get();
        System.out.println(e.getAge() + e.getDepartment()+ e.getName());


    }
}
