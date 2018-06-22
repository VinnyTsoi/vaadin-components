package com.vinnytsoi;

class Student
{

    public Student() 
    {
        super();
    }


    public Student(String name, String studentNumber, int mark, String grade) 
    {
        this.name = name;
        this.studentNumber = studentNumber;
        this.mark = mark;
        this.grade = grade;
    }
    
	private String name;
    private int mark;
    private String grade;
    private String studentNumber;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the mark
     */
    public int getMark() {
        return mark;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @return the studentNumber
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    @Override
    public String toString() {
        return "This is a toString method";
    }
    
}