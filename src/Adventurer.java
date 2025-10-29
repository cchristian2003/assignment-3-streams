import java.util.List;

public class Adventurer implements Comparable<Adventurer>
{
    private String name;
    private int age;
    private String role;
    private double goldEarned;
    private List<Skill> skills;

    public Adventurer(String name, int age, String role,
                      double goldEarned, List<Skill> skills)
        {
        this.name = name;
        this.age = age;
        this.role = role;
        this.goldEarned = goldEarned;
        this.skills = skills;
        }

    @Override
    public int compareTo(Adventurer o)
    {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString()
    {
        return name + " is a " + age + " year old "
            + role + " who has earned " + goldEarned
            + " gold. They know the following skills: "
            + skills;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public double getGoldEarned()
    {
        return goldEarned;
    }

    public void setGoldEarned(double goldEarned)
    {
        this.goldEarned = goldEarned;
    }

    public List<Skill> getSkills()
    {
        return skills;
    }

    public void setSkills(List<Skill> skills)
    {
        this.skills = skills;
    }
}
