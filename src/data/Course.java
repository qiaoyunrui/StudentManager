package data;

/**
 * Created by qiao1 on 2016/12/28.
 */
public class Course {

    private final String no;

    private String name;

    private String desc;

    private int selected;    //已选人数

    private int capacity;   //容量

    private String term;    //学期

    public Course(String no) {
        this.no = no;
    }

    public Course(String no, String name, String desc, int capacity,
                  String term, int selected) {
        this(no);
        this.name = name;
        this.desc = desc;
        this.selected = selected;
        this.capacity = capacity;
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
