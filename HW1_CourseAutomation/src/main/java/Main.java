public class Main {
    public static void main(String... args) {
        boolean isCourseAdded;
        try {
            // SCENARIO ONE  CREATE ADMIN , TEACHERS AND USERS
            AutomationSystem GTUStudentSystem = new AutomationSystem("Gebze Technical University Moodle System");
            Admin admin = null;

            Student stdMercan = new Student("Mercan", "Karacabey", "mkrcby", "123", "mercankrcby@gmail.com", 131044009);
            Student stdGokce = new Student("Gokce", "Demir", "gokdemir", "123", "gdemir@gmail.com", 141044001);
            Student stdOsman = new Student("Osman", "Suzer", "osuzer", "123", "osuzer@gmail.com", 123456789);
            Student stdAliYasin = new Student("Ali Yasin", "ESER", "aliysn", "123", "aliyasineser@gmail.com", 141044058);

            System.out.println("\n\t######\t\tSCENARIO 1 - CREATE LOCAL STUDENTS\t\t######\t");
            System.out.println(stdMercan+"\n"+stdGokce+"\n"+stdOsman+"\n"+stdAliYasin);

            Teacher tErdogan = new Teacher("Fatih Erdogan", "Sevilgen", "ferdogansev", "123", "ferdogan@gtu.edu.tr");
            Teacher tYGenc = new Teacher("Yakup", "Genc", "ygenc", "123", "ygenc@gtu.edu.tr");
            Teacher tFTektas = new Teacher("Furkan", "Tektas", "ftektas", "123", "ftektas@gtu.ed.tr");

            System.out.println("\n\t######\t\tSCENARIO 2 - CREATE LOCAL TEACHERS\t\t######\t");
            System.out.println(tErdogan+"\n"+tYGenc+"\n"+tFTektas);

            Course cse222 = new Course("CSE222");
            Course cse102 = new Course("CSE102");
            Course cse108 = new Course("CSE108");

            System.out.println("\n\t######\t\tSCENARIO 3 -  CREATE LOCAL COURSES\t\t######\t");
            System.out.println(cse222+"\n"+cse102+"\n"+cse108);

            // LOGIN TO SYSTEM AS ADMIN
            User loginUser = GTUStudentSystem.login("hmenn", "hasan5669");

            System.out.println("\n\t######\t\tSCENARIO 4 - ADMIN LOGIN AND ADD COURSES IN THE SYSTEM\t\t######\t");
            if (loginUser instanceof Admin) {
                admin = (Admin) loginUser;
                System.out.println("---> Admin Login to system <---\n");
            }



            // Add independent copy of courses
            isCourseAdded= admin.addCourse(cse222);
            if (isCourseAdded)
                System.out.println("ADDED " + cse222);

            isCourseAdded = admin.addCourse(cse102);
            if (isCourseAdded)
                System.out.println("ADDED " + cse102);

            isCourseAdded= admin.addCourse(cse108);
            if (isCourseAdded)
                System.out.println("ADDED " + cse108);

            isCourseAdded= admin.addCourse(cse222);
            if (isCourseAdded)
                System.out.println("ADDED " + cse222+"\n");


            if(admin.addStudent(stdMercan))
                System.out.println("ADDED "+stdMercan);
            if(admin.addStudent(stdGokce))
                System.out.println("ADDED "+stdGokce);
            if(admin.addStudent(stdOsman))
                System.out.println("ADDED "+stdOsman);
            if(admin.addStudent(stdAliYasin))
                System.out.println("ADDED "+stdAliYasin+"\n");


            if(admin.addTeacher(cse222, tErdogan))
                System.out.println(tErdogan +" ADDED in "+cse222);
            if(admin.addTeacher(tFTektas))
                System.out.println(tFTektas +" ADDED in SYSTEM");
            if(admin.addTeacher(cse102, tYGenc))
                System.out.println(tYGenc +" ADDED in "+cse102);
            if(admin.addTeacher(cse108, tFTektas))
                System.out.println(tFTektas +" ADDED in "+cse108);
            if( admin.addTeacher(cse102, tFTektas))
                System.out.println(tFTektas +" ADDED in "+cse102+"\n");


            System.out.println("\n\t######\t\tSCENARIO 5 - LOGIN SYSTEM TO TEST FUNCTIONS\t\t######\t");

            loginUser = GTUStudentSystem.login(tErdogan.getUserName(), tErdogan.getPassWord());
            if (loginUser instanceof Teacher) {
                tErdogan = (Teacher) loginUser;
                System.out.println("--> Teacher - " + tErdogan.getName() + " login the system.");
            }

            loginUser = GTUStudentSystem.login(tFTektas.getUserName(), tFTektas.getPassWord());
            if (loginUser instanceof Teacher) {
                tFTektas = (Teacher) loginUser;
                System.out.println("--> Teacher - " + tFTektas.getName() + " login the system.");
            }

            loginUser = GTUStudentSystem.login(stdMercan.getUserName(), stdMercan.getPassWord());
            if (loginUser instanceof Student) {
                stdMercan = (Student) loginUser;
                System.out.println("--> Student - " + stdMercan.getName() + " login the system.");
            }

            loginUser = GTUStudentSystem.login(stdAliYasin.getUserName(), stdAliYasin.getPassWord());
            if (loginUser instanceof Student) {
                stdAliYasin = (Student) loginUser;
                System.out.println("--> Studen - " + stdAliYasin.getName() + " login the system.");
            }


            System.out.println("\n\t######\t\tSCENARIO 6 - LOOK GENERAL SYSTEM\t\t######\t");
            System.out.println(GTUStudentSystem);


            System.out.println("\n\t######\t\tSCENARIO 7 - USERS ENROL  THE COURSES AND SHOW COURSE ENROLMENTS\t\t######\t");

            stdMercan.enrolCourse(cse222);
            stdAliYasin.enrolCourse(cse102);

            System.out.println("--------------");
            System.out.println("\n"+cse222 + " ENROLMENTS : ");
            System.out.print(tErdogan.showEnrolments(cse222));
            System.out.println("--------------");
            System.out.println(cse222 + "BEFORE TEACHER ACCEPT STUDENTS - COURSE STUDENT LIST");
            System.out.print(tErdogan.showCourseStudents(cse222));
            System.out.println("--------------");


            System.out.println("--------------");
            System.out.println("\n"+cse102 + " ENROLMENTS : ");
            System.out.print(tFTektas.showEnrolments(cse102));
            System.out.println("--------------");
            System.out.println(cse102 + " BEFORE TEACHER ACCEPT STUDENTS - COURSE STUDENT LIST");
            System.out.print(tFTektas.showCourseStudents(cse102));
            System.out.println("--------------");



            System.out.println("\n\t######\t\tSCENARIO 8 - FIRST TEACHER ACCEPT ENROLS\t\t######\t");

            tErdogan.acceptEnrolment(cse222, stdMercan);
            System.out.println("--------------");
            System.out.println(cse222 + " AFTER TEACHER ACCEPT REQUEST - ENROLMENT LIST ");
            System.out.print(tErdogan.showEnrolments(cse222));
            System.out.println("--------------");
            System.out.println(cse222 + " AFTER TEACHER ACCEPT REQUEST - COURSE STUDENT LIST");
            System.out.print(tErdogan.showCourseStudents(cse222));
            System.out.println("--------------");


            System.out.println("\n\t######\t\tSCENARIO 9 - SECOND TEACHER ACCEPT ENROLS\t\t######\t");
            tFTektas.acceptEnrolment(cse102, stdAliYasin);
            System.out.println("--------------");
            System.out.println(cse102 + " AFTER TEACHER ACCEPT REQUEST - ENROLMENT LIST ");
            System.out.print(tFTektas.showEnrolments(cse102));
            System.out.println("--------------");
            System.out.println(cse102 + " AFTER TEACHER ACCEPT REQUEST - COURSE STUDENT LIST");
            System.out.print(tFTektas.showCourseStudents(cse102));
            System.out.println("--------------");


            System.out.println("\n\t######\t\tSCENARIO 10 - ADD ITEMS IN THE COURSE\t\t######\t");
            tErdogan.addCourseItem(cse222, new Book("Java Programming", "Koffman", "2015", 50));
            tErdogan.addCourseItem(cse222, new Book("Effective Java", "hmenn", "2011", 25));
            tErdogan.addCourseItem(cse222, new URL("GTU", "www.gtu.edu.tr"));

            tFTektas.addCourseItem(cse102, new Book("C Programming", "Deılte", "2013", 12));
            tFTektas.addCourseItem(cse102, new Book("Absolute C", "necmeddin", "2016", 15));
            tFTektas.addCourseItem(cse102, new URL("hmenn", "www.hasanmen.com"));


            System.out.println("\n\t######\t\tSCENARIO 11 - SHOW COURSE ITEMS FOR TEACTER\t\t######\t");
            System.out.println(tErdogan);
            System.out.println(tErdogan.showCourseItems(cse222));

            System.out.println(tFTektas);
            System.out.println(tFTektas.showCourseItems(cse102));


            System.out.println("\n\t######\t\tSCENARIO 12 - UPDATE COURSE ITEMS WITH TEACTER\t\t######\t");

//            stdMercan.unEnrolCourse(cse222);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ARRAY INDEX OUT OF BOUND. BE CAREFULL \n PLEASE LOOK STACT TRACE !!!");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
