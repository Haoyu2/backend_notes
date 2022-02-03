import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     *
     *  Compare employee by  name(descending) and then age(ascending)
     *
     * @param other the other employee
     * @return int
     */
    @Override
    public int compareTo(Employee other) {
        int dif = name.compareTo(other.name);
        if (dif == 0) return Integer.compare(age, other.age);
        return -dif;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && Objects.equals(name, employee.name);
    }


    public static void main(String[] args) {
        String[] names = new String[]{"a", "b", "b", "b", "c"};
        int[] ages = new int[]{1,2,3,4,5};

        List<Employee> employeeList = new ArrayList<>();
        for (int i=0; i< names.length; i++)
            employeeList.add(new Employee(names[i], ages[i]));

        Collections.sort(employeeList);
        System.out.println(employeeList.toString());

    }
}
