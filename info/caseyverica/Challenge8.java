package info.caseyverica;


import org.w3c.dom.ls.LSOutput;

public class Challenge8 {

    public static void main(String[] args) {
        NewTutor tutor = new NewTutor();
        NewStudent student = new NewStudent(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });

        tutorThread.start();
        studentThread.start();
    }
}

//class Tutor {
//    private Student student;
//
//    public synchronized void setStudent(Student student) {
//        this.student = student;
//    }
//
//    public synchronized void studyTime() {
//        System.out.println("Tutor has arrived");
//        try {
//            // wait for student to arrive and hand in assignment
//            Thread.sleep(300);
//        }
//        catch (InterruptedException e) {
//
//        }
//        student.startStudy();
//        System.out.println("Tutor is studying with student");
//    }
//
//    public synchronized void getProgressReport() {
//        // get progress report
//        System.out.println("Tutor gave progress report");
//    }
//}
//
//class Student {
//
//    private Tutor tutor;
//
//    Student(Tutor tutor) {
//        this.tutor = tutor;
//    }
//
//    public synchronized void startStudy() {
//        // study
//        System.out.println("Student is studying");
//    }
//
//    public synchronized void handInAssignment() {
//        tutor.getProgressReport();
//        System.out.println("Student handed in assignment");
//    }
//}




//
// Challenge 9 stuff
//




class NewTutor {
    private NewStudent student;

    public void setStudent(NewStudent student) {
        this.student = student;
    }

    public void studyTime() {

        synchronized (this) {
            System.out.println("Tutor has arrived");
            synchronized (student) {
                try {
                    //wait for student to arrive
                    this.wait();
                } catch (InterruptedException e){}
                student.startStudy();
                System.out.println("Tutor is studying with student");
            }
        }
    }

    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

class NewStudent {

    private NewTutor tutor;

    public NewStudent(NewTutor tutor) {
        this.tutor = tutor;
    }

    public void startStudy() {
        // study
        System.out.println("The student is studying");
    }

    public void handInAssignment() {
        synchronized (tutor) {
            tutor.getProgressReport();
            synchronized (this) {
                System.out.println("Student handed in assignment");
                tutor.notifyAll();
            }
        }
    }
}