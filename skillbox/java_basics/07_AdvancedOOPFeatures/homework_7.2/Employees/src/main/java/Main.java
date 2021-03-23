import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2016);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        Calendar c = Calendar.getInstance();
        return staff.stream()
                    .filter(e -> {
                        c.setTime(e.getWorkStart());
                        return c.get(Calendar.YEAR) == year;
                    })
                    .max(Comparator.comparing(Employee::getSalary))
                    .orElse(null);
    }
}