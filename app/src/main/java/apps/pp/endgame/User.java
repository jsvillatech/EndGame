package apps.pp.endgame;

public class User {


    private String id;
    private String name;
    private String sex;
    private String age;
    private String vote;


    public User(){


    }


    public User(String idB, String nameB, String sexB, String ageB, String voteB) {


        id = idB;
        name = nameB;
        sex = sexB;
        age = ageB;
        vote = voteB;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }


}
