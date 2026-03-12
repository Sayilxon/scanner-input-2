import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ScannerInputAdvanced {

    record Student(String name, int age, double gpa) {
        String status() {
            if (gpa >= 3.5) return "Dean's List";
            if (gpa >= 2.0) return "Good Standing";
            return "Academic Probation";
        }
    }

    // Input validation helper (for demo: shows validation logic)
    static boolean isValidAge(int age)   { return age >= 16 && age <= 100; }
    static boolean isValidGPA(double g)  { return g >= 0.0 && g <= 4.0; }
    static boolean isValidName(String n) { return n != null && n.trim().length() >= 2; }

    public static void main(String[] args) {
        // Simulate multi-student input processing
        System.out.println("=== Student Registration System ===");
        System.out.println("(Simulated — replace arrays with Scanner.nextLine() for live input)\n");

        List<Student> students = new ArrayList<>();

        // Simulated entries
        String[][] data = {
            {"Alice Johnson", "20", "3.9"},
            {"Bob Smith",     "22", "2.8"},
            {"Carol White",   "19", "1.5"},
            {"Dave Brown",    "21", "3.5"}
        };

        for (String[] row : data) {
            String name = row[0].trim();
            int    age  = Integer.parseInt(row[1].trim());
            double gpa  = Double.parseDouble(row[2].trim());

            if (!isValidName(name))  { System.out.println("Invalid name: "  + name); continue; }
            if (!isValidAge(age))    { System.out.println("Invalid age: "   + age);  continue; }
            if (!isValidGPA(gpa))    { System.out.println("Invalid GPA: "   + gpa);  continue; }

            students.add(new Student(name, age, gpa));
            System.out.printf("Registered: %-15s Age: %d GPA: %.1f%n", name, age, gpa);
        }

        System.out.println("\n=== Report ===");
        System.out.printf("Total students: %d%n", students.size());
        double avgGPA = students.stream().mapToDouble(Student::gpa).average().orElse(0);
        System.out.printf("Average GPA: %.2f%n", avgGPA);

        System.out.println("\nStudent Status:");
        students.forEach(s -> System.out.printf("  %-15s → %s%n", s.name(), s.status()));
    }
}
